package xupt.se.ttms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 解决跨域问题
@WebFilter("/*")
public class Filter1Cors implements Filter
{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException
    {
        System.out.println("------------>进入CorsFilter");
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        HttpServletRequest request=(HttpServletRequest) servletRequest;
         System.out.println("------------>sessionid : " + request.getRequestedSessionId());

        String origin=request.getHeader("Origin");
        if(origin != null)
        {
            // 允许的跨域的域名
            response.setHeader("Access-Control-Allow-Origin", origin);
            // 允许跨域的方法
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PATCH, PUT, HEAD");
            // 缓存时间，单位是秒。-1表示禁用
            response.setHeader("Access-Control-Max-Age", "3600");
            // 允许跨域请求携带的请求头
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
            // 允许携带 cookies 等认证信息
            response.setHeader("Access-Control-Allow-Credentials", "true");
            // 设置输出编码为UTF-8
            response.setHeader("content-type", "text/html;charset=UTF-8");
            String method=request.getMethod();
            // 跨域预检请求，直接返回
            if(method.equalsIgnoreCase("OPTIONS"))
                return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy()
    {}
}