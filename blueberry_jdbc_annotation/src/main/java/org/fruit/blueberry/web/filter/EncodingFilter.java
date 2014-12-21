package org.fruit.blueberry.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by AFei on 2014/7/30.
 */
public class EncodingFilter implements Filter {
    private String charset = "UTF-8";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        httpServletRequest.setCharacterEncoding(charset);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String temp = filterConfig.getInitParameter("charset");

        if (temp != null && !"".equals(temp)) {
            charset = temp;
        }
    }

    @Override
    public void destroy() {

    }
}
