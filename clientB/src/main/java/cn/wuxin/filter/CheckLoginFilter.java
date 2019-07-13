package cn.wuxin.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
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
        Object user = request.getSession().getAttribute("user");
        if (user != null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            String username = request.getParameter("username");
            if (username == null || "null".equalsIgnoreCase(username)){
                HttpServletResponse response = (HttpServletResponse) servletResponse ;
                response.sendRedirect("http://www.ssoserver.com:8080/login.servlet?redirect_url="+request.getRequestURL().toString());
            }else {
                request.getSession().setAttribute("user","username");
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }
    @Override
    public void destroy() {

    }
}
