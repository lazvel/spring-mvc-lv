package it.engineering.spring.mvc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.engineering.spring.mvc.converter.impl.CityConverterDtoEntity;
import it.engineering.spring.mvc.dao.CityDao;
import it.engineering.spring.mvc.dto.CityDto;
import it.engineering.spring.mvc.entity.CityEntity;
import it.engineering.spring.mvc.exception.ExistEntityException;
import it.engineering.spring.mvc.service.CityService;


@Service("cityServiceImpl")
@Transactional
public class CityServiceImpl implements CityService{
	private final CityDao cityDao;
	private final CityConverterDtoEntity cityConverterDtoEntity;
	
	@Autowired
	public CityServiceImpl(CityDao cityDao, CityConverterDtoEntity cityConverterDtoEntity) {
		this.cityDao = cityDao;
		this.cityConverterDtoEntity = cityConverterDtoEntity;
	}
	
	@Override
	public void save(CityDto cityDto) throws Exception {
		// to do validacija
		// proveri da li postoji
		CityEntity existingCity = cityDao.findById(cityDto.getNumber());
		if (existingCity != null) {
			throw new ExistEntityException(cityDto, "City with code number: " + cityDto.getNumber() + " already exists");
		}
		
		cityDao.save(cityConverterDtoEntity.toEntity(cityDto));
	}

	@Override
	public List<CityDto> getAll() throws Exception {
		List<CityEntity> entities = cityDao.getAll();
		
		return entities.stream().map(entity -> {
			return cityConverterDtoEntity.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public CityDto findById(Long number) throws Exception {
		CityEntity cityEntity = cityDao.findById(number);
		return cityConverterDtoEntity.toDto(cityEntity);
	}
	
}
