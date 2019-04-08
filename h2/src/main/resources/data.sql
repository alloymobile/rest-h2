
/* Department Table */
INSERT into department(id,name)
  values(1,'finance');
INSERT into department(id,name)
  values(2,'technology');
INSERT into department(id,name)
  values(3,'sales');

/* Project Table */
INSERT into project(id,name)
  values(1,'tac');
INSERT into project(id,name)
  values(2,'pmat');
INSERT into project(id,name)
  values(3,'glass');

/* Employee Table */
INSERT into employee(id,name,email, department_id)
  values(1,'John Doe', 'john@rbc.com',1);
INSERT into employee(id,name,email, department_id)
  values(2,'Sam Scott', 'sam@rbc.com',2);
INSERT into employee(id,name,email, department_id)
  values(3,'Dev Salmon', 'dev@rbc.com',2);
INSERT into employee(id,name,email, department_id)
  values(4,'Harry Robert', 'harry@rbc.com',3);


/* EmployeeProject Table */
INSERT into employee_project(id,employee_id, project_id)
  values(1,1,1);
INSERT into employee_project(id,employee_id, project_id)
  values(2,3,1);
INSERT into employee_project(id,employee_id, project_id)
  values(3,4,1);
INSERT into employee_project(id,employee_id, project_id)
  values(4,2,2);
INSERT into employee_project(id,employee_id, project_id)
  values(5,1,2);
INSERT into employee_project(id,employee_id, project_id)
  values(6,4,3);
