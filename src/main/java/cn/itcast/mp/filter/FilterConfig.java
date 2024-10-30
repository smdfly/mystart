package cn.itcast.mp.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.servlet.FilterRegistration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Ipfilter> ipfilterFilterRegistration(){
         FilterRegistrationBean<Ipfilter> registrationBean=new FilterRegistrationBean<Ipfilter>();
          registrationBean.setFilter(new Ipfilter());
          registrationBean.addUrlPatterns("/*");
          registrationBean.setName("Ipfilter");
          registrationBean.setOrder(1);//设置优先级，默认优先级最低，数字越小优先级越低 为int类型

        return registrationBean;
    }
}
