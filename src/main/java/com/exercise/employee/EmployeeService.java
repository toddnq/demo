package com.exercise.employee;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class EmployeeService {

	@PersistenceContext
	private EntityManager em;
	
	public EmployeeService() {
		
	}
	
	public List<EmployeeEntity> getAll(){
		TypedQuery<EmployeeEntity> query = em.createNamedQuery("findAll", EmployeeEntity.class);
		return query.getResultList();
	}
	
	public void create(EmployeeEntity emp) {
		if(emp != null) {
			this.em.persist(emp);
		}
	}
	
	public void update(EmployeeEntity emp) {
		EmployeeEntity employeeEntity = findById(emp.getId());
		employeeEntity.setFirstName(emp.getFirstName());
		employeeEntity.setLastName(emp.getLastName());
		employeeEntity.setGender(emp.getGender());
		employeeEntity.setDepartment(emp.getDepartment());
		this.update(employeeEntity);
	}
	
	public void flush() {
		this.em.flush();
	}
	
	public void removeEmployee(EmployeeEntity emp) {
		EmployeeEntity empEntity = findById(emp.getId());
		this.remove(empEntity.getId().toString());
	}
	
	public void remove(String id) {
		EmployeeEntity emp = findById(Integer.parseInt(id));
		if(emp != null) {
			em.remove(emp);
		}
	}
	
	public EmployeeEntity findById(Integer id) {
		List<EmployeeEntity> getOneEmployee = em
				.createNamedQuery("findById", EmployeeEntity.class)
				.setParameter("empid", id).getResultList();
		if(getOneEmployee.isEmpty())
			throw new NoResultException("Not found ne`");
		else
			return getOneEmployee.get(0);
	}
	
	public EmployeeEntity findByIdStoredProcedure(Integer id) {
		EmployeeEntity getOneEmployee = (EmployeeEntity) em.createNamedStoredProcedureQuery("SP_GetEmployeeById")
			    .setParameter("id", id)
			    .getSingleResult();
			return getOneEmployee;
	}
}
