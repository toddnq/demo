insert into department (deptid, deptname) values ('1', 'Developer')
insert into department (deptid, deptname) values ('2', 'Information Technology')
insert into department (deptid, deptname) values ('3', 'Finance')
insert into department (deptid, deptname) values ('4', 'Human Resources')
insert into employee (empid, firstname, lastname, gender, email, deptid) values ('1', 'test1', 'test11', 'male', 'test1@axonactive.com', '1')
insert into employee (empid, firstname, lastname, gender, email, deptid) values ('2', 'test2', 'test22', 'female', 'test2@axonactive.com', '1')
insert into employee (empid, firstname, lastname, gender, email, deptid) values ('3', 'test3', 'test33', 'undefine', 'test3@axonactive.com', '2')

--DROP PROCEDURE IF EXISTS SP_GET_EMPLOYEE_BY_ID;
--CREATE PROCEDURE SP_GET_EMPLOYEE_BY_ID(IN id INTEGER(11))
--BEGIN
--    SELECT * FROM Employee e WHERE e.empid = id;
--END;