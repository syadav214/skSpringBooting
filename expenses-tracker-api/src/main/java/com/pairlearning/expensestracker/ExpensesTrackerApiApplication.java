package com.pairlearning.expensestracker;

import com.pairlearning.expensestracker.filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.FilterRegistration;

@SpringBootApplication
public class ExpensesTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpensesTrackerApiApplication.class, args);
	}

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
