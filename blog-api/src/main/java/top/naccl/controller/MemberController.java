package top.naccl.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.naccl.common.pojo.Result;
import top.naccl.common.utils.GeetestLib;
import top.naccl.common.utils.ResultUtil;
import top.naccl.entity.manager.dto.front.CommonDto;
import top.naccl.entity.manager.dto.front.Member;
import top.naccl.entity.manager.dto.front.MemberLoginRegist;
import top.naccl.service.RedisService;
import top.naccl.service.mall.LoginService;
import top.naccl.service.mall.MemberService;
import top.naccl.service.mall.RegisterService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;

@RestController
public class MemberController {
    private final static Logger log= LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private RedisService jedisClient;



//用户登录
    @RequestMapping(value = "/member/login",method = RequestMethod.POST)
    public Result<Member> login(@RequestBody MemberLoginRegist memberLoginRegist,
                                HttpServletRequest request){

        //极验验证
        GeetestLib gtSdk = new GeetestLib(GeetestLib.id, GeetestLib.key,GeetestLib.newfailback);

        String challenge=memberLoginRegist.getChallenge();
        String validate=memberLoginRegist.getValidate();
        String seccode=memberLoginRegist.getSeccode();

        //从redis中获取gt-server状态
        //int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
        int gt_server_status_code = Integer.parseInt(jedisClient.getMapByValue(memberLoginRegist.getStatusKey()).toString());

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }

        Member member=new Member();
        if (gtResult == 1) {
            // 验证成功
            member=loginService.userLogin(memberLoginRegist.getUserName(), memberLoginRegist.getUserPwd());
        }
        else {
            // 验证失败
            member.setState(0);
            member.setMessage("验证失败");
        }
        return new ResultUtil<Member>().setData(member);
    }

    //判断用户是否登录
    @RequestMapping(value = "/member/checkLogin",method = RequestMethod.GET)
    public Result<Member> checkLogin(@RequestParam(defaultValue = "") String token){

        Member member=loginService.getUserByToken(token);
        return new ResultUtil<Member>().setData(member);
    }

    //退出登录
    @RequestMapping(value = "/member/loginOut",method = RequestMethod.GET)
    public Result<Object> logout(@RequestParam(defaultValue = "") String token){

        loginService.logout(token);
        return new ResultUtil<Object>().setData(null);
    }

    /**
     * 用户注册
     * @param memberLoginRegist
     * @param request
     * @return
     */
    @RequestMapping(value = "/member/register",method = RequestMethod.POST)
    public Result<Object> register(@RequestBody MemberLoginRegist memberLoginRegist,
                                   HttpServletRequest request){

        //极验验证
        GeetestLib gtSdk = new GeetestLib(GeetestLib.id, GeetestLib.key,GeetestLib.newfailback);

        String challenge=memberLoginRegist.getChallenge();
        String validate=memberLoginRegist.getValidate();
        String seccode=memberLoginRegist.getSeccode();

        //从redis中获取gt-server状态
        //int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
        int gt_server_status_code = Integer.parseInt(jedisClient.getMapByValue(memberLoginRegist.getStatusKey()).toString());

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }

        if (gtResult == 1) {
            // 验证成功
            int result=registerService.register(memberLoginRegist.getUserName(), memberLoginRegist.getUserPwd());
            if(result==0){
                return new ResultUtil<Object>().setErrorMsg("该用户名已被注册");
            }else if(result==-1){
                return new ResultUtil<Object>().setErrorMsg("用户名密码不能为空");
            }
            return new ResultUtil<Object>().setData(result);
        }
        else {
            // 验证失败
            return new ResultUtil<Object>().setErrorMsg("验证失败");
        }
    }

    /**
     * 用户头像上传
     * @param common
     * @return
     */

//    @RequestMapping(value = "/member/imgaeUpload",method = RequestMethod.POST)
//    public Result<Object> imgaeUpload(@RequestBody CommonDto common){
//
//        String imgPath = memberService.imageUpload(common.getUserId(),common.getToken(),common.getImgData());
//        return new ResultUtil<Object>().setData(imgPath);
//    }
}
