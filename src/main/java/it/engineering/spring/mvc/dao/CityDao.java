package it.engineering.spring.mvc.dao;

import java.util.List;

import it.engineering.spring.mvc.entity.CityEntity;

public interface CityDao {
	void save(CityEntity cityEntity) throws Exception;
	List<CityEntity> getAll() throws Exception;
	CityEntity findById(Long number) throws Exception;
}
