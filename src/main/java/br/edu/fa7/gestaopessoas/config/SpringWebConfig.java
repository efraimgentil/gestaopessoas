//package br.edu.fa7.gestaopessoas.config;
//
//import org.springframework.context.annotation.*;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//
//import java.util.List;
//
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = { "br.com.efraimgentil.controllers"  })
//@EnableGlobalMethodSecurity(securedEnabled = true , jsr250Enabled = true, proxyTargetClass = true)//Needs to be here or will not work for the controllers
//public class SpringWebConfig extends WebMvcConfigurerAdapter {
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/resources/**")
//            .addResourceLocations("/resources/");
//	}
//
//	@Bean
//	public InternalResourceViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver  = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//		return viewResolver;
//	}
//
//	@Bean
//	MappingJackson2HttpMessageConverter converter() {
//		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//		return converter;
//	}
//
//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		MappingJackson2HttpMessageConverter converter = converter();
//		//converter.setObjectMapper();/*
//		//new ObjectMapper().set*/
//		super.configureMessageConverters(converters);
//	}
//}
