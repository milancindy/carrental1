package top.naccl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.naccl.common.pojo.Result;
import top.naccl.common.utils.ResultUtil;
import top.naccl.entity.manager.dto.front.Cart;
import top.naccl.entity.manager.dto.front.CartProduct;
import top.naccl.service.mall.CartService;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    //添加购物车商品
    @RequestMapping(value = "/member/addCart",method = RequestMethod.POST)
    public Result<Object> addCart(@RequestBody Cart cart){

        int result=cartService.addCart(cart.getUserId(),cart.getProductId(),cart.getProductNum());
        return new ResultUtil<Object>().setData(result);
    }

    //获取购物车商品列表
    @RequestMapping(value = "/member/cartList",method = RequestMethod.POST)
    public Result<List<CartProduct>> getCartList(@RequestBody Cart cart){

        List<CartProduct> list=cartService.getCartList(cart.getUserId());
        return new ResultUtil<List<CartProduct>>().setData(list);
    }

    //编辑购物车商品
    @RequestMapping(value = "/member/cartEdit",method = RequestMethod.POST)
    public Result<Object> updateCartNum(@RequestBody Cart cart){

        int result=cartService.updateCartNum(cart.getUserId(),cart.getProductId(),cart.getProductNum(),cart.getChecked());
        return new ResultUtil<Object>().setData(result);
    }

    //是否全选购物车商品
    @RequestMapping(value = "/member/editCheckAll",method = RequestMethod.POST)
    public Result<Object> editCheckAll(@RequestBody Cart cart){

        int result=cartService.checkAll(cart.getUserId(),cart.getChecked());
        return new ResultUtil<Object>().setData(result);
    }

    //删除购物车商品
    @RequestMapping(value = "/member/cartDel",method = RequestMethod.POST)
    public Result<Object> deleteCartItem(@RequestBody Cart cart){

        int result=cartService.deleteCartItem(cart.getUserId(),cart.getProductId());
        return new ResultUtil<Object>().setData(result);
    }

    //删除购物车选中商品
    @RequestMapping(value = "/member/delCartChecked",method = RequestMethod.POST)
    public Result<Object> delChecked(@RequestBody Cart cart){

        cartService.delChecked(cart.getUserId());
        return new ResultUtil<Object>().setData(null);
    }
}
