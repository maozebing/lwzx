package gsunis.bigdata.common.config;

import gsunis.bigdata.common.utility.EnumClass;
import gsunis.bigdata.common.utility.JWTUtil;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述 ：
 *
 * @author : maozebing
 * @version : v1.00
 * @CreationDate : 2017/1/6 20:55
 * @Description :
 * @update : 修改人，修改时间，修改内容
 * @see :[相关类/方法]
 */
@Component
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT,GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Cache-Control,Pragma,Content-Type,access_token,uid");
        response.setHeader("Access-Control-Allow-Credentials","true");
        String url = request.getRequestURI();
        String authorUrl = "/user/login";//可能需要读取配置文件
        //如果包含说明是认证的服务不需要过滤，直接通过
        if (url.contains(authorUrl)||url.contains("favicon.ico")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else { //如果不包含就需要进行安全认证后才能放行
            if (request.getMethod().equals("OPTIONS") || request.getMethod().equals("HEAD")) { //因为跨域默认需要放行OPTIONS和HEAD方法
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                String access_token = request.getHeader("access_token");
                //如果有access_token就进行token验证
                if(JWTUtil.verifyToken(access_token)){
                    filterChain.doFilter(servletRequest, servletResponse);
                }else{
                    response.setStatus(EnumClass.HttpStatusCode.未授权.getValue());
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
