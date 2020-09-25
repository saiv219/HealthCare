package com.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.dao.IDependentsDAO;
import com.crud.dao.IEnrolleesDAO;
import com.crud.entity.Dependents;
import com.crud.entity.Enrollees;

@Service
public class DependentsService implements IDependentsService {
	
	@Autowired
	private IDependentsDAO dao;

	
	@Override
	public List<Dependents> getDependentsListFromEnrol(int enrolleesId) {
		return dao.getDependentsListFromEnrol(enrolleesId);
	}

	@Override
	public Dependents createDependentsFromEnrol(int enrolleesId, Dependents dependents) {
		return dao.createDependentsFromEnrol(enrolleesId, dependents);
	}

	@Override
	public Dependents modifyDePendentFromEnrol(int enrolleesId, int dependentsId, Dependents dependents) {
		return dao.modifyDePendentFromEnrol(enrolleesId,dependentsId, dependents);
	}

	@Override
	public boolean deleteDependentsFromEnrol(int enrolleesId, int dependentsId) {
		return dao.deleteDependentsFromEnrol(enrolleesId,dependentsId);
	}

	@Override
	public Dependents getDependentsFromEnrol(int enrolleesId, int dependentsId) {
		return dao.getDependentsFromEnrol(enrolleesId, dependentsId);
	}

}
