package com.crud.dao;

import java.util.List;

import com.crud.entity.Enrollees;

public interface IEnrolleesDAO {

	List<Enrollees> getEnrolleesList();
	Enrollees createEnrollees(Enrollees enrollees);
	Enrollees updateEnrollees(int enrolleesId, Enrollees enrollees);
	boolean deleteEnrollees(int enrolleesId);
	Enrollees getEnrollees(int enrolleesId);
}


