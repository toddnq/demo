package com.exercise.employee;

import java.awt.print.Book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.exercise.department.DepartmentEntity;
import com.exercise.validation.Email;

@Entity
@Table(name = "employee")
@NamedQueries({
	@NamedQuery(name="findAll", query="SELECT e FROM EmployeeEntity e"),
	@NamedQuery(name="findById", query="SELECT e FROM EmployeeEntity e where e.id = :empid")
})
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "SP_GetEmployeeById",
            procedureName = "SP_GET_EMPLOYEE_BY_ID",
            resultClasses = EmployeeEntity.class,
            parameters = {
                    @StoredProcedureParameter(
                            name = "id",
                            mode = ParameterMode.IN,
                            type = Integer.class)
            }
    ),
})

public class EmployeeEntity {
	
	@Column(name="empId")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	private String firstName;
	private String lastName;
	private String gender;
	
	@NotNull
	@Email
	private String email;
	
	@ManyToOne
	@JoinColumn(name="deptid")
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

	
	
}
