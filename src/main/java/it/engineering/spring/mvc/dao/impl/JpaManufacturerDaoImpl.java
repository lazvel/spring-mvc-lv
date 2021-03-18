package it.engineering.spring.mvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.spring.mvc.dao.CityDao;
import it.engineering.spring.mvc.dao.ManufacturerDao;
import it.engineering.spring.mvc.entity.CityEntity;
import it.engineering.spring.mvc.entity.ManufacturerEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY) // zahtevamo da se ove metode pozivaju iz servisa
public class JpaManufacturerDaoImpl implements ManufacturerDao{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(ManufacturerEntity manufacturerEntity) throws Exception {
		entityManager.persist(manufacturerEntity);
	}

	@Override
	public List<ManufacturerEntity> getAll() throws Exception {
		String query = "SELECT m FROM ManufacturerEntity m ORDER BY m.name";
		return entityManager.createQuery(query, ManufacturerEntity.class).getResultList();
	}

	@Override
	public ManufacturerEntity findById(Long id) throws Exception {
		return entityManager.find(ManufacturerEntity.class, id);
	}

	@Override
	public ManufacturerEntity update(ManufacturerEntity manufacturerEntity) throws Exception {
		return entityManager.merge(manufacturerEntity);
	}

}
