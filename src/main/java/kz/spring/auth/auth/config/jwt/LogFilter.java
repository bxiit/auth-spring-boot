package kz.spring.auth.auth.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("loggingFilter")
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("A REQUEST IS GOING DOWN THROUGH FILTER CHAIN");
        chain.doFilter(request, response);
        System.out.println("A REQUEST IS GOING UP THROUGH FILTER CHAIN");
    }
}
