package com.exercise.employee;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.exercise.department.DepartmentEntity;

@XmlRootElement(name="employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeBOM {
	private Integer id;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;

	private DepartmentEntity department;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}



	public EmployeeBOM(Integer id, String firstName, String lastName, String gender, String email,
			DepartmentEntity department) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.department = department;
	}

	
	
}
