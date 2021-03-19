package it.engineering.spring.mvc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.engineering.spring.mvc.converter.impl.ContactPersonConverterDtoEntity;
import it.engineering.spring.mvc.dao.CityDao;
import it.engineering.spring.mvc.dao.ContactPersonDao;
import it.engineering.spring.mvc.dao.ManufacturerDao;
import it.engineering.spring.mvc.dto.ContactPersonDto;
import it.engineering.spring.mvc.entity.CityEntity;
import it.engineering.spring.mvc.entity.ContactPersonEntity;
import it.engineering.spring.mvc.entity.ManufacturerEntity;
import it.engineering.spring.mvc.exception.ExistEntityException;
import it.engineering.spring.mvc.service.ContactPersonService;

@Service("contactPersonServiceImpl")
@Transactional
public class ContactPersonServiceImpl implements ContactPersonService{
	private final ContactPersonDao contactPersonDao;
	private final ManufacturerDao manufacturerDao;
	private final ContactPersonConverterDtoEntity cpcde;
	
	@Autowired
	public ContactPersonServiceImpl(ContactPersonDao contactPersonDao, ManufacturerDao manufacturerDao, ContactPersonConverterDtoEntity cpcde) {
		this.contactPersonDao = contactPersonDao;
		this.manufacturerDao = manufacturerDao;
		this.cpcde = cpcde;
	}
	
	@Override
	public void save(ContactPersonDto contactPersonDto) throws Exception {
		ManufacturerEntity manufacturerEntity = manufacturerDao.findById(contactPersonDto.getManufacturerDto().getId());
		
		if (manufacturerEntity == null) throw new ExistEntityException(contactPersonDto, "Manufacturer dont exist");
	
		contactPersonDao.save(cpcde.toEntity(contactPersonDto));
	}

	@Override
	public List<ContactPersonDto> getAll() throws Exception {
		List<ContactPersonEntity> entities = contactPersonDao.getAll();
		
		return entities.stream().map(entity -> {
			return cpcde.toDto(entity);
		}).collect(Collectors.toList());
	}

	@Override
	public ContactPersonDto findById(Long id) throws Exception {
		ContactPersonEntity contactPersonEntity = contactPersonDao.findById(id);
		if (contactPersonEntity == null) throw new ExistEntityException(contactPersonEntity, "Contact person dont exist");
		return cpcde.toDto(contactPersonEntity);
	}

	@Override
	public ContactPersonDto update(ContactPersonDto contactPersonDto) throws Exception {
		ContactPersonEntity entity = contactPersonDao.findById(contactPersonDto.getId());
		if (entity == null) throw new ExistEntityException(contactPersonDto, "Contact Person does not exist.");
		
		ManufacturerEntity manufacturerEntity = manufacturerDao.findById(contactPersonDto.getManufacturerDto().getId());
		if (manufacturerEntity == null) throw new ExistEntityException(manufacturerEntity, "Manufacturer does not exist.");
		
		ContactPersonEntity contactPersonEntity = new ContactPersonEntity();
		contactPersonEntity.setId(contactPersonDto.getId());
		contactPersonEntity.setFirstname(contactPersonDto.getFirstname());
		contactPersonEntity.setLastname(contactPersonDto.getLastname());
		contactPersonEntity.setManufacturerEntity(manufacturerEntity);
		
		contactPersonEntity = contactPersonDao.update(contactPersonEntity);
		
		
		return cpcde.toDto(contactPersonEntity);
	}

}
