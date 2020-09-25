package com.crud.services;

import java.util.List;

import com.crud.entity.Enrollees;


public interface IEnrolleesService {
	List<Enrollees> getEnrolleesList();
	Enrollees createEnrollees(Enrollees enrollees);
	Enrollees updateEnrollees(int enrolleesId, Enrollees enrollees);
	boolean deleteEnrollees(int enrolleesId);
	Enrollees getEnrollees(int enrolleesId);

}
