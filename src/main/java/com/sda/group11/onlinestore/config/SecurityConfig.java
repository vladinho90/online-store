package com.sda.group11.onlinestore.config;


//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    DataSource dataSource;
//
//    @Autowired
//    BCryptPasswordEncoder bCryptEncoder;
//
//    @Value("${spring.queries.users-query}")
//    private String usersQuery;
//
//    @Value("${spring.queries.roles-query}")
//    private String rolesQuery;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .usersByUsernameQuery(usersQuery)
//                .authoritiesByUsernameQuery(rolesQuery)
//                .dataSource(dataSource)
//                .passwordEncoder(bCryptEncoder);
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/api/products").hasRole("ADMIN")
//                .antMatchers("/api/category").hasRole("ADMIN")
//                .antMatchers("/", "/**").permitAll()
//                .and().formLogin();
//
//        http.headers().frameOptions().disable();
//
//        http.cors();
//        http.csrf().disable();
////        http.authorizeRequests()
////                .anyRequest()
////                .permitAll();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }
//
//
//}
