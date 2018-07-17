package com.exercise.employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import com.exercise.department.DepartmentEntity;
import com.exercise.department.DepartmentService;

@ManagedBean
@ViewScoped
public class EmployeeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EmployeeEntity selectedEmployee;
	public EmployeeEntity getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(EmployeeEntity selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	private List<EmployeeEntity> listAllEmployees;
	private List<DepartmentEntity> listAllDept;

	public List<DepartmentEntity> getListAllDept() {
		return listAllDept;
	}

	public void setListAllDept(List<DepartmentEntity> listAllDept) {
		this.listAllDept = listAllDept;
	}

	@Inject
	private DepartmentService departmentService;
	private DepartmentEntity selectedDept;
	
	public DepartmentEntity getSelectedDept() {
		return selectedDept;
	}

	public void setSelectedDept(DepartmentEntity selectedDept) {
		this.selectedDept = selectedDept;
	}

	@Inject
	EmployeeService employeeService;
	
	
	public List<EmployeeEntity> getListAllEmployees() {
		return listAllEmployees;
	}

	public void setListAllEmployees(List<EmployeeEntity> listAllEmployees) {
		this.listAllEmployees = listAllEmployees;
	}

	private  List<EmployeeEntity> loadAllEmployees(){
		return employeeService.getAll();
	}
	
	private boolean createEmployee = true;
	private boolean updateEmployee = false;
	public boolean isCreateEmployee() {
		return createEmployee;
	}

	public void setCreateEmployee(boolean createEmployee) {
		this.createEmployee = createEmployee;
	}

	public boolean isUpdateEmployee() {
		return updateEmployee;
	}

	public void setUpdateEmployee(boolean updateEmployee) {
		this.updateEmployee = updateEmployee;
	}

	@PostConstruct
	public void init() {
		prepareEmployeeData();
		prepareDepartment();
	}

	private void prepareDepartment() {
		listAllDept = new ArrayList<DepartmentEntity>();
		listAllDept = loadAllDepts();
		if(listAllDept.size() > 0) {
			setSelectedDept(listAllDept.get(0));
		}
	}

	private List<DepartmentEntity> loadAllDepts() {
		return departmentService.getAll();
	}

	private void prepareEmployeeData() {
		listAllEmployees = new ArrayList<EmployeeEntity>();
		listAllEmployees = loadAllEmployees();
		selectedEmployee = new EmployeeEntity();
		selectedEmployee.setGender("undefine");
	}
	
	public EmployeeBean() {
		
	}
	
//	public void valueChangeMethod(ValueChangeEvent e) {
//		selectedEmployee = employeeService.findById(Integer.parseInt(e.getNewValue().toString()));
//	}
	
	public void changeDepartment(ValueChangeEvent dept) {
		selectedDept = departmentService.findById(Integer.parseInt(dept.getNewValue().toString()));
	}
	
	public String addEmployee() {
		EmployeeEntity emp = new EmployeeEntity();
		emp.setFirstName(this.selectedEmployee.getFirstName());
		emp.setLastName(this.selectedEmployee.getLastName());
		emp.setGender(this.selectedEmployee.getGender());
		emp.setEmail(this.selectedEmployee.getEmail());
		emp.setDepartment(this.selectedDept);
		employeeService.create(emp);
		listAllEmployees = loadAllEmployees();
		
		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}
	
	public String editEmployee() {
		selectedEmployee.setDepartment(selectedDept);
		employeeService.update(selectedEmployee);
		
		selectedEmployee = new EmployeeEntity();
		listAllEmployees = loadAllEmployees();
		
		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}
	
	public void selectEmployeeForEdit(EmployeeEntity emp) {
		setSelectedEmployee(emp);
		selectedDept = emp.getDepartment();
		setCreateEmployee(false);
		setUpdateEmployee(true);
	}
	
	public String deteleEmployee(EmployeeEntity emp) {
		employeeService.removeEmployee(emp);
		listAllEmployees = loadAllEmployees();
		
		return "index.xhtml?faces-redirect=true&includeViewParams=true";
	}
	
	private boolean sortAscending = true;
	
	public void sortByOrderEmployeeID() {
		if(sortAscending) {
			Collections.sort(listAllEmployees, new Comparator<EmployeeEntity>() {
				@Override
				public int compare(EmployeeEntity emp1, EmployeeEntity emp2) {
					return emp1.getId().compareTo(emp2.getId());
				}
			});
			sortAscending = false;
		} else {
			Collections.sort(listAllEmployees, new Comparator<EmployeeEntity>() {
				@Override
				public int compare(EmployeeEntity emp1, EmployeeEntity emp2) {
					return emp2.getId().compareTo(emp1.getId());
				}
			});
			sortAscending = true;
		}
	}
}
