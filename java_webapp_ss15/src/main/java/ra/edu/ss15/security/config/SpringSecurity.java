package ra.edu.ss15.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ra.edu.ss15.security.jwt.JWTAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SpringSecurity {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new JWTAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, JWTAuthFilter jwtAuthFilter) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Public APIs
                        .requestMatchers("/api/auth/**").permitAll()

                        // CUSTOMER APIs
                        .requestMatchers(HttpMethod.POST, "/api/orders").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/orders/my").hasRole("CUSTOMER")

                        // Shared: All roles can view products
                        .requestMatchers(HttpMethod.GET, "/api/products").permitAll()

                        // STAFF/ADMIN: manage products
                        .requestMatchers(HttpMethod.POST, "/api/products").hasAnyRole("STAFF", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/products/**").hasAnyRole("STAFF", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasAnyRole("STAFF", "ADMIN")

                        // STAFF/ADMIN: view all orders
                        .requestMatchers(HttpMethod.GET, "/api/orders").hasAnyRole("STAFF", "ADMIN")
                        // STAFF: update order status
                        .requestMatchers(HttpMethod.PUT, "/api/orders/**/status").hasRole("STAFF")

                        // ADMIN: change user role
                        .requestMatchers(HttpMethod.PUT, "/api/users/**/role").hasRole("ADMIN")

                        // All authenticated users: get own info
                        .requestMatchers(HttpMethod.GET, "/api/users/me").authenticated()

                        // Others
                        .anyRequest().authenticated()
                        .requestMatchers("/api/reviews/**", "/api/reports/**").authenticated()

                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint()))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
