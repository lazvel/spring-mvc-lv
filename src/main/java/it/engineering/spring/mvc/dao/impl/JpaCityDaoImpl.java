package it.engineering.spring.mvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.spring.mvc.dao.CityDao;
import it.engineering.spring.mvc.entity.CityEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY) // zahtevamo da se ove metode pozivaju iz servisa
public class JpaCityDaoImpl implements CityDao{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(CityEntity cityEntity) throws Exception {
		entityManager.persist(cityEntity);
	}

	@Override
	public List<CityEntity> getAll() throws Exception {
		String query = "SELECT c FROM CityEntity c ORDER BY c.name";
		return entityManager.createQuery(query, CityEntity.class).getResultList();
	}

	@Override
	public CityEntity findById(Long number) throws Exception {
		return entityManager.find(CityEntity.class, number);
	}

}
