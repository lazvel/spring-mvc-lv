package it.engineering.spring.mvc.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import it.engineering.spring.mvc.formatter.CityDtoFormatter;
import it.engineering.spring.mvc.service.CityService;
import it.engineering.spring.mvc.service.ContactPersonService;


@Configuration
@ComponentScan(basePackages = {
		"it.engineering.spring.mvc.controller"
})
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer{
	private CityService cityService;
	
	@Autowired
	public MvcConfig(CityService cityService) {
		this.cityService = cityService;
	}
	
	// TILES FROM 18.03 SPRING09
	@Bean
	public ViewResolver tilesViewResolver() {
		System.out.println("===================== tilesViewResolver");
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setOrder(0);
		return tilesViewResolver;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		// gde se nalazi definicija pogleda koji se vracju korisniku
		tilesConfigurer.setDefinitions(new String[] {
				"/WEB-INF/views/tiles/tiles.xml"
		}); // lokacije gde se nalaze
		
		return tilesConfigurer;
	}
	
	@Bean
	public ViewResolver jspViewResolver() {
		System.out.println("=========================== jspViewResolver =====================");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(1);
		return resolver;
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		//WebMvcConfigurer.super.addFormatters(registry);
		System.out.println("================== public void addFormatters(FormatterRegistry registry) ========================");
		registry.addFormatter(new CityDtoFormatter(cityService));
	}
}
