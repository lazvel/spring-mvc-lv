package it.engineering.spring.mvc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.engineering.spring.mvc.dao.ContactPersonDao;
import it.engineering.spring.mvc.entity.ContactPersonEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY) // zahtevamo da se ove metode pozivaju iz servisa
public class JpaContactPersonDaoImpl implements ContactPersonDao{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(ContactPersonEntity contactPersonEntity) throws Exception {
		entityManager.persist(contactPersonEntity);
	}

	@Override
	public List<ContactPersonEntity> getAll() throws Exception {
		String query = "SELECT p FROM ContactPersonEntity p ORDER BY p.firstname";
		return entityManager.createQuery(query, ContactPersonEntity.class).getResultList();
	}

	@Override
	public ContactPersonEntity findById(Long id) throws Exception {
		return entityManager.find(ContactPersonEntity.class, id);
	}

	@Override
	public ContactPersonEntity update(ContactPersonEntity contactPersonEntity) throws Exception {
		return entityManager.merge(contactPersonEntity);
	}

}
