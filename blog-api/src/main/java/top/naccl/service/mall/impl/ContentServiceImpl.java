package top.naccl.service.mall.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.naccl.common.utils.DtoUtil;
import top.naccl.entity.manager.dto.front.AllGoodsResult;
import top.naccl.entity.manager.dto.front.Product;
import top.naccl.entity.manager.dto.front.ProductDet;
import top.naccl.entity.manager.pojo.*;
import top.naccl.mapper.TbItemDescMapper;
import top.naccl.mapper.TbItemMapper;
import top.naccl.mapper.TbPanelContentMapper;
import top.naccl.mapper.TbPanelMapper;
import top.naccl.service.mall.ContentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentServiceImpl  implements ContentService {

    @Value("${HEADER_PANEL_ID}")
    private int HEADER_PANEL_ID;
    @Value("${RECOMEED_PANEL_ID}")
    private Integer RECOMEED_PANEL_ID;
    @Autowired
    private TbPanelContentMapper tbPanelContentMapper;
    @Autowired
    private TbPanelMapper tbPanelMapper;
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public List<TbPanelContent> getNavList() {

        List<TbPanelContent> list = new ArrayList<>();

        TbPanelContentExample exampleContent=new TbPanelContentExample();
        exampleContent.setOrderByClause("sort_order");
        TbPanelContentExample.Criteria criteriaContent=exampleContent.createCriteria();
        //条件查询
        criteriaContent.andPanelIdEqualTo(HEADER_PANEL_ID);
        list=tbPanelContentMapper.selectByExample(exampleContent);

        return list;
    }

    @Override
    public List<TbPanel> getHome() {

        List<TbPanel> list=new ArrayList<>();

        //没有缓存
        TbPanelExample example=new TbPanelExample();
        TbPanelExample.Criteria criteria=example.createCriteria();
        //条件查询
        criteria.andPositionEqualTo(0);
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("sort_order");
        list=tbPanelMapper.selectByExample(example);
        for(TbPanel tbPanel:list){
            TbPanelContentExample exampleContent=new TbPanelContentExample();
            exampleContent.setOrderByClause("sort_order");
            TbPanelContentExample.Criteria criteriaContent=exampleContent.createCriteria();
            //条件查询
            criteriaContent.andPanelIdEqualTo(tbPanel.getId());
            List<TbPanelContent> contentList=tbPanelContentMapper.selectByExample(exampleContent);
            for(TbPanelContent content:contentList){
                if(content.getProductId()!=null){
                    TbItem tbItem=tbItemMapper.selectByPrimaryKey(content.getProductId());
                    content.setProductName(tbItem.getTitle());
                    content.setSalePrice(tbItem.getPrice());
                    content.setSubTitle(tbItem.getSellPoint());
                }
            }

            tbPanel.setPanelContents(contentList);
        }

        return list;
    }
    @Override
    public ProductDet getProductDet(Long id) {



        TbItem tbItem=tbItemMapper.selectByPrimaryKey(id);
        ProductDet productDet=new ProductDet();
        productDet.setProductId(id);
        productDet.setProductName(tbItem.getTitle());
        productDet.setSubTitle(tbItem.getSellPoint());
        if(tbItem.getLimitNum()!=null&&!tbItem.getLimitNum().toString().isEmpty()){
            productDet.setLimitNum(Long.valueOf(tbItem.getLimitNum()));
        }else{
            productDet.setLimitNum(Long.valueOf(tbItem.getNum()));
        }
        productDet.setSalePrice(tbItem.getPrice());

        TbItemDesc tbItemDesc=tbItemDescMapper.selectByPrimaryKey(id);
        if(tbItemDesc!=null) {
            productDet.setDetail(tbItemDesc.getItemDesc());
        }

        if(tbItem.getImage()!=null&&!tbItem.getImage().isEmpty()){
            String images[]=tbItem.getImage().split(",");
            productDet.setProductImageBig(images[0]);
            List list=new ArrayList();
            for(int i=0;i<images.length;i++){
                list.add(images[i]);
            }
            productDet.setProductImageSmall(list);
        }
        return productDet;
    }

    @Override
    public AllGoodsResult getAllProduct(int page, int size, String sort, Long cid, int priceGt, int priceLte) {

        AllGoodsResult allGoodsResult=new AllGoodsResult();
        List<Product> list=new ArrayList<>();
        //分页执行查询返回结果
        if(page<=0) {
            page = 1;
        }
        PageHelper.startPage(page,size);

        //判断条件
        String orderCol="created";
        String orderDir="desc";
        if(sort.equals("1")){
            orderCol="price";
            orderDir="asc";
        }else if(sort.equals("-1")){
            orderCol="price";
            orderDir="desc";
        }else{
            orderCol="created";
            orderDir="desc";
        }

        List<TbItem> tbItemList = tbItemMapper.selectItemFront(cid,orderCol,orderDir,priceGt,priceLte);
        PageInfo<TbItem> pageInfo=new PageInfo<>(tbItemList);

        for(TbItem tbItem:tbItemList){
            Product product= DtoUtil.TbItem2Product(tbItem);
            list.add(product);
        }

        allGoodsResult.setData(list);
        allGoodsResult.setTotal((int) pageInfo.getTotal());

        return allGoodsResult;
    }
    @Override
    public List<TbPanel> getRecommendGoods() {


        List<TbPanel> list = new ArrayList<>();

        list = getTbPanelAndContentsById(RECOMEED_PANEL_ID);

        return list;
    }
    List<TbPanel> getTbPanelAndContentsById(Integer panelId){

        List<TbPanel> list=new ArrayList<>();
        TbPanelExample example=new TbPanelExample();
        TbPanelExample.Criteria criteria=example.createCriteria();
        //条件查询
        criteria.andIdEqualTo(panelId);
        criteria.andStatusEqualTo(1);
        list=tbPanelMapper.selectByExample(example);
        for(TbPanel tbPanel:list){
            TbPanelContentExample exampleContent=new TbPanelContentExample();
            exampleContent.setOrderByClause("sort_order");
            TbPanelContentExample.Criteria criteriaContent=exampleContent.createCriteria();
            //条件查询
            criteriaContent.andPanelIdEqualTo(tbPanel.getId());
            List<TbPanelContent> contentList=tbPanelContentMapper.selectByExample(exampleContent);
            for(TbPanelContent content:contentList){
                if(content.getProductId()!=null){
                    TbItem tbItem=tbItemMapper.selectByPrimaryKey(content.getProductId());
                    content.setProductName(tbItem.getTitle());
                    content.setSalePrice(tbItem.getPrice());
                    content.setSubTitle(tbItem.getSellPoint());
                }
            }

            tbPanel.setPanelContents(contentList);
        }
        return list;
    }
}
