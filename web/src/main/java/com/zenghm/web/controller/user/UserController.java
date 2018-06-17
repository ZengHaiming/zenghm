package com.zenghm.web.controller.user;

import com.zenghm.dto.user.SysUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import com.zenghm.web.controller.ControllerConstant;
import com.zenghm.common.ResultUtil;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create date:2018/06/12.
 * Created by: Airlen.
 * Class name:user.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpSession session, @RequestParam("user") String userid,
                 @RequestParam("password") String password){
        SysUser user = new SysUser();
        user.setUserid(userid);
        user.setUsername(userid);
        session.setAttribute(ControllerConstant.AUTHENTICATED_USER,user);
        /**
         * 返回字符时 ，会默认返回的是视图，导致请求失败，需要添加注解，表示返回的在响应体中
         */
        return ResultUtil.SUCCESS;
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
    @ResponseBody
    public String logout(HttpSession session){
        session.removeAttribute(ControllerConstant.AUTHENTICATED_USER);
        return ResultUtil.SUCCESS;
    }
}
