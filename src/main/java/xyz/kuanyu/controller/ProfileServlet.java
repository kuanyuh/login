package xyz.kuanyu.controller;

import com.alibaba.fastjson.JSON;
import xyz.kuanyu.domain.User;
import xyz.kuanyu.service.UserService;
import xyz.kuanyu.util.JDBCUtil;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ProfileServlet", value = "/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //TODO session验证
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("userid");
        System.out.println("session验证结果如下：" + id);
        if (id==0) { //id为空
            return;
        }
        User user = JDBCUtil.serviceInstance().findUserById(id);
        if (user!=null){
            String userJson = JSON.toJSONString(user);
            System.out.println(userJson);

            request.setAttribute("user", user);
            request.getRequestDispatcher("profile.jsp").forward(request,response);
            PrintWriter out=response.getWriter();
            out.print(userJson);
        }else {
            System.out.println("用户不存在");
            user.setName("用户不存在");
        }
    }
    //从 session 中提取 userid，jdbc提取数据
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
