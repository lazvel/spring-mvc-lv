package it.engineering.spring.mvc.service;

import java.util.List;

import it.engineering.spring.mvc.dto.CityDto;
import it.engineering.spring.mvc.dto.ManufacturerDto;

public interface ManufacturerService {
	void save(ManufacturerDto manufacturerDto) throws Exception;
	List<ManufacturerDto> getAll() throws Exception;
	ManufacturerDto findById(Long id) throws Exception;
	ManufacturerDto update(ManufacturerDto manufacturerDto) throws Exception;
}
