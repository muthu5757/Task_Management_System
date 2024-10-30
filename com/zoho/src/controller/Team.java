package src.controller;

import src.model.ObjectCreation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Team {

    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    public void TeamTaskStatus(List<Integer> l)
    {
        Scanner in = ObjectCreation.getInstanceofScanner();
        try
        {
            System.out.println("\n ==========================================================================================================================================================================================================\n");
            System.out.println(" |                                                                                  You Assigned Tasks 👨‍💻️                                                                                               |");
            System.out.println("\n ==========================================================================================================================================================================================================\n");
            System.out.printf(" %-12s | %-12s | %-12s | %-12s | %-40s | %-70s | %-10s | %-10s |","Employee ID" ,"First Name", "Last Name", "Task Code", "Subject", "Description", "Due Date", "Status");
            System.out.println("\n ==========================================================================================================================================================================================================\n");
            for(int i=0;i< l.size();i++)
            {
                PreparedStatement ps = conn.prepareStatement(Query.FETCH_TEAM_MEMBERS_TASK);
                ps.setInt(1, (Integer) l.get(i));
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    System.out.printf(" %-12s | %-12s | %-12s | %-12s | %-40s | %-70s | %-10s | %-10s |\n",rs.getString("employee_id"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("code"), rs.getString("subject"),rs.getString("description"),rs.getString("due_date"),rs.getString("status"));
                }
            }
            System.out.println("\n ==========================================================================================================================================================================================================\n");

            while(true){
                System.out.println("\n Need Filters (Yes / No) : ");
                String choice = in.next();
                if(choice.equalsIgnoreCase("Yes"))
                {
                    new TaskFilterTeam().taskfilterteam(l);
                }
                else {
                    System.out.println("\n Need Task Report (Yes / No) : ");
                    choice = in.next();
                    if(choice.equalsIgnoreCase("Yes"))
                    {
                        new TaskWriter().writeTasksToFile(l);
                        break;
                    }else break;
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println("Display Failed : "+e);
        }
    }
}
