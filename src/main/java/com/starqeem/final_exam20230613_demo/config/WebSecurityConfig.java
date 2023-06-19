package com.starqeem.final_exam20230613_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Date: 2023/5/17 13:02
 * @author: Qeem
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("admin").password("{noop}admin").roles("admin").build());//管理员用户
        userDetailsManager.createUser(User.withUsername("zhangsan").password("{noop}123456").roles("common").build());//普通用户
        userDetailsManager.createUser(User.withUsername("lisi").password("{noop}123456").roles("vip").build());//VIP用户
        return userDetailsManager;
    }
    //自定义AuthenticationManager 推荐
    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        System.out.println("自定义AuthenticationManager" + builder);
        builder.userDetailsService(userDetailsService());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .mvcMatchers("/admin").permitAll()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/images/**").permitAll()
                .mvcMatchers("/css/**").permitAll()
                .mvcMatchers("/film/common/**").hasAnyRole("common","vip","admin")//此路径放行拥有common、vip。admin权限的用户
                .mvcMatchers("/film/vip/**").hasAnyRole("vip","admin")//此路径放行vip、admin权限的用户
                .mvcMatchers("/admin/**").hasRole("admin")//放行资源写在前面  此路径放行admin权限的用户
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/login")//用来指定默认的登录页面,注意:一旦自定义登录页面以后必须只能登录url
                .loginProcessingUrl("/login")  //指定处理login请求url
//                .usernameParameter("uname")  //修改默认的用户名名称
//                .passwordParameter("passwd")  //修改默认的密码
//                .successForwardUrl("/hello") //认证成功之后跳转到的页面(forword跳转路径,始终在认证成功后跳转到指定路径)
                .defaultSuccessUrl("/index",true) //认证成功之后跳转的页面(redirect 之后跳转  根据上一次保存请求进行成功跳转)(特性可以改,通过第二个参数设置true或false,默认是false)
//                .successForwardUrl("/admin")
//                .successHandler() //认证成功的处理(前后端分离)
//                .failureForwardUrl("/login")  //认证失败之后,forword跳转
                .failureUrl("/login")  //认证失败之后  redirect跳转
                .and()
                .csrf().disable(); //禁止  csrf跨域请求保护
    }
}
