package com.crud.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Enrollees;
import com.crud.exception.EnrolleesNotFoundException;
import com.crud.services.IEnrolleesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("enrolleesService")
public class EnrolleesController {
	
	@Autowired
	private IEnrolleesService service;
	
	@ApiOperation(value = "View a list of available Enrollees", response = Iterable.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved all enrollees list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@GetMapping("enrolleesList")
	public ResponseEntity<List<Enrollees>> getEnrollees(){
		
		List<Enrollees> enrollees = service.getEnrolleesList();
		return new ResponseEntity<List<Enrollees>>(enrollees, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "View a list of single Enrollee", response = Iterable.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list of single enrollee"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@GetMapping("getEnrollees/{id}")
	public ResponseEntity<Enrollees> getEnrollees(@PathVariable("id") Integer id){
		Enrollees enrollees = service.getEnrollees(id);
		if(enrollees ==null)
		{
			throw new EnrolleesNotFoundException("Enrollees not found");
		}
		return new ResponseEntity<Enrollees>(enrollees, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Add an Enrollee", response = Iterable.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully added enrollee"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@PostMapping("addEnrollees")
	public ResponseEntity<Enrollees> createEnrollees(@RequestBody Enrollees enrollees){
		if(!isValidFormat("dd/MM/yyyy", enrollees.getDob(), Locale.ENGLISH))
		{
			throw new EnrolleesNotFoundException("Enter the Date in this format: dd/MM/yyyy");
		}

			Enrollees e = service.createEnrollees(enrollees);
			return new ResponseEntity<Enrollees>(e, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Update an existing Enrollee", response = Iterable.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully updated enrollee"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@PutMapping("modifyEnrollees/{id}")
	public ResponseEntity<Enrollees> updateEnrollees(@PathVariable("id") int id, @RequestBody Enrollees enrollees){
		if(!isValidFormat("dd/MM/yyyy", enrollees.getDob(), Locale.ENGLISH))
		{
			throw new EnrolleesNotFoundException("Enter the Date in this format: dd/MM/yyyy");
		}
		Enrollees e = service.updateEnrollees(id, enrollees);
		return new ResponseEntity<Enrollees>(e, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Delete an Enrollee", response = Iterable.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Deleted an Enrollee"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@DeleteMapping("removeEnrollees/{id}")
	public ResponseEntity<String> deleteEnrollees(@PathVariable("id") int id){
		boolean isDeleted = service.deleteEnrollees(id);
		if(isDeleted){
			String responseContent = "Enrollees has been deleted successfully";
			return new ResponseEntity<String>(responseContent,HttpStatus.OK);
		}
		String error = "Error while deleting Enrollees from database";
		return new ResponseEntity<String>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static boolean isValidFormat(String format, String value, Locale locale) {
		LocalDateTime ldt = null;
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

		try {
			ldt = LocalDateTime.parse(value, fomatter);
			String result = ldt.format(fomatter);
			return result.equals(value);
		} catch (DateTimeParseException e) {
			try {
				LocalDate ld = LocalDate.parse(value, fomatter);
				String result = ld.format(fomatter);
				return result.equals(value);
			} catch (DateTimeParseException exp) {
				try {
					LocalTime lt = LocalTime.parse(value, fomatter);
					String result = lt.format(fomatter);
					return result.equals(value);
				} catch (DateTimeParseException e2) {
					// Debugging purposes
					//e2.printStackTrace();
				}
			}
		}

		return false;
	}

}
