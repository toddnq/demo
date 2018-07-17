package com.exercise.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.exercise.employee.EmployeeEntity;
import com.exercise.employee.EmployeeService;

@Stateless
@Path("employee")
public class EmployeeRest {
	@EJB
	EmployeeService emp;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAll() {
        return Response.ok(emp.getAll()).build();
    }
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("{EmployeeId}")
	public EmployeeEntity getById(@PathParam("EmployeeId")String employeeId){
		return emp.findById(Integer.parseInt(employeeId));
	}
	
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("sp/{EmployeeId}")
	public EmployeeEntity getByIdStoredProcedure(@PathParam("EmployeeId")String employeeId){
		return emp.findByIdStoredProcedure(Integer.parseInt(employeeId));
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response employee(@Valid EmployeeEntity empEntity) {
		emp.create(empEntity);
		return Response.status(Response.Status.CREATED).build();
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateEmployee(EmployeeEntity empEntity) {
		emp.update(empEntity);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{EmployeeId}")
	public Response deletebyId(@PathParam("EmployeeId")String employeeId) {
		emp.remove(employeeId);
		return Response.ok().build();
	}
	
	@DELETE
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response removeEmployee(EmployeeEntity empEntity) {
		emp.removeEmployee(empEntity);
		return Response.ok().build();
	}
}
