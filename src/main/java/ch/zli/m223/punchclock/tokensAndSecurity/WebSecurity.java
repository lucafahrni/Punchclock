package ch.zli.m223.punchclock.tokensAndSecurity;

import ch.zli.m223.punchclock.domain.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static ch.zli.m223.punchclock.tokensAndSecurity.SecurityConstants.*;

/**
 * @author Luca Fahrni
 * @version 25.11.2020
 * @Class WebSecurity
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, GET_INDEX_HTML_URL).permitAll()
                .antMatchers(HttpMethod.GET, GET_ENTRY).permitAll()
                .antMatchers(HttpMethod.GET, GET_INDEX_JS).permitAll()
                .antMatchers(HttpMethod.GET, GET_HOMEPAGE_HTML).permitAll()
                .antMatchers(HttpMethod.GET, GET_HOMEPAGE_JS).permitAll()
                .antMatchers(HttpMethod.POST, GET_LOGIN_URL).permitAll()
                .antMatchers(HttpMethod.GET, GET_REGISTER_HTML).permitAll()
                .antMatchers(HttpMethod.GET, GET_REGISTER_JS).permitAll()
                .antMatchers(HttpMethod.GET, GET_USER_HTML).permitAll()
                .antMatchers(HttpMethod.GET, GET_USER_JS).permitAll()
                .antMatchers(HttpMethod.GET, GET_CATEGORY_HTML).permitAll()
                .antMatchers(HttpMethod.GET, GET_CATEGORY_JS).permitAll()
                .antMatchers(HttpMethod.DELETE, SecurityConstants.GET_ENTRY+"/*").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()

                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}