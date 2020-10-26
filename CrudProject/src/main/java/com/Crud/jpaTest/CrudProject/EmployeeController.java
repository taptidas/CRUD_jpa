package com.Crud.jpaTest.CrudProject;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.management.relation.RelationNotFoundException;
//import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/employees")
@Api(value="employeedetails", description="Operations regarding employees details")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@ApiOperation(value = "View a list of existing employees",response = List.class)
	@GetMapping("/")
	public List <Employee> getAllEmployees() {
		logger.debug("Entering getAllEmployees method");
		
		return (List<Employee>) employeeRepository.findAll();
	}

	@ApiOperation(value = "Search for an employee with a id",response = ResponseEntity.class)
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws EmployeeNotPresent
	{logger.trace("Entering getAllEmployeeById method");


		Optional<Employee> employee = employeeRepository.findById(employeeId);
		logger.info("Checking if employee exists!");
		if(!employee.isPresent())
		{
			logger.error("No such employee exists!");
			throw new EmployeeNotPresent("employee with this id is not present");	
		}
		logger.trace("get employee data");
		return ResponseEntity.ok().body(employee);
	}


	@ApiOperation(value = "Add a employee")
	@PostMapping("/")
	public Employee createEmployee(@RequestBody Employee employee) throws DuplicateUserException {
		logger.trace("Entering createEmployee method");
		Optional<Employee> employee1 = Optional.ofNullable(employeeRepository.findByEmailId(employee.getEmailId()));
		
		logger.info("Checking if employee already exists!");
		if(employee1.isPresent())
		{	
			logger.error("employee already exists!");
			throw new DuplicateUserException("Duplicate User!, User already exists!");
		}
		
			logger.trace("employee created");
		return employeeRepository.save(employee);
		
	}

	@ApiOperation(value = "Update a employee")
	@PutMapping("/{id}")
	public ResponseEntity < Employee > updateEmployee(@PathVariable(value = "id") Long employeeId,
			@RequestBody Employee employeeDetails) {
		logger.trace("Entering updateEmployees method");
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		
		logger.info("Checking if employee exists!");
		if(!employee.isPresent())
			return (ResponseEntity<Employee>) ResponseEntity.notFound();

		employeeDetails.setId(employeeId);
		Employee updatedEmployee = employeeRepository.save(employeeDetails);
		logger.trace("employee updated");
		return ResponseEntity.ok(updatedEmployee);
	}

	@ApiOperation(value = "Delete a employee from record")
	@DeleteMapping("/{id}")
	public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
	{logger.trace("Entering deleteEmployees method");

		//Employee employee =employeeRepository.findById(employeeId);
		employeeRepository.deleteById(employeeId);
		Map < String, Boolean > response = new HashMap < > ();
		response.put("deleted", Boolean.TRUE);
		
		logger.trace("employee deleted");
		return response;
	}
}
