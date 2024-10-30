package src.controller;

import src.model.Employee;
import src.model.ObjectCreation;
import src.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskAssignment {

    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    public void sendTask(Employee s, Employee To, Task task, int parent_task, String query)
    {
        try{
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            if(parent_task==0) {
                ps.setNull(1, java.sql.Types.INTEGER);
            }
            else {
                ps.setInt(1, parent_task);
            }
            ps.setInt(2, s.getId());
            ps.setInt(3, To.getId());
            ps.setString(4,task.getCode());
            ps.setString(5, task.getSubject());
            ps.setString(6, task.getDescription());
            ps.setString(7, task.getDueDate());
            ps.setString(8, task.getPriority());
            ps.executeUpdate();
            System.out.println("\n Task Assigned Successfully ! 🎉️ ");
        }
        catch(SQLException e)
        {
            System.out.println("Task Assigning Failed : "+e);
        }
    }
}
