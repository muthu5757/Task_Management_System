package src.controller;

public class Query {
    public static String EMPLOYEE_INSERT = "insert into employee (employee_id, firstname,lastname, email_id, mobile_number, hire_date,designation) values ( ? , ? , ? , ?, ?, cast(? as date), ?) returning id ;";
    public static String EMPLOYEE_CREDENTIAL_INSERT = "insert into logincredentials (employee_id,username, password) values (?, ?, ?)";
    public static String SET_USERNAME = "update logincredentials set username = ? where employee_id = ? ;";
    public static String SET_PASSWORD = "update logincredentials set password = ? where employee_id = ? ;";
    public static String FETCH_EMPLOYEE_ID = "select id from employee where trim(employee_id) = ?;";
    public static String FETCH_DEPT_ROLE_ID = "select d.id as dept, r.id as role from department d cross join role r where trim(lower(d.name)) = lower(?) and trim(lower(r.name)) = lower(?) ;";
    public static String INSERT_EMPLOYEE_ROLE = "insert into employee_role_department(employee_id,department,role) values (? , ? , ?); ";
    public static String UPDATE_EMPLOYEE_ROLE = "update employee_role_department set department = ? and role = ? where employee_id =?; ";
    public static String EMPLOYEE_ISROLE = "select 1 as found from employee_role_department where employee_id = ?;";
    public static String DISPLAY_EMPLOYEE_ROLE = "select e.id, e.employee_id, e.firstname, e.lastname, ds.designation_name, de.name, r.name as role from employee e join employee_role_department d on d.employee_id = e.id join department de on d.department = de.id join role r on d.role = r.id join designation ds on e.designation = ds.id;";
    public static String INSERT_ROLE = "insert into role (name) values (?);";
    public static String DISPLAY_ROLE = "select name from role;";
    public static String DISPLAY_DEPARTMENT = "select name from department;";
    public static String INSERT_DEPARTMENT = "insert into department (name) values (?);";
    public static String INSERT_DESIGNATION = "insert into designation (designation_name) values (?);";
    public static String FETCH_PASSWORD = "select employee_id, password from logincredentials where trim(username) = ? and status = 'active';";
    public static String FETCH_ROLE = "select trim(r.name) as name from role r join employee_role_department rd on rd.role = r.id where employee_id = ?;";
    public static String FETCH_EMPLOYEE_DETAILS_BY_ID = "select e.employee_id,e.firstname,e.lastname,e.email_id,e.mobile_number,e.hire_date,d.designation_name from employee e join designation d on e.designation = d.id where e.id = ?;";

    public static String FETCH_EMPLOYEE_DETAILS_BY_EMPID = "select e.id,e.firstname,e.lastname,e.email_id,e.mobile_number,e.hire_date,d.designation_name from employee e join designation d on e.designation = d.id where e.employee_id = ?;";

    public static String DESIGNATION_ID = "select id from designation where lower(designation_name) = lower(?) ;";
    public static String INSERT_DESIGNATION_GET_ID = "insert into designation (designation_name) values (?) returning id;";
    public static String INSERT_HR = "insert into hr_list(admin_id, hr_id) values (?, ?); ";
    public static String INSERT_MANAGER = "insert into manager_list(hr_id, manager_id) values (?, ?); ";
    public static String INSERT_TEAMLEADER = "insert into team_leader_list(manager_id,team_leader) values (?, ?); ";
    public static String INSERT_COORDINATOR = "insert into co_ordinator_list(manager_id,team_leader_id, co_ordinator_id) values (?,?, ?);";
    public static String INSERT_EMPLOYEE = "insert into employees_list(manager_id,team_leader_id,co_ordinator_id, employee_id) values (?,?,?, ?);";
    public static String DISPLAY_HR = "select e.id, e.employee_Id, e.firstname, e.lastname, e.email_id, e.mobile_number from employee e join hr_list hl on hl.hr_id = e.id where admin_id = ?;";
    public static String DISPLAY_MANAGER = "select e.id, e.employee_Id, e.firstname, e.lastname,e.email_id, e.mobile_number from employee e join manager_list hl on hl.manager_id = e.id where hr_id = ?;";
    public static String DISPLAY_TEAMLEADER = "select e.id, e.employee_Id, e.firstname, e.lastname, e.email_id, e.mobile_number from employee e join team_leader_list hl on hl.team_leader = e.id where manager_id = ?;";

    public static String DISPLAY_COORDINATOR_BY_MANAGER = "select e.id, e.employee_Id, e.firstname, e.lastname, e.email_id, e.mobile_number from employee e join co_ordinator_list hl on hl.co_ordinator_id = e.id where manager_id = ?;";

    public static String DISPLAY_COORDINATOR_BY_TEAMLEADER = "select e.id, e.employee_Id, e.firstname, e.lastname, e.email_id, e.mobile_number from employee e join co_ordinator_list hl on hl.co_ordinator_id = e.id where team_leader_id = ?;";

    public static String DISPLAY_EMPLOYEE_BY_MANAGER = "select e.id, e.employee_Id, e.firstname, e.lastname, e.email_id, e.mobile_number from employee e join employees_list hl on hl.employee_id = e.id where manager_id = ?;";

    public static String DISPLAY_EMPLOYEE_BY_TEAMLEADER = "select e.id, e.employee_Id, e.firstname, e.lastname, e.email_id, e.mobile_number from employee e join employees_list hl on hl.employee_id = e.id where team_leader_id = ?;";

    public static String DISPLAY_EMPLOYEE_BY_COORDINATOR = "select e.id, e.employee_Id, e.firstname, e.lastname, e.email_id, e.mobile_number from employee e join employees_list hl on hl.employee_id = e.id where co_ordinator_id = ?;";

    public static String DISPLAY_TEAM = "select e.id, e.employee_id, e.firstname, e.lastname, e.email_id, e.mobile_number, case when tl.team_leader is not null then 'Team Leader' when cl.co_ordinator_id is not null then 'Co-ordinator' when el.employee_id is not null then 'Employee' end as role from employee e left join team_leader_list tl on tl.team_leader = e.id left join co_ordinator_list cl on cl.co_ordinator_id = e.id left join employees_list el on el.employee_id = e.id where tl.manager_id = ? or cl.manager_id = ? or el.manager_id = ?; ";
    public static String WRITE_TASK = "insert into tasks(parent_task,sender, employee, code, subject, description, due_date, priority) values (?,?, ?,?, ?, ?, cast(? as date), ?::priority);";public static String DISPLAY_TASK_BY_MANAGER = "select e.employee_Id, e.firstname, e.lastname, hl.id, hl.code, hl.subject, hl.description, hl.due_date, hl.status from employee e join tasks hl on hl.employee = e.id where sender= ? order by creation_date desc limit 15;";
    public static String DISPLAY_TASK_TO_TEAMLEADER = "select e.employee_Id, e.firstname, e.lastname, hl.id, hl.code, hl.subject, hl.description, hl.due_date,hl.status from employee e join tasks hl on hl.sender = e.id where employee = ? order by creation_date desc limit 15;";
    public static String DISPLAY_TASK_TO_COORDINATOR = "select e.employee_Id, e.firstname, e.lastname,hl.id, hl.code, hl.subject, hl.description, hl.due_date,hl.status from employee e join tasks hl on hl.sender = e.id where employee = ? order by creation_date desc limit 15;";
    public static String DISPLAY_TASK_TO_EMPLOYEE = "select e.employee_Id, e.firstname, e.lastname,hl.id, hl.code, hl.subject, hl.description, hl.due_date,hl.status from employee e join tasks hl on hl.sender = e.id where employee = ? order by creation_date desc limit 15;";

    public static String DISPLAY_TASK_BY_TEAMLEADER = "select e.employee_Id, e.firstname, e.lastname,hl.id, hl.code, hl.subject, hl.description, hl.due_date, hl.status from employee e join tasks hl on hl.employee = e.id where sender = ? order by creation_date desc limit 15;";
    public static String DISPLAY_TASK_BY_COORDINATOR = "select e.employee_Id, e.firstname, e.lastname, hl.id, hl.code, hl.subject, hl.description, hl.due_date, hl.status from employee e join tasks hl on hl.employee = e.id where hl.sender = ? order by creation_date desc limit 15;";

    public static String FETCH_MANAGER_BY_TEAMLEADER = "select manager_id from team_leader_list where team_leader = ? ;";
    public static String FETCH_MANAGER_BY_COORDINATOR = "select manager_id from co_ordinator_list where co_ordinator_id = ? ;";
    public static String FETCH_MANAGER_BY_EMPLOYEE = "select manager_id from employees_list where employee_id = ? ;";
    public static String FETCH_TASK = "select id from tasks where code = ?";
    public static String FETCH_TLTASK_ID = " select id from tasks where status != 'COMPLETED' and sender = ? ;";
    public static String FETCH_MTASK_ID = " select id from tasks where status != 'COMPLETED' and sender = ? ; ";
    public static String FETCH_TASK_ID_COUNT_1 = "select count(id) as count from tasks where parent_task = ?;";
    public static String FETCH_TASK_STATUS_1 = "select count(status) as count from tasks where status = 'COMPLETED' and parent_task = ?;";
    public static String FETCH_TASK_ID_COUNT_2 = "select count(id) as count from tasks where parent_task = ? ;";
    public static String FETCH_TASK_STATUS_2 = "select count(status) as count from tasks where status = 'COMPLETED' and parent_task = ? ;";
    public static String UPDATE_AUTO_STATUS1 = "Update tasks set status = ?::new_task_status where id = ?;";
    public static String UPDATE_AUTO_STATUS2 = "Update tasks set status = ?::new_task_status where id = ? ";
    public static String UPDATE_TASK_STATUS = "Update tasks set status = ?::new_task_status where id = ? ";
    public static String FETCH_TEAM_MEMBERS_ID = "select e.id from employee e left join team_leader_list tl on tl.team_leader = e.id left join co_ordinator_list cl on cl.co_ordinator_id = e.id left join employees_list el on el.employee_id = e.id where tl.manager_id = ? or cl.manager_id = ? or el.manager_id = ?; ";
    public static String FETCH_TEAM_MEMBERS_TASK = "select e.employee_id, e.firstname, e.lastname,hl.code, hl.subject, hl.description, hl.due_date, hl.status from employee e join tasks hl on hl.employee = e.id where employee = ? ";

}
