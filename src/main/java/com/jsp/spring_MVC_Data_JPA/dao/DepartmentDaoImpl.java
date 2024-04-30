package com.jsp.spring_MVC_Data_JPA.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Component;

import com.jsp.spring_MVC_Data_JPA.entity.Department;
import com.jsp.spring_MVC_Data_JPA.repository.DepartmentRepository;

@Component
public class DepartmentDaoImpl implements DepartmentDao {
	
	//constant variable- because page size is fixed
	private static final int PAGE_SIZE=4;

	@Autowired
	private DepartmentRepository departmentRepository;
	
	public Department addDepartment(Department department) {
		
		return departmentRepository.save(department);
	
	}

	public List<Department> viewAllDepartments() {
		return departmentRepository.findAll();
	}

	public void removeDepartmentById(int id) {
		departmentRepository.deleteById(id);
		
	}

	@Override
	public Department getDepartmentById(int id) {
		
		//the return type of findById is Optional
		Optional<Department> optional= departmentRepository.findById(id);
		return optional.orElse(null);
	}

	@Override
	public Department updatDepartment(Department department) {
		
		Department dept= getDepartmentById(department.getId());
		
		if(dept!=null)
		{
			//update
			dept.setDepartmentName(department.getDepartmentName());
			
			Department updatedObj=departmentRepository.save(dept);
			
			return updatedObj;
			
		}
		return null;
	}

	@Override
	public Page<Department> departmentListbyPage(int pageNum, String columnName, String direction) {
		
		//because user enter page number from one but its starts form 0
		int pageNumber=pageNum-1;
		
		//to sort the data according to column name
		Sort sort=Sort.by(columnName);
		sort=direction.equals("ascending") ? sort.ascending(): sort.descending();
		
		Pageable pageable=PageRequest.of(pageNumber, PAGE_SIZE, sort);
		
		Page<Department> page = departmentRepository.findAll(pageable);
		
		System.out.println("No of pages = "+page.getTotalPages());
		
		return page;
	}

}
