package it.engineering.spring.mvc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.engineering.spring.mvc.converter.impl.ManufacturerConverterDtoEntity;
import it.engineering.spring.mvc.dao.CityDao;
import it.engineering.spring.mvc.dao.ManufacturerDao;
import it.engineering.spring.mvc.dto.CityDto;
import it.engineering.spring.mvc.dto.ManufacturerDto;
import it.engineering.spring.mvc.entity.CityEntity;
import it.engineering.spring.mvc.entity.ManufacturerEntity;
import it.engineering.spring.mvc.exception.ExistEntityException;
import it.engineering.spring.mvc.service.CityService;
import it.engineering.spring.mvc.service.ManufacturerService;


@Service("manfuacturerServiceImpl")
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService{
	private final ManufacturerDao manufacturerDao;
	private final CityDao cityDao;
	private final ManufacturerConverterDtoEntity manufacturerConverterDtoEntity;
	
	@Autowired
	public ManufacturerServiceImpl(ManufacturerDao manufacturerDao, CityDao cityDao, ManufacturerConverterDtoEntity manufacturerConverterDtoEntity) {
		this.manufacturerDao = manufacturerDao;
		this.cityDao = cityDao;
		this.manufacturerConverterDtoEntity = manufacturerConverterDtoEntity;
	}
	
	@Override
	public void save(ManufacturerDto manufacturerDto) throws Exception {
		// to do validacija
		// proveri da li postoji proizvodjac pa grad
		
		CityEntity existingCity = cityDao.findById(manufacturerDto.getCityDto().getNumber());
		
		if (existingCity == null) throw new ExistEntityException(manufacturerDto, "City dont exist");
		
//		ManufacturerEntity manufacturerEntity = new ManufacturerEntity();
//		manufacturerEntity.setName(manufacturerDto.getName());
//		manufacturerEntity.setCity(existingCity); refaktorisali smo odje dole
		
		manufacturerDao.save(manufacturerConverterDtoEntity.toEntity(manufacturerDto));
	}

	@Override
	public List<ManufacturerDto> getAll() throws Exception {
		List<ManufacturerEntity> entities = manufacturerDao.getAll();
		
		return entities.stream().map(entity -> {
			return manufacturerConverterDtoEntity.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public ManufacturerDto findById(Long number) throws Exception {
		ManufacturerEntity manufacturerEntity = manufacturerDao.findById(number);
		if (manufacturerEntity == null) throw new ExistEntityException(manufacturerEntity, "Manufacturer dont exist");
		return manufacturerConverterDtoEntity.toDto(manufacturerEntity);
	}

	@Override
	public ManufacturerDto update(ManufacturerDto manufacturerDto) throws Exception {
		ManufacturerEntity entity = manufacturerDao.findById(manufacturerDto.getId());
		if (entity == null) throw new ExistEntityException(manufacturerDto, "Manufacturer dont exist");
		
		CityEntity cityEntity = cityDao.findById(manufacturerDto.getCityDto().getNumber());
		if (cityEntity == null) throw new ExistEntityException(cityEntity, "City dont exist");
		
		
		ManufacturerEntity manufacturerEntity = new ManufacturerEntity();
		manufacturerEntity.setId(manufacturerDto.getId());
		manufacturerEntity.setName(manufacturerDto.getName());
		
		manufacturerEntity.setCity(cityEntity); // vezemo objekat iz perzist konteksta u ovaj grad
		
		// update
		manufacturerEntity = manufacturerDao.update(manufacturerEntity);
		
		// convert - entity to dto
		return manufacturerConverterDtoEntity.toDto(manufacturerEntity);
	}
	
}
