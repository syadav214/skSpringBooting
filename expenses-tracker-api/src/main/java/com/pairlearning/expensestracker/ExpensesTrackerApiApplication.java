package com.pairlearning.expensestracker;

import com.pairlearning.expensestracker.filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.FilterRegistration;
import java.util.Arrays;

@SpringBootApplication
public class ExpensesTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensesTrackerApiApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		//config.addAllowedOrigin("http://localhost:3002");
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		source.registerCorsConfiguration("/**", config);
		registrationBean.setFilter(new CorsFilter(source));
		registrationBean.setOrder(0);
		return registrationBean;
	}

	/*@Bean
	public CorsFilter corsFilter2() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.setAllowedMethods(Arrays.asList("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}*/

	@Bean
	public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		// login and register can be access by any one
		// other endpoint need to authenticated such as categories
		registrationBean.addUrlPatterns("/api/categories/*");
		return  registrationBean;
	}

}
