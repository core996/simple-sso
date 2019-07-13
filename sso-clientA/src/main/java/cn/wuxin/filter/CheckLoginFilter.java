package cn.wuxin.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: drp
 * @description: 检测是否登录
 * @author: Xin Wu
 * @create: 2019-07-13 13:21
 **/
@WebFilter("/pages/*")
public class CheckLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest ;
        // 登录用户的session中是否有指定属性
        Object user = request.getSession().getAttribute("user");
        if (user != null){      // 如果有属性则用户为登录过的。
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            //判断用户名是否通过登录中心传回来
            String username = request.getParameter("username");
            //如果没有信息则重定向到登录中心
            if (username == null || "null".equalsIgnoreCase(username)){
                HttpServletResponse response = (HttpServletResponse) servletResponse ;
                response.sendRedirect("http://www.ssoserver.com:8080/login.servlet?redirect_url="+request.getRequestURL().toString());
            }else {
                // 将登录中心的数据保存到本地session ，以便于下次使用。
                request.getSession().setAttribute("user","username");
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }
    @Override
    public void destroy() {

    }
}
