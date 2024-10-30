package src.controller;

import src.model.Employee;
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

public class Manager {
    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    public void addManager(Employee mg, Employee em)
    {
        try
        {
            PreparedStatement ps = conn.prepareStatement(Query.INSERT_MANAGER);
            ps.setInt(1, em.getId());
            ps.setInt(2,mg.getId());
            ps.executeUpdate();
            System.out.println("\nManager Allocated Successfully ! 👍️");
        }
        catch(SQLException e)
        {
            System.out.println("Insertion Failed : "+e);
        }
    }
    public List<Employee> getManagerDetailsByHR(Employee em) {
        List<Employee> manager = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(Query.DISPLAY_MANAGER);
            ps.setInt(1, em.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                manager.add(new Employee(rs.getInt("id"), rs.getString("employee_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email_id"), rs.getString("mobile_number")));
            }
        } catch (SQLException e) {
            System.out.println("Display Failed : " + e);
        }
        return manager;
    }

    public Map<Employee,Task> getTasksByManager(Employee manager)
    {
        Map<Employee,Task> task = new LinkedHashMap<>();
        try
        {
            PreparedStatement ps = conn.prepareStatement(Query.DISPLAY_TASK_BY_MANAGER);
            ps.setInt(1, manager.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Employee em = new Employee(rs.getString("employee_id"),rs.getString("firstname"),rs.getString("lastname"));
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
