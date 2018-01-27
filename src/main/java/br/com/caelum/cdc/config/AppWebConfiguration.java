package br.com.caelum.cdc.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.caelum.cdc.controller.HomeController;
import br.com.caelum.cdc.dao.ProductDao;
import br.com.caelum.cdc.infra.FileSaver;
import br.com.caelum.cdc.models.ShoppingCart;
import br.com.caelum.cdc.viewresolver.JsonViewResolver;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProductDao.class, FileSaver.class, ShoppingCart.class})
@EnableCaching
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		//scope								
		resolver.setExposedContextBeanNames("shoppingCart");
		return resolver;
	}
	
	//método para disponibilizar as mensagens no properties
	
	@Bean(name="messageSource")
	public MessageSource loudSource(){
		
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setBasename("/WEB-INF/messages");//caminho para encontrar as mensagens
		reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");//alterar o encoding das mensagens
		reloadableResourceBundleMessageSource.setCacheSeconds(1);
		return reloadableResourceBundleMessageSource;
	}
	
	@Bean
	public FormattingConversionService mvcConversionService(){
		DefaultFormattingConversionService defaultFormattingConversionService = new DefaultFormattingConversionService(true);
		
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(defaultFormattingConversionService);
		return defaultFormattingConversionService;
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		System.out.println("Instanciando o multirpart ...");
		return new StandardServletMultipartResolver();
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public CacheManager cacheManager(){
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder().maximumSize(25).expireAfterAccess(5, TimeUnit.SECONDS);
		GuavaCacheManager guavaCacheManager = new GuavaCacheManager();
		guavaCacheManager.setCacheBuilder(builder);
		return guavaCacheManager;
	}//habilitar cache com Guava
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> resolvers =  new ArrayList<ViewResolver>();
		resolvers.add(internalResourceViewResolver());
		resolvers.add(new JsonViewResolver());
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
		return resolver;
	}
}
