package subbotin.spring.microservices.testassignment.upday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${api.home}")
    private String apiHome;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("secret").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, apiHome).permitAll()
                .antMatchers(HttpMethod.GET, apiHome + "/*").permitAll()
                .antMatchers(HttpMethod.POST, apiHome).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, apiHome).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .csrf().disable();
    }
}
