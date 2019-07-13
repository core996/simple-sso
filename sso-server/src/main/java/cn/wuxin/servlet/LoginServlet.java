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
        String redirect_url = req.getParameter("redirect_url");
        String username = req.getParameter("username") ;
        if (req.getSession().getAttribute("user")!=null ){    //已经登录过
            username = (String) req.getSession().getAttribute("user") ;
            resp.sendRedirect(redirect_url+"?username="+username); //返回标志位
        }else {
            if ("wuxin".equalsIgnoreCase(username)){     //登录成功
                req.getSession().setAttribute("user",username);
                resp.sendRedirect(redirect_url+"?username="+username); //返回标志位
            }else {
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
