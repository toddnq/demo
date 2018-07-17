package com.exercise.department;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.exercise.employee.EmployeeEntity;

@Stateless
public class DepartmentService {

	@PersistenceContext
	private EntityManager em;
	
	public DepartmentService() {
		
	}
	
	public List<DepartmentEntity> getAll(){
		TypedQuery<DepartmentEntity> query = em.createQuery("Select d From DepartmentEntity d", DepartmentEntity.class);
		return query.getResultList();
	}
	
	public DepartmentEntity findById(Integer id) {
		return em.find(DepartmentEntity.class, id);
	}
	
}
