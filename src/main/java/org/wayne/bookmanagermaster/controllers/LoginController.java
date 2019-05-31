package org.wayne.bookmanagermaster.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wayne.bookmanagermaster.biz.LoginBiz;
import org.wayne.bookmanagermaster.model.User;
import org.wayne.bookmanagermaster.utils.CookieUtils;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    LoginBiz loginBiz;

    @RequestMapping(path = {"/users/register"}, method = {RequestMethod.GET})
    public String register() {
        return "login/register";
    }

    @RequestMapping(path = {"/users/register/do"}, method = {RequestMethod.POST})
    public String doregister(
            Model model,
            HttpServletResponse response,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        User user = new User();
        user.setUserName(name);
        user.setUserEmail(email);
        user.setUserPassword(password);

        try {
            String t = loginBiz.register(user);
            CookieUtils.writeCookie("t", t, response);
        } catch (Exception e) {
            //model.addAttribute("error", e.getMessage());
            //return "404";
        }

        return "redirect:/index";
    }

    @RequestMapping(path = {"/users/login"}, method = {RequestMethod.GET})
    public String login() {
        return "login/login";
    }

    @RequestMapping(path = {"/users/login/do"}, method = {RequestMethod.POST})
    public String doLogin(
            Model model,
            HttpServletResponse response,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        try {
            String t = loginBiz.login(email, password);
            CookieUtils.writeCookie("t", t, response);
            return "redirect:/index";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "404";
        }

    }

    @RequestMapping(path = {"/users/logout/do"}, method = {RequestMethod.GET})
    public String doLogout(
            @CookieValue("t") String t
    ) {
        loginBiz.logout(t);
        return "redirect:/index";
    }

}
