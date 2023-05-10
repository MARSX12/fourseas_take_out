package com.hy.fourseas.filter;


import com.alibaba.fastjson.JSON;
import com.hy.fourseas.common.BaseContext;
import com.hy.fourseas.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        log.info("拦截到请求：" + request.getRequestURI());
        String uri = request.getRequestURI();
        String[] urls = new String[]{
                "/employee/login", "/employee/logout", "/backend/**",
                "/front/**","/common/**","/user/sendMsg","/user/login"
        };

        if (check(urls, uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        //后台员工登录验证
        if (request.getSession().getAttribute("employee") != null) {
//            log.info("用户已登录，用户ID为：{}",request.getSession().getAttribute("employee"));

            Long empid = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentID(empid);

            filterChain.doFilter(request, response);
            return;
        }

        //移动端用户登录验证
        if (request.getSession().getAttribute("user") != null) {
//            log.info("用户已登录，用户ID为：{}",request.getSession().getAttribute("user"));

            Long userid = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentID(userid);

            filterChain.doFilter(request, response);
            return;
        }

//        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;


    }


    public boolean check(String[] urls, String uri) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url,uri);
            if (match){
                return true;
            }
        }

        return false;
    }

}
