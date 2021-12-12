package xyz.kuanyu.controller;

import jdk.nashorn.internal.scripts.JD;
import xyz.kuanyu.domain.User;
import xyz.kuanyu.util.JDBCUtil;
import xyz.kuanyu.util.MD5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PwResetServlet", value = "/PwResetServlet")
public class PwResetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("注册、找回密码");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        User user = JDBCUtil.serviceInstance().findUserByEmail(email);

        System.out.println("修改密码 原密码："+user.getPassword());
        System.out.println("新密码："+request.getParameter("confirm"));

        user.setPassword(request.getParameter("confirm"));
        String newPw = MD5.getMD5String(request.getParameter("confirm"));
        //TODO 收尾
        user.setPassword(newPw);
        JDBCUtil.serviceInstance().modifyUser(user);
        PrintWriter out=response.getWriter();
        out.print("OK");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
