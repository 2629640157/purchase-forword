package com.ydgk.eight.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author 游斌
 * @create 2020-02-29  15:23
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {
    private String encoding = null;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
      /*  HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        //2.设置请求的编码，解决请求参数的中文乱码问题
        request.setCharacterEncoding("UTF-8");
        //3.设置响应的编码，解决响应的乱码问题
        response.setContentType("text/html;charset=UTF-8");
        chain.doFilter(request, response);*/
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
