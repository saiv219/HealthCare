package com.crud.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crud.entity.Enrollees;

@Transactional
@Repository
public class EnrolleesDAO implements IEnrolleesDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Enrollees> getEnrolleesList() {

		String hql = "FROM Enrollees as atcl ORDER BY atcl.id";
		return (List<Enrollees>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public Enrollees getEnrollees(int enrolleesId) {
		return entityManager.find(Enrollees.class, enrolleesId);
	}

	@Override
	public Enrollees createEnrollees(Enrollees enrollees) {
		entityManager.persist(enrollees);
		String hql = "from Enrollees order by id DESC";
		Query query = entityManager.createQuery(hql);
		query.setMaxResults(1);
		Enrollees e = (Enrollees)query.getSingleResult();
		return e;
	}

	@Override
	public Enrollees updateEnrollees(int enrolleesId, Enrollees enrollees) {
		Enrollees enrolleesFromDB = getEnrollees(enrolleesId);
		enrolleesFromDB.setName(enrollees.getName());
		enrolleesFromDB.setDob(enrollees.getDob());
		enrolleesFromDB.setStatus(enrollees.getStatus());
		enrolleesFromDB.setPhoneNumber(enrollees.getPhoneNumber());
		entityManager.flush();

		Enrollees updatedEnrollees = getEnrollees(enrolleesId);

		return updatedEnrollees;
	}

	@Override
	public boolean deleteEnrollees(int enrolleesId) {
		Enrollees e = getEnrollees(enrolleesId);
		entityManager.remove(e);
		boolean status = entityManager.contains(e);
		if(status){
			return false;
		}
		return true;
	}



}
