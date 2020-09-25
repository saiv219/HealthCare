package com.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.dao.IEnrolleesDAO;
import com.crud.entity.Enrollees;

@Service
public class EnrolleesService implements IEnrolleesService {
	
	@Autowired
	private IEnrolleesDAO dao;

	@Override
	public List<Enrollees> getEnrolleesList() {
		return dao.getEnrolleesList();
	}

	@Override
	public Enrollees createEnrollees(Enrollees enrollees) {
		return dao.createEnrollees(enrollees);
	}

	@Override
	public Enrollees updateEnrollees(int enrolleesId, Enrollees enrollees) {
		return dao.updateEnrollees(enrolleesId, enrollees);
	}

	@Override
	public boolean deleteEnrollees(int enrolleesId) {
		return dao.deleteEnrollees(enrolleesId);
	}

	@Override
	public Enrollees getEnrollees(int enrolleesId) {
		return dao.getEnrollees(enrolleesId);
	}

}
