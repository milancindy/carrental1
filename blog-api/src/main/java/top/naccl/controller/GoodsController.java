package top.naccl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.naccl.common.pojo.Result;
import top.naccl.common.utils.ResultUtil;
import top.naccl.entity.manager.dto.front.AllGoodsResult;
import top.naccl.entity.manager.dto.front.ProductDet;
import top.naccl.entity.manager.pojo.TbPanel;
import top.naccl.entity.manager.pojo.TbPanelContent;
import top.naccl.service.mall.ContentService;

import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    private ContentService contentService;
    //导航
    @GetMapping(value = "/goods/navList")
    public Result<List<TbPanelContent>> getNavList(){

        List<TbPanelContent> list=contentService.getNavList();
        Result<List<TbPanelContent>> result=new Result<>();
       result.setCode(200);
       result.setResult(list);
        return result;
    }
    //首页
    @RequestMapping(value = "/goods/home",method = RequestMethod.GET)
    public Result<List<TbPanel>> getProductHome(){

        List<TbPanel> list=contentService.getHome();
        return new ResultUtil<List<TbPanel>>().setData(list);
    }
    //商品详情
    @RequestMapping(value = "/goods/productDet",method = RequestMethod.GET)
    public Result<ProductDet> getProductDet(Long productId){

        ProductDet productDet=contentService.getProductDet(productId);
        return new ResultUtil<ProductDet>().setData(productDet);
    }
//所有商品
    @RequestMapping(value = "/goods/allGoods",method = RequestMethod.GET)
    public Result<AllGoodsResult> getAllProduct(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "20") int size,
                                                @RequestParam(defaultValue = "") String sort,
                                                @RequestParam(defaultValue = "") Long cid,
                                                @RequestParam(defaultValue = "-1") int priceGt,
                                                @RequestParam(defaultValue = "-1") int priceLte){

        AllGoodsResult allGoodsResult=contentService.getAllProduct(page,size,sort,cid,priceGt,priceLte);
        return new ResultUtil<AllGoodsResult>().setData(allGoodsResult);
    }
    //商品推荐板块
    @RequestMapping(value = "/goods/recommend",method = RequestMethod.GET)
    public Result<List<TbPanel>> getRecommendGoods(){

        List<TbPanel> list=contentService.getRecommendGoods();
        return new ResultUtil<List<TbPanel>>().setData(list);
    }

}
