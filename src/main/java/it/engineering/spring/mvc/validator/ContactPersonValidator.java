package it.engineering.spring.mvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.engineering.spring.mvc.dto.ContactPersonDto;
import it.engineering.spring.mvc.dto.ManufacturerDto;

public class ContactPersonValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ContactPersonDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("Validate method in public class ContactPersonValidator");
		ContactPersonDto contactPersonDto = (ContactPersonDto) target;
		if (contactPersonDto != null) {
			if (contactPersonDto.getFirstname().isEmpty()) {
				errors.rejectValue("firstname", "contactPerson.firstname", "Firstname is required.");
			} else if (contactPersonDto.getLastname().isEmpty()) {
				errors.rejectValue("firstname", "contactPerson.lastname", "Lastname is required.");
			}
			if (contactPersonDto.getManufacturerDto() == null) {
				errors.rejectValue("manufacturerDto", "contactPerson.manufacturer", "Manufacturer is required.");
			}
		}
	}

}
