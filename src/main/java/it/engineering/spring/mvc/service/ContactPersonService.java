package it.engineering.spring.mvc.service;

import java.util.List;

import it.engineering.spring.mvc.dto.CityDto;
import it.engineering.spring.mvc.dto.ContactPersonDto;
import it.engineering.spring.mvc.dto.ManufacturerDto;

public interface ContactPersonService {
	void save(ContactPersonDto contactPersonDto) throws Exception;
	List<ContactPersonDto> getAll() throws Exception;
	ContactPersonDto findById(Long id) throws Exception;
	ContactPersonDto update(ContactPersonDto contactPersonDto) throws Exception;
}
