package com.sda.group11.onlinestore.config;

import com.sda.group11.onlinestore.jwt.JwtAuthorizationFilter;
import com.sda.group11.onlinestore.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //cross-origin resource  because we owrks with diffrenre ports
        http.cors().and()
                .authorizeRequests()
                //These are public pages
                .antMatchers("/resources/**", "/error", "/api/user/**", "/api/products/**","/api/category/**").permitAll()
                //These can be rechable for just have admin role
                .antMatchers("/admin/**").hasRole("ADMIN")
                //all remaning paths should need authentication
                .anyRequest().fullyAuthenticated()
                .and()
                //logout will log the user out by invalidate session
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/user/logout", "POST"))
                .and()
                //login form and path
                .formLogin().loginPage("/api/user/login")
                .and()
                //enable basic authetication. Http header: basis username:password
                .httpBasic()
                .and()
                //cross side request forgery
                .csrf().disable();

//        http.cors();
//        http.csrf().disable();
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll();
        http.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtTokenProvider));
    }

    //define user detail service
    //how the user details are keept in the application (database, elda, or in memory)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    //Cross origin resource sharing
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }
}
