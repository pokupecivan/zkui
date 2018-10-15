package com.deem.zkui.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "filterhttps", urlPatterns = "/*")
public class HttpsRenamingFilter implements Filter {
    private FilterConfig conf;

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        if (req.getScheme().equals("http")) {
            String url = "https://" + req.getServerName()
                    + req.getContextPath() + req.getServletPath();
            if (req.getPathInfo() != null) {
                url += req.getPathInfo();
            }
            resp.sendRedirect(url);
        } else {
            chain.doFilter(request, response);
        }
    }

    public FilterConfig getFilterConfig() {
        return conf;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        conf = filterConfig;
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        conf = filterConfig;
    }
}