package com.crud.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crud.entity.Dependents;
import com.crud.entity.Enrollees;

@Transactional
@Repository
public class DependentsDAO implements IDependentsDAO {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private IEnrolleesDAO dao;

	@Override
	public List<Dependents> getDependentsListFromEnrol(int enrolleesId) {
		String sql = "SELECT * FROM dependents WHERE enrol_id="+enrolleesId;
		return (List<Dependents>) entityManager.createNativeQuery(sql).getResultList();
	}

	@Override
	public Dependents createDependentsFromEnrol(int enrolleesId,Dependents dependents) {
		Enrollees enrollees=dao.getEnrollees(enrolleesId);
		dependents.setEnrollees(enrollees);
		entityManager.persist(dependents);
		String hql = "from Dependents order by id DESC";
		Query query = entityManager.createQuery(hql);
		query.setMaxResults(1);
		Dependents d = (Dependents)query.getSingleResult();
		return d;
	}

	@Override
	public Dependents modifyDePendentFromEnrol(int enrolleesId,int dependentsId, Dependents dependents) {
		Dependents DependentsFromDB = getDependentsFromEnrol(enrolleesId, dependentsId);
		Enrollees enrollees=dao.getEnrollees(enrolleesId);
		dependents.setEnrollees(enrollees);
		DependentsFromDB.setName(dependents.getName());
		DependentsFromDB.setDob(dependents.getDob());
		entityManager.flush();
		Dependents updateDependents = getDependentsFromEnrol(enrolleesId, dependentsId);
		return updateDependents;
	}

	@Override
	public boolean deleteDependentsFromEnrol(int enrolleesId,int dependentsId) {
		Dependents e = getDependentsFromEnrol(enrolleesId, dependentsId);
		entityManager.remove(e);
		boolean status = entityManager.contains(e);
		if(status){
			return false;
		}
		return true;
	}

	@Override
	public Dependents getDependentsFromEnrol(int enrolleesId,int dependentsId) {
		return entityManager.find(Dependents.class, dependentsId);
	}



}
