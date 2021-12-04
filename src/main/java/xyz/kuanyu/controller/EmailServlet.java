package xyz.kuanyu.controller;

import xyz.kuanyu.util.EmailUtil;
import xyz.kuanyu.util.LogUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "EmailServlet", value = "/EmailServlet")
public class EmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String email = request.getParameter("email");

        LogUtil.log("即将发送Email验证码",email);
        int verify = new Random().nextInt(9000)+1000;
        EmailUtil.sendLoginAuthCodeEmail(email, verify);

        session.setAttribute("email",email);
        session.setAttribute("verify",verify);
        System.out.println(session.getAttribute("email"));
        System.out.println(session.getAttribute("verify"));
    }
    //根据前端传来的邮箱，发送邮件，保存邮箱和验证码到session
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
