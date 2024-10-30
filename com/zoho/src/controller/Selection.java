package src.controller;

import src.model.Department;
import src.model.Employee;
import src.model.ObjectCreation;
import src.model.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Selection {

    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    public Employee getDetailsofEmployee(String empId)
    {
        try
        {
            PreparedStatement ps = conn.prepareStatement(Query.FETCH_EMPLOYEE_DETAILS_BY_EMPID);
            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Employee(rs.getInt("id"),empId,rs.getString("firstname"),rs.getString("lastname"),rs.getString("email_id"), rs.getString("mobile_number"),rs.getString("hire_date"),rs.getString("designation_name"),null);
            }
        }
        catch(SQLException e)
        {
            System.out.println();
        }
        return null;
    }
    public Employee getDetailsofEmployee(int empId)
    {
        try
        {
            PreparedStatement ps = conn.prepareStatement(Query.FETCH_EMPLOYEE_DETAILS_BY_ID);
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Employee(empId, rs.getString("employee_id"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("email_id"), rs.getString("mobile_number"),rs.getString("hire_date"),rs.getString("designation_name"),null);
            }
        }
        catch(SQLException e)
        {
            System.out.println();
        }
        return null;
    }
    public List<Employee> getEmployeewithRole()
    {
        List<Employee> employeeRole = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(Query.DISPLAY_EMPLOYEE_ROLE);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                employeeRole.add(new Employee(rs.getInt("id"),rs.getString("employee_id"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("designation_name"),rs.getString("name"),rs.getString("role")));
            }
        } catch (SQLException e) {
            System.out.println("SignIn failed: " + e.getMessage());
        }
        return employeeRole;
    }

    public List<Role> getRoles()
    {
        List<Role> role = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(Query.DISPLAY_ROLE);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                role.add(new Role(rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("SignIn failed: " + e.getMessage());
        }
        return role;
    }

    public List<Department> getDepartments()
    {
        List<Department> dept = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(Query.DISPLAY_DEPARTMENT);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                dept.add(new Department(rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("SignIn failed: " + e.getMessage());
        }
        return dept;
    }

    public String getEmployeeRole(Employee em)
    {
        try{
            PreparedStatement ps = conn.prepareStatement(Query.FETCH_ROLE);
            ps.setInt(1, em.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getString("name");
            else
                System.out.println("\n No Role for this Employee ID ! ❌️ ");
        }
        catch(SQLException e){
            System.out.println("Selection Failed : "+e);
        }
        return null;
    }
    public Employee getManagerIdByEmployee(String Query, Employee em)
    {
        try{
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setInt(1, em.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return getDetailsofEmployee(rs.getInt("manager_id"));
            else
                System.out.println("\n No Manager for this Team Leader ID ! ❌️ ");
        }
        catch(SQLException e){
            System.out.println("Selection Failed : "+e);
        }
        return null;
    }
    public List<Employee> getTeamDetails(Employee em) {
        List<Employee> team = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(Query.DISPLAY_TEAM);
            ps.setInt(1, em.getId());
            ps.setInt(2, em.getId());
            ps.setInt(3, em.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               Employee emp =  new Employee(rs.getInt("id"), rs.getString("employee_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email_id"), rs.getString("mobile_number"));
                emp.setRole(rs.getString("role"));
                team.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Display Failed : " + e);
        }
        return team;
    }
    public List<Integer> getTeamMembersId(Employee em)
    {
        List<Integer> al = new ArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement(Query.FETCH_TEAM_MEMBERS_ID);
            ps.setInt(1,em.getId());
            ps.setInt(2,em.getId());
            ps.setInt(3,em.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                al.add(rs.getInt("id"));
            }
        }
        catch(SQLException e){
            System.out.println("Selection Failed : "+e);
        }
        return al;
    }
    public int getTaskId(String code)
    {
        try{
            PreparedStatement ps = conn.prepareStatement(Query.FETCH_TASK);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt("id");
            else
                System.out.println("\n No Tasks available on this code ! ❌️ ");
        }
        catch(SQLException e){
            System.out.println("Selection Failed : "+e);
        }
        return 0;
    }
    public List<Integer> getTeamLeaderTaskId(Employee em)
    {
        List<Integer> al = new ArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement(Query.FETCH_TLTASK_ID);
            ps.setInt(1,em.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                al.add(rs.getInt("id"));
            }
        }
        catch(SQLException e){
            System.out.println("Selection Failed : "+e);
        }
        return al;
    }
    public List<Integer> getManagerTaskId(Employee em)
    {
        List<Integer> al = new ArrayList();
        try{
            PreparedStatement ps = conn.prepareStatement(Query.FETCH_MTASK_ID);
            ps.setInt(1,em.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                al.add(rs.getInt("id"));
            }
        }
        catch(SQLException e){
            System.out.println("Selection Failed : "+e);
        }
        return al;
    }
}
