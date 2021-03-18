package it.engineering.spring.mvc.dao;

import java.util.List;

import it.engineering.spring.mvc.entity.CityEntity;
import it.engineering.spring.mvc.entity.ContactPersonEntity;
import it.engineering.spring.mvc.entity.ManufacturerEntity;

public interface ContactPersonDao {
	void save(ContactPersonEntity contactPersonEntity) throws Exception;
	List<ContactPersonEntity> getAll() throws Exception;
	ContactPersonEntity findById(Long id) throws Exception;
	ContactPersonEntity update(ContactPersonEntity contactPersonEntity) throws Exception;
}
