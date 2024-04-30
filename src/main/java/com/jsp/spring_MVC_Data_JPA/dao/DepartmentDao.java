package com.jsp.spring_MVC_Data_JPA.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jsp.spring_MVC_Data_JPA.entity.Department;

import jakarta.transaction.Transactional;

@Transactional
public interface DepartmentDao {
	
	Department addDepartment(Department department);

	List<Department> viewAllDepartments();
	
	void removeDepartmentById(int id);
	
	Department getDepartmentById(int id);
	
	Department updatDepartment(Department department);
	
	Page<Department> departmentListbyPage(int pageNum, String columnName, String direction);



}
