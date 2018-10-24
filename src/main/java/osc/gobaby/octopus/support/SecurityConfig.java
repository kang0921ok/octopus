package osc.gobaby.octopus.support;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/css/**", "/resources/js/**", "/resources/image/**", "/",
                        "/user/**", "/api/**/user/login", "/api/**/user/join", "/api/**/logstash").permitAll()
                .antMatchers("/dashboard/**").hasRole("USER")
                .antMatchers("/api/v1.0/admin/server/druid/broker").hasRole("USER")
                .antMatchers("/api/**/admin/**", "/dashboard/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().invalidSessionUrl("/user/login")
                .and()
                .cors()
                .and()
                .exceptionHandling().accessDeniedPage("/login")
                .and()
                .logout().logoutUrl("/user/logout").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }
}
