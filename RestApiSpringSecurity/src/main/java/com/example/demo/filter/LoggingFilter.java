package com.example.demo.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class LoggingFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		logger.info("🔍 [LOGGING FILTER]");
		logger.info("Request URI: {}", httpRequest.getRequestURI());
		logger.info("Method: {}", httpRequest.getMethod());
		logger.info("Remote Addr: {}", httpRequest.getRemoteAddr());

		// log headers needed
		httpRequest.getHeaderNames().asIterator()
				.forEachRemaining(header -> logger.info("{}: {}", header, httpRequest.getHeader(header)));

		// Continue the filter chain
		chain.doFilter(request, response);

	}

}
