package it.engineering.spring.mvc.converter;

import it.engineering.spring.mvc.dto.Dto;
import it.engineering.spring.mvc.dto.Entity;

public interface ConverterDtoEntity<DTO extends Dto, E extends Entity> {
	public DTO toDto(E e);
	public E toEntity(DTO dto);
}
