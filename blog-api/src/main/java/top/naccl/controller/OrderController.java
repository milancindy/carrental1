package top.naccl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.naccl.common.pojo.Result;
import top.naccl.common.utils.ResultUtil;
import top.naccl.entity.manager.dto.front.Order;
import top.naccl.entity.manager.dto.front.OrderInfo;
import top.naccl.entity.manager.dto.front.PageOrder;
import top.naccl.service.mall.OrderService;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    //获得用户所有订单
    @RequestMapping(value = "/member/orderList",method = RequestMethod.GET)
    public Result<PageOrder> getOrderList(String userId,
                                          @RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "5") int size){

        PageOrder pageOrder=orderService.getOrderList(Long.valueOf(userId), page, size);
        return new ResultUtil<PageOrder>().setData(pageOrder);
    }

    //通过id获取订单
    @RequestMapping(value = "/member/orderDetail",method = RequestMethod.GET)
    public Result<Order> getOrder(String orderId){

        Order order=orderService.getOrder(Long.valueOf(orderId));
        return new ResultUtil<Order>().setData(order);
    }

    //创建订单
    @RequestMapping(value = "/member/addOrder",method = RequestMethod.POST)
    public Result<Object> addOrder(@RequestBody OrderInfo orderInfo){

        Long orderId=orderService.createOrder(orderInfo);
        return new ResultUtil<Object>().setData(orderId.toString());
    }

    //取消订单
    @RequestMapping(value = "/member/cancelOrder",method = RequestMethod.POST)
    public Result<Object> cancelOrder(@RequestBody Order order){

        int result=orderService.cancelOrder(order.getOrderId());
        return new ResultUtil<Object>().setData(result);
    }

    //删除订单
    @RequestMapping(value = "/member/delOrder",method = RequestMethod.GET)
    public Result<Object> delOrder(String orderId){

        int result=orderService.delOrder(Long.valueOf(orderId));
        return new ResultUtil<Object>().setData(result);
    }

}
