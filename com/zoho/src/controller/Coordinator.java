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

public class Coordinator {
    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    public void addCoordinator(Employee em,Employee tl, Employee mg)
    {
        try
        {
            PreparedStatement ps = conn.prepareStatement(Query.INSERT_COORDINATOR);
            ps.setInt(1, mg.getId());
            ps.setInt(2, tl.getId());
            ps.setInt(3,em.getId());
            ps.executeUpdate();
            System.out.println("\nCo-Ordinator Allocated Successfully ! 👍️");
        }
        catch(SQLException e)
        {
            System.out.println("Insertion Failed : "+e);
        }
    }
    public List<Employee> getCoordinatorDetails(String Query, Employee em) {
        List<Employee> coordinator = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setInt(1, em.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                coordinator.add(new Employee(rs.getInt("id"), rs.getString("employee_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email_id"), rs.getString("mobile_number")));
            }
        } catch (SQLException e) {
            System.out.println("Display Failed : " + e);
        }
        return coordinator;
    }
    public Map<Employee, Task> getTasksByCoordinator(Employee coordinator, String Query)
    {
        Map<Employee,Task> task = new LinkedHashMap<>();
        try
        {
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setInt(1, coordinator.getId());
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
