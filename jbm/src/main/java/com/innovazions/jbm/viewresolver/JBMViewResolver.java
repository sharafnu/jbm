package com.innovazions.jbm.viewresolver;

import java.util.Locale;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * 
 * @author Sharaf
 * 
 */
public class JBMViewResolver implements ViewResolver {

	private ViewResolver webViewResolver;

	private ViewResolver mobileAndroidWebViewResolver;

	@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		String headerParamValue = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest().getHeader("User-Agent");

		if (headerParamValue.contains("Android")) {
			return mobileAndroidWebViewResolver.resolveViewName(viewName,
					locale);
		}
		return webViewResolver.resolveViewName(viewName, locale);
	}

	public ViewResolver getWebViewResolver() {
		return webViewResolver;
	}

	public void setWebViewResolver(ViewResolver webViewResolver) {
		this.webViewResolver = webViewResolver;
	}

	public ViewResolver getMobileAndroidWebViewResolver() {
		return mobileAndroidWebViewResolver;
	}

	public void setMobileAndroidWebViewResolver(
			ViewResolver mobileAndroidWebViewResolver) {
		this.mobileAndroidWebViewResolver = mobileAndroidWebViewResolver;
	}

}
