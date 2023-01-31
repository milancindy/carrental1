package top.naccl.service.mall.impl;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.naccl.common.utils.DtoUtil;
import top.naccl.entity.manager.dto.front.CartProduct;
import top.naccl.entity.manager.pojo.TbItem;
import top.naccl.mapper.TbItemMapper;
import top.naccl.service.RedisService;
import top.naccl.service.mall.CartService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    private final static Logger log= LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private RedisService jedisClient;
    @Value("${CART_PRE}")
    private String CART_PRE;
    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public int addCart(long userId, long itemId, int num) {

        //hash: "key:用户id" field："商品id" value："商品信息"
        Object hexists = jedisClient.getStringByKey(CART_PRE + ":" + userId+":"+itemId + "");
        //如果存在数量相加
        if (hexists!=null) {
                CartProduct cartProduct = new Gson().fromJson(hexists.toString(),CartProduct.class);
                cartProduct.setProductNum(cartProduct.getProductNum() + num);
                jedisClient.saveKVToHash(CART_PRE + ":" + userId,itemId + "", new Gson().toJson(cartProduct));
            return 1;
        }
        else {
            //如果不存在，根据商品id取商品信息
            TbItem item = itemMapper.selectByPrimaryKey(itemId);
            if (item == null) {
                return 0;
            }
            CartProduct cartProduct = DtoUtil.TbItem2CartProduct(item);
            cartProduct.setProductNum((long) num);
            cartProduct.setChecked("1");
            jedisClient.saveKVToHash(CART_PRE + ":" + userId,itemId + "", new Gson().toJson(cartProduct));
            return 1;
        }
    }

    @Override
    public List<CartProduct> getCartList(long userId) {

        Map<String,String> mapByHash = jedisClient.getMapByHash(CART_PRE + ":" + userId);
        List<CartProduct> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : mapByHash.entrySet()) {

            CartProduct cartProduct = new Gson().fromJson(entry.getValue().toString(),CartProduct.class);
            list.add(cartProduct);
        }
        return list;
    }

    @Override
    public int updateCartNum(long userId, long itemId, int num, String checked) {

        Object json = jedisClient.getValueByHashKey(CART_PRE + ":" + userId,itemId + "");
        if(json==null){
            return 0;
        }
        CartProduct cartProduct = new Gson().fromJson(json.toString(),CartProduct.class);
        cartProduct.setProductNum((long) num);
        cartProduct.setChecked(checked);
        jedisClient.saveKVToHash(CART_PRE + ":" + userId,itemId + "", new Gson().toJson(cartProduct));
        return 1;
    }

    @Override
    public int checkAll(long userId,String checked) {

        List<CartProduct> jsonList = jedisClient.getListByValue(CART_PRE + ":" + userId);

        for (CartProduct cartProduct : jsonList) {
            if("true".equals(checked)) {
                cartProduct.setChecked("1");
            }else if("false".equals(checked)) {
                cartProduct.setChecked("0");
            }else {
                return 0;
            }
            jedisClient.saveKVToHash(CART_PRE + ":" + userId,cartProduct.getProductId() + "", new Gson().toJson(cartProduct));
        }

        return 1;
    }

    @Override
    public int deleteCartItem(long userId, long itemId) {

        jedisClient.deleteByHashKey(CART_PRE + ":" + userId, itemId + "");
        return 1;
    }

    @Override
    public int delChecked(long userId) {

        List<String> jsonList = jedisClient.getListByValue(CART_PRE+":"+userId);
        for (String json : jsonList) {
            CartProduct cartProduct = new Gson().fromJson(json,CartProduct.class);
            if("1".equals(cartProduct.getChecked())) {
                jedisClient.deleteByHashKey(CART_PRE+":"+userId, cartProduct.getProductId()+"");
            }
        }
        return 1;
    }

}
