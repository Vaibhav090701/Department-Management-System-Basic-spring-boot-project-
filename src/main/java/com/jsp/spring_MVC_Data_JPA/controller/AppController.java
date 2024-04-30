package com.jsp.spring_MVC_Data_JPA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class AppController {
	
	@RequestMapping("/addDepartment")
	public String showAddDepartmentPage()
	{
		return "Add_Department";
	}
	
	@RequestMapping("/index")
	public String ShowIndexPage()
	{
		return "index";
	}

}
