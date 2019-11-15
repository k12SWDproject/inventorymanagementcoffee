package swd.SWDProject.config.sercurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SercurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/" +
                        "",
                "/webjars/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                //.antMatchers(HttpMethod.POST, "**").permitAll()
                .antMatchers("**/swagger-resources/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**","/login").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
//                .antMatchers(HttpMethod.PUT, "/api/**").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/api/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTGenerator(authenticationManager()))
                .addFilter(new JWTVerifier(authenticationManager()))
                // disable session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


}

