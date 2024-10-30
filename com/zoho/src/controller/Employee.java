package src.controller;

import src.model.ObjectCreation;
import src.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Employee {
    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    public void addEmployee(src.model.Employee em, src.model.Employee cl, src.model.Employee tl, src.model.Employee mg)
    {
        try
        {
            PreparedStatement ps = conn.prepareStatement(Query.INSERT_EMPLOYEE);
            ps.setInt(1, mg.getId());
            ps.setInt(2,tl.getId());
            ps.setInt(3, cl.getId());
            ps.setInt(4,em.getId());
            ps.executeUpdate();
            System.out.println("\nEmployee Allocated Successfully ! 👍️");
        }
        catch(SQLException e)
        {
            System.out.println("Insertion Failed : "+e);
        }
    }
    public List<src.model.Employee> getEmployeeDetails(String Query, src.model.Employee em) {
        List<src.model.Employee> employee = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setInt(1, em.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employee.add(new src.model.Employee(rs.getInt("id"), rs.getString("employee_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email_id"), rs.getString("mobile_number")));
            }
        } catch (SQLException e) {
            System.out.println("Display Failed : " + e);
        }
        return employee;
    }
    public Map<src.model.Employee, Task> getTasksToEmployee(src.model.Employee coordinator, String Query)
    {
        Map<src.model.Employee,Task> task = new LinkedHashMap<>();
        try
        {
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setInt(1, coordinator.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                src.model.Employee em = new src.model.Employee(rs.getString("employee_id"),rs.getString("firstname"),rs.getString("lastname"));
                Task t = new Task(
                        rs.getInt("id"),rs.getString("code"), rs.getString("subject"),rs.getString("description"),rs.getString("due_date"),rs.getString("status"));
                task.put(em,t);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Display Failed : "+e);
        }
        return task;
    }
}
