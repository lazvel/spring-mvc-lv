package it.engineering.spring.mvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.engineering.spring.mvc.dto.ManufacturerDto;

public class ManufacturerValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ManufacturerDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("Validate method in public class ManufacturerValidator");
		ManufacturerDto manufacturerDto = (ManufacturerDto) target;
		if (manufacturerDto != null) {
			if (manufacturerDto.getName().isEmpty()) {
				errors.rejectValue("name", "manufacturer.name", "Name is required.");
			}
			if (manufacturerDto.getCityDto() == null) {
				errors.rejectValue("cityDto", "manufacturer.city", "City is required.");
			}
		}
	}

}
