package it.engineering.spring.mvc.converter.impl;

import org.springframework.stereotype.Component;

import it.engineering.spring.mvc.converter.ConverterDtoEntity;
import it.engineering.spring.mvc.dto.CityDto;
import it.engineering.spring.mvc.entity.CityEntity;

@Component // ubaci paket u komponent sken
public class CityConverterDtoEntity implements ConverterDtoEntity<CityDto, CityEntity>{ // bilo koja 2 objekta

	@Override
	public CityDto toDto(CityEntity e) {
		return new CityDto(e.getNumber(), e.getName());
	}

	@Override
	public CityEntity toEntity(CityDto dto) {
		return new CityEntity(dto.getNumber(), dto.getName());
	}

}
