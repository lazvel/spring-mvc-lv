package it.engineering.spring.mvc.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import it.engineering.spring.mvc.dto.CityDto;
import it.engineering.spring.mvc.service.CityService;

public class CityDtoFormatter implements Formatter<CityDto>{
	private CityService cityService;
	
	@Autowired
	public CityDtoFormatter(CityService cityService) {
		this.cityService = cityService;
		System.out.println("KREIRAN JE CityDtoFormatter");
	}
	
	@Override
	public String print(CityDto cityDto, Locale locale) {
		return cityDto.getNumber() + " - " + cityDto.getName();
	}

	@Override
	public CityDto parse(String city, Locale locale) throws ParseException {
		System.out.println("============== CityDtoFormatte::parse ============== ");
		System.out.println("String city: " + city);
		Long number = Long.parseLong(city);
		try {
			CityDto cityDto = cityService.findById(number);
			return cityDto;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ParseException("Greska u formatteru...", 0);
		}
	}

}
