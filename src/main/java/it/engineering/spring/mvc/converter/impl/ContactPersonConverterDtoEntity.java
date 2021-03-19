package it.engineering.spring.mvc.converter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.engineering.spring.mvc.converter.ConverterDtoEntity;
import it.engineering.spring.mvc.dto.ContactPersonDto;
import it.engineering.spring.mvc.entity.ContactPersonEntity;

@Component
public class ContactPersonConverterDtoEntity implements ConverterDtoEntity<ContactPersonDto, ContactPersonEntity>{
	private ManufacturerConverterDtoEntity manufacturerConverterDtoEntity;
	
	@Autowired
	public ContactPersonConverterDtoEntity(ManufacturerConverterDtoEntity manufacturerConverterDtoEntity) {
		this.manufacturerConverterDtoEntity = manufacturerConverterDtoEntity;
	}
	
	@Override
	public ContactPersonDto toDto(ContactPersonEntity e) {
		return new ContactPersonDto(e.getId(), e.getFirstname(), e.getLastname(), manufacturerConverterDtoEntity.toDto(e.getManufacturerEntity()));
	}

	@Override
	public ContactPersonEntity toEntity(ContactPersonDto dto) {
		return new ContactPersonEntity(dto.getId(), dto.getFirstname(), dto.getLastname(), manufacturerConverterDtoEntity.toEntity(dto.getManufacturerDto()));
	}

}
