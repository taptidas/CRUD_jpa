package com.Crud.jpaTest.CrudProject;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

	@Repository
	public interface EmployeeRepository extends JpaRepository<Employee, Long>{
		
		public Employee findByEmailId(String emailId);

	}


