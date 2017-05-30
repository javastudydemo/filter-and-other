package com.ijiangtao.javastudydemo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogFilter implements Filter {

	// FilterConfig可以用于访问Filer的配置信息
	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//-------------------------------------------------------------------------
		String encoding = filterConfig.getInitParameter("Encoding");
		request.setCharacterEncoding(encoding);

		//-------------------------------------------------------------------------
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		/*
		 * Returns the current HttpSession associated with this request or, if
		 * there is no current session and create is true, returns a new
		 * session.
		 * If create is false and the request has no valid HttpSession, this
		 * method returns null.
		 * 
		 */
		HttpSession session=httpServletRequest.getSession(true);
		String LoginPage = filterConfig.getInitParameter("LoginPage");
		session.setAttribute("UserName", "default");
		if (session.getAttribute("UserName")==null&&!httpServletRequest.getServletPath().endsWith(LoginPage)) {
			request.setAttribute("tip", "您还没有登录");
			request.getRequestDispatcher(LoginPage).forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		this.filterConfig = null;
	}

}
