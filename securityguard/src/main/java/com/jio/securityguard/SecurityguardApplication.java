package com.jio.securityguard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class SecurityguardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityguardApplication.class, args);
	}

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
	    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
	    loggingFilter.setIncludeClientInfo(true);
	    loggingFilter.setIncludeQueryString(true);
	    loggingFilter.setIncludePayload(true);
	    loggingFilter.setIncludeHeaders(true);
	    loggingFilter.setMaxPayloadLength(10000);
	    return loggingFilter;
	}
}
