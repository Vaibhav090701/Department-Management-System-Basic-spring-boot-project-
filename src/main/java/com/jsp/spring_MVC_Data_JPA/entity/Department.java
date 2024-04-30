package com.jsp.spring_MVC_Data_JPA.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_id")
	@SequenceGenerator(name = "dept_id", initialValue = 101, allocationSize = 10, sequenceName = "dept_sequence")
	private int id;
	private String departmentName;
	
	@OneToMany(mappedBy = "department")
	private List<Employee> employees;
	
	


}
