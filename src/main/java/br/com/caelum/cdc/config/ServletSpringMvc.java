package br.com.caelum.cdc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMvc extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		//sempre adiciona as classes de coniguração para sinalizar ao spring
		return new Class[]{AppWebConfiguration.class, JPAConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

}