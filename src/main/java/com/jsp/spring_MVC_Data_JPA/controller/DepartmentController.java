package com.jsp.spring_MVC_Data_JPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.spring_MVC_Data_JPA.dao.DepartmentDao;
import com.jsp.spring_MVC_Data_JPA.dao.DepartmentDaoImpl;
import com.jsp.spring_MVC_Data_JPA.entity.Department;
import com.jsp.spring_MVC_Data_JPA.repository.DepartmentRepository;

@Controller
@RequestMapping("/dept")
public class DepartmentController {
	
	@Autowired
	private DepartmentDao departmentDao;
	
	private DepartmentDaoImpl dept;
	
	@Autowired
	private DepartmentRepository deptRepo;
	
	@RequestMapping("/saveDepartment")
	public String addDepartment(@RequestParam("deptName") String deptName, RedirectAttributes attributes)
	{
		Department department=new Department();
		department.setDepartmentName(deptName);
		
		//to save department data into database
		Department deptFromDB=departmentDao.addDepartment(department);
		
		attributes.addFlashAttribute("msg", "Department has been added successfully with id "+ deptFromDB.getId());
		
		//redirecting to another url to see the view Department page
		return "redirect:page/1/id/ascending";
	}
	
	@RequestMapping("/viewDepartment")
	public String viewDepartments(Model model) {
		List<Department>departments=departmentDao.viewAllDepartments();
		model.addAttribute("listOfDepartments", departments);
		
		//return html file
		return "View_Department";
		
	}
	
	//to transfer the data for redirect resourse- RedirectAttributes interface
	//contains addFlashAttributed(identifier, msg);
	
	@RequestMapping("/removeDepartment/{id}")
	public String removeDepartment(@PathVariable("id") int id, RedirectAttributes attributes)
	{
		departmentDao.removeDepartmentById(id);
		attributes.addFlashAttribute("msg", "Department Deleted...");
		return "redirect:/dept/page/1/id/ascending";
	}
	
	@RequestMapping("/displayDept/{id}")
	public String displayDepartment(@PathVariable("id") int id, Model model)
	{
		Department department=departmentDao.getDepartmentById(id);
		
		model.addAttribute("dept", department);
		return "update_Department";	
	}
	
	@RequestMapping("/updateDepartment")
	public String updateDepartment(@ModelAttribute Department department, Model model, RedirectAttributes attributes)
	{
		Department dept=departmentDao.updatDepartment(department);
		
		if(dept!=null)
		{
			attributes.addFlashAttribute("msg", "Department has been updated successfully with id "+ department.getId());
			return "redirect:page/1/id/ascending";
		}
		
		model.addAttribute("msg","Updation Unsuccessfull!!!");
		return "update_Departmen";
		
	}
	
	@RequestMapping("/page/{pageNum}/{columnName}/{direction}")
	public String listDepartmentByPage(@PathVariable("pageNum") int pageNum, Model model,
										@PathVariable("columnName") String columnName,
										@PathVariable("direction") String direction)
	{
		Page<Department>page= departmentDao.departmentListbyPage(pageNum,columnName,direction);
		
		//to get total pages created
		int pages=page.getTotalPages();
		model.addAttribute("noOfPage", pages);
	
		//to get list of page
		List<Department> content =page.getContent();

		model.addAttribute("listOfDepartments", content);
		model.addAttribute("columnName", columnName);
		model.addAttribute("direction", direction);
		model.addAttribute("pageNumber", pageNum);
		
		//reverse the direction
		String reverseDirection=direction.equals("ascending") ? "desending" : "ascending";
		model.addAttribute("revDirection", reverseDirection);
		
		return "View_Department";		
	}

}
	