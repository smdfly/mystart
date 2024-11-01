package cn.itcast.mp.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class Ipfilter implements Filter {

    String allowip="127.0.0.1";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        String remoteip=httpServletRequest.getRemoteAddr();
      //http请求报文获取
        String requestBody= httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        System.out.println("Ipfilter请求报文------------------------>"+requestBody);
        System.out.println("进入过滤器");
        if(allowip.equals(remoteip)){
            ( (HttpServletResponse)response).sendError(201,"ip不允许");
        }else {
            chain.doFilter(request,response);
        }
    }
}
