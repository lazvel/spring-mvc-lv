package it.engineering.spring.mvc.dao;

import java.util.List;

import it.engineering.spring.mvc.entity.CityEntity;
import it.engineering.spring.mvc.entity.ManufacturerEntity;

public interface ManufacturerDao {
	void save(ManufacturerEntity manufacturerEntity) throws Exception;
	List<ManufacturerEntity> getAll() throws Exception;
	ManufacturerEntity findById(Long id) throws Exception;
	ManufacturerEntity update(ManufacturerEntity manufacturerEntity) throws Exception;
}
