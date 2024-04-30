package com.jsp.spring_MVC_Data_JPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jsp.spring_MVC_Data_JPA.entity.Department;

import jakarta.transaction.Transactional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	


}
