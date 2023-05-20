package com.hnust.controller;
import com.hnust.pojo.User;
import com.hnust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String userLogin(User user, HttpSession session, Model model){

        boolean flag = false;

        User u = userService.queryUserByName(user.getEmail());

        if (u!=null){
            if (u.getPassword().equals(user.getPassword())){
                session.setAttribute("u",u);
                flag = true;
            }else {
                model.addAttribute("msg","密码不正确！");
            }
        }else {
            model.addAttribute("msg","用户不存在！");
        }

        return flag?"redirect:/course/allCourse":"error";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){

        if (session.getAttribute("u")!=null){
            //退出登录移除session
            session.removeAttribute("u");
        }

        return "login";
    }

}
