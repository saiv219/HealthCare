package com.crud.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Dependents;
import com.crud.entity.Enrollees;
import com.crud.exception.DependentsNotFoundException;
import com.crud.exception.EnrolleesNotFoundException;
import com.crud.services.IDependentsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("dependentService")
public class DependentsController {

	@Autowired
	private IDependentsService service;
	
	@GetMapping("getDependents/{eid}/{did}")
	public ResponseEntity<Dependents> getEnrollees(@PathVariable("eid") Integer eid,@PathVariable("did") Integer did){
		Dependents d = service.getDependentsFromEnrol(eid, did);
		return new ResponseEntity<Dependents>(d, HttpStatus.OK);
	}


	@ApiOperation(value = "View a list of available Dependencies based on Enrollee ID", response = Iterable.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved all dependencies list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@GetMapping("dependentsList/{id}")
	public ResponseEntity<List<Dependents>> getDependentsListFromEnrol(@PathVariable("id") Integer enrol_id){
		List<Dependents> dependentsList = service.getDependentsListFromEnrol(enrol_id);
		System.out.println(dependentsList.size());
		return new ResponseEntity<List<Dependents>>(dependentsList, HttpStatus.OK);
		
	}

	@ApiOperation(value = "Add a single Dependent to an Enrollee", response = Iterable.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully added single dependent"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@PostMapping("addDependentToEnrol/{id}")
	public ResponseEntity<Dependents> createDependentsFromEnrol(@PathVariable("id") Integer enrol_id,@RequestBody Dependents dependents){
		if(!EnrolleesController.isValidFormat("dd/MM/yyyy", dependents.getDob(), Locale.ENGLISH))
		{
			throw new DependentsNotFoundException("Enter the Date in this format: dd/MM/yyyy");
		}

		Dependents d = service.createDependentsFromEnrol(enrol_id,dependents);
		return new ResponseEntity<Dependents>(d, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Update an existing Dependent", response = Iterable.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully updated Dependent"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@PutMapping("modifyDependentFromEnrol/{eid}/{did}")
	public ResponseEntity<Dependents> modifyDePendentFromEnrol(@PathVariable("eid") int eid,
						@PathVariable("did") int did,@RequestBody Dependents dependents){
		
		Dependents d = service.modifyDePendentFromEnrol(eid,did, dependents);
		return new ResponseEntity<Dependents>(d, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete Dependent", response = Iterable.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Deleted an Dependent"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@DeleteMapping("removeDependentFromEnrollees/{eid}/{did}")
	public ResponseEntity<String> deleteDependentsFromEnrol(@PathVariable("eid") int eid,
			@PathVariable("did") int did){
		boolean isDeleted = service.deleteDependentsFromEnrol(eid,did);
		if(isDeleted){
			String responseContent = "Dependent from Enrollees has been deleted successfully";
			return new ResponseEntity<String>(responseContent,HttpStatus.OK);
		}
		String error = "Error while deleting Dependent from Enrollees in database";
		return new ResponseEntity<String>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
