package xyz.kuanyu.controller;

import xyz.kuanyu.domain.User;
import xyz.kuanyu.util.EmailUtil;
import xyz.kuanyu.util.JDBCUtil;
import xyz.kuanyu.util.LogUtil;
import xyz.kuanyu.util.MD5;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);
        PrintWriter out=response.getWriter();
        String way = request.getParameter("way");

        if (way.equals("nextStep")){
            String email = request.getParameter("email");
            System.out.println(email);
            User user = JDBCUtil.serviceInstance().findUserByEmail(email);
            if (user==null){
                System.out.println("email_exist");
                out.print("email_exist");
                return;
            }
            System.out.println("verify");
            session=request.getSession();
            String verify = (String) session.getAttribute("verify");
            System.out.println(verify);
            System.out.println("verify");
            if (email.equals(request.getParameter("email")) && verify.equals(request.getParameter("verify"))){
                session.setAttribute("legal",true);
                System.out.println("next");
                out.print("next");
                return;
            }
            System.out.println("err");
            out.print("err");
            return;

        }else if (way.equals("register")){
            String legal = (String) session.getAttribute("legal");
            if (legal!="true"){
                out.print("err");
                return;
            }
            User user = new User();//新用户
            user.setEmail((String) session.getAttribute("email"));
            user.setPassword(MD5.getMD5String(request.getParameter("password")));

            int nums = JDBCUtil.serviceInstance().addUser(user);
            System.out.println("是否成功"+nums);
            out.print("OK");
        }else{
            System.out.println("注册 请求无法识别");
        }


        User user = null;
        user.setPassword(request.getParameter("confirm"));
        String newPw = MD5.getMD5String(request.getParameter("confirm"));
        //TODO 收尾
        user.setPassword(newPw);
        JDBCUtil.serviceInstance().modifyUser(user);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
