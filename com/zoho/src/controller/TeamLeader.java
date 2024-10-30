package src.controller;

import src.model.Employee;
import src.model.ObjectCreation;
import src.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TeamLeader {

    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    public void addTeamLeader(Employee tl, Employee mg)
    {
        try
        {
            PreparedStatement ps = conn.prepareStatement(Query.INSERT_TEAMLEADER);
            ps.setInt(1, mg.getId());
            ps.setInt(2,tl.getId());
            ps.executeUpdate();
            System.out.println("\nTeam Leader Allocated Successfully ! 👍️");
        }
        catch(SQLException e)
        {
            System.out.println("Insertion Failed : "+e);
        }
    }
    public List<Employee> getTeamLeaderDetailsByManager(Employee em) {
        List<Employee> teamleader = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(Query.DISPLAY_TEAMLEADER);
            ps.setInt(1, em.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                teamleader.add(new Employee(rs.getInt("id"), rs.getString("employee_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email_id"), rs.getString("mobile_number")));
            }
        } catch (SQLException e) {
            System.out.println("Display Failed : " + e);
        }
        return teamleader;
    }
    public Map<Employee, Task> getTasksByTeamLeader(Employee teamleader, String Query)
    {
        Map<Employee,Task> task = new LinkedHashMap<>();
        try
        {
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setInt(1, teamleader.getId());
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
