package com.ydgk.forword.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 游斌
 * @create 2020-02-21  21:59
 */
public class EncodingFilter2 implements Filter {
    private String encoding;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }

}
