package com.crud.services;

import java.util.List;

import com.crud.entity.Dependents;

public interface IDependentsService {

	List<Dependents> getDependentsListFromEnrol(int enrolleesId);
	Dependents createDependentsFromEnrol(int enrolleesId,Dependents dependents);
	Dependents modifyDePendentFromEnrol(int enrolleesId,int dependentsId, Dependents dependents);
	boolean deleteDependentsFromEnrol(int enrolleesId,int dependentsId);
	Dependents getDependentsFromEnrol(int enrolleesId,int dependentsId);
}
