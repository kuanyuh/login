package xyz.kuanyu.controller;

import com.alibaba.fastjson.JSON;
import xyz.kuanyu.domain.User;
import xyz.kuanyu.util.JDBCUtil;
import xyz.kuanyu.util.MD5;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "EditServlet", value = "/EditServlet")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int userid = (int) session.getAttribute("userid");

        String userJson = compareEdit(userid, request);
        PrintWriter pw = response.getWriter();
        pw.println(userJson);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    private String compareEdit(int id, HttpServletRequest request){

        User user = JDBCUtil.serviceInstance().findUserById(id);
        Map<String, Object> map = new HashMap<>();

        String parName= request.getParameter("name").trim();
        if (parName!="") user.setName(parName);

        String parEmail = request.getParameter("email").trim();
        if (parEmail!="") user.setEmail(parEmail);

        String parAge = request.getParameter("age").trim();
        if (parAge!="") user.setAge(Integer.parseInt(parAge));

        String parPhoneNumber = request.getParameter("phoneNumber").trim();
        if (parPhoneNumber!="") user.setPhoneNumber(parPhoneNumber);

        String parPassword = request.getParameter("password").trim();
//        if (parPassword!="") user.setPassword(parPassword); //TODO
        if (parPassword!="") user.setPassword(MD5.getMD5String(parPassword));

        int nums = JDBCUtil.serviceInstance().modifyUser(user);
        System.out.println("更新信息"+nums);

        map.put("name", user.getName());
        map.put("email", user.getEmail());
        map.put("age", user.getAge());
        map.put("phoneNumber", user.getPhoneNumber());
        String userJson = JSON.toJSONString(map);
        System.out.println(userJson);

        return userJson;
    }

}
