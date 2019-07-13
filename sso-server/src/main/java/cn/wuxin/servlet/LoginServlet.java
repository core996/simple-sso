package cn.wuxin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 登录中心核心处理类
 * @author: Xin Wu
 * @create: 2019-07-13 13:04
 **/
@WebServlet("/login.servlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获得重定向的路径
        String redirect_url = req.getParameter("redirect_url");
        // 用户输入的信息
        String username = req.getParameter("username") ;
        // 如果对应的用户已经登录过了
        if (req.getSession().getAttribute("user")!=null ){    //已经登录过
            //将存在session中的用户信息取出来
            username = (String) req.getSession().getAttribute("user") ;
            //将用户信息返回给指定的跳转地址
            resp.sendRedirect(redirect_url+"?username="+username); //返回标志位
        }else {
            //如果是新用户则进行密码验证
            if ("wuxin".equalsIgnoreCase(username)){     //登录成功
                //登录成功将信息保存在session中
                req.getSession().setAttribute("user",username);
                //将地址重定向回指定系统
                resp.sendRedirect(redirect_url+"?username="+username); //返回标志位
            }else {
                //登录失败
                req.setAttribute("error","failed login");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
