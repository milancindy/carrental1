package top.naccl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.naccl.common.pojo.Result;
import top.naccl.common.utils.ResultUtil;
import top.naccl.entity.manager.pojo.TbAddress;
import top.naccl.service.mall.AddressService;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 获得所有收货地址
     * @param tbAddress
     * @return
     */
    @RequestMapping(value = "/member/addressList",method = RequestMethod.POST)
    public Result<List<TbAddress>> addressList(@RequestBody TbAddress tbAddress){

        List<TbAddress> list=addressService.getAddressList(tbAddress.getUserId());
        return new ResultUtil<List<TbAddress>>().setData(list);
    }

    //通过id获得收货地址
    @RequestMapping(value = "/member/address",method = RequestMethod.POST)
    public Result<TbAddress> address(@RequestBody TbAddress tbAddress){

        TbAddress address=addressService.getAddress(tbAddress.getAddressId());
        return new ResultUtil<TbAddress>().setData(address);
    }

    //添加收货地址
    @RequestMapping(value = "/member/addAddress",method = RequestMethod.POST)
    public Result<Object> addAddress(@RequestBody TbAddress tbAddress){

        int result=addressService.addAddress(tbAddress);
        return new ResultUtil<Object>().setData(result);
    }

    //编辑收货地址
    @RequestMapping(value = "/member/updateAddress",method = RequestMethod.POST)
    public Result<Object> updateAddress(@RequestBody TbAddress tbAddress){

        int result=addressService.updateAddress(tbAddress);
        return new ResultUtil<Object>().setData(result);
    }

    //删除收货地址
    @RequestMapping(value = "/member/delAddress",method = RequestMethod.POST)
    public Result<Object> delAddress(@RequestBody TbAddress tbAddress){

        int result=addressService.delAddress(tbAddress);
        return new ResultUtil<Object>().setData(result);
    }
}
