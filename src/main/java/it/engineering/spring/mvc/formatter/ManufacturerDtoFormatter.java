package it.engineering.spring.mvc.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import it.engineering.spring.mvc.dto.CityDto;
import it.engineering.spring.mvc.dto.ManufacturerDto;
import it.engineering.spring.mvc.service.CityService;
import it.engineering.spring.mvc.service.ManufacturerService;
import it.engineering.spring.mvc.service.impl.ManufacturerServiceImpl;

public class ManufacturerDtoFormatter implements Formatter<ManufacturerDto>{
	private ManufacturerService manufacturerService;
	
	@Autowired
	public ManufacturerDtoFormatter(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
		System.out.println("KREIRAN JE ManufacturerDtoFormatter");
	}
	
	@Override
	public String print(ManufacturerDto manufacturerDto, Locale locale) {
		return manufacturerDto.getId() + " - " + manufacturerDto.getName();
	}

	@Override
	public ManufacturerDto parse(String manufacturer, Locale locale) throws ParseException {
		System.out.println("============== ManufacturerDtoFormatter::parse ============== ");
		System.out.println("String manufacturer: " + manufacturer);
		Long id = Long.parseLong(manufacturer);
		try {
			ManufacturerDto manufacturerDto = manufacturerService.findById(id);
			return manufacturerDto;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ParseException("Greska u formatteru...", 0);
		}
	}

}
