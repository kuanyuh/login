package xyz.kuanyu.controller;

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

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("尝试登录。。。。");

        HttpSession session = request.getSession();
        String way = request.getParameter("way");
        PrintWriter out=response.getWriter();
        System.out.println(way);
        switch (way){
            case "passwd":
                int userid = Integer.parseInt(request.getParameter("userid"));
                String password = request.getParameter("passwd");

                System.out.println("userid"+userid);

                if (checkPasswd(userid,password,out)){//检查密码
                    //设置session
                    session.setAttribute("userid", userid);
                    out.print("OK");
                }
                break;
            case "email":
                String email = request.getParameter("email");
                int verify = Integer.parseInt(request.getParameter("verify"));

                //验证session
                String sEmail = (String) session.getAttribute("email");
                int sVerify = (int) session.getAttribute("verify");
                System.out.println("email验证结果如下：" + sEmail);
                System.out.println("verify验证结果如下：" + sVerify);


                if (sEmail.equals(email)){//检查验证码
                    if (sVerify==verify){
                        //设置session
                        User user = JDBCUtil.serviceInstance().findUserByEmail(email);
                        session.setAttribute("userid", user.getId());
                        out.print("OK");
                    }else {
                        out.print("ERR_VERIFY");
                    }
                }else {
                    out.print("ERR_EMAIL");
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("使用doPost");

        doGet(request,response);
    }

    private boolean checkPasswd(int userid, String password, PrintWriter out){

        User user = JDBCUtil.serviceInstance().findUserById(userid);
        if (user==null){
            System.out.println("ERR_ID");
            out.print("ERR_ID");
            return false;
        }
        //TODO 收尾
//        if (!user.getPassword().equals(MD5.getMD5String(password))){
        if (!user.getPassword().equals(password)){
            out.print("ERR_PW");
            System.out.println(user.getPassword()+"  "+password);
            return false;
        }
        return true;
    }
}
