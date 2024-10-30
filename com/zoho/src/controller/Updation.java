package src.controller;

import src.model.Employee;
import src.model.ObjectCreation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
public class Updation {
    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();

    public int getTaskStatusofTeamLeader(Employee em)
    {
        int count = 0;
        Selection s = ObjectCreation.getInstanceofSelection();
        List l = s.getTeamLeaderTaskId(em);
        for(int i=0;i<l.size();i++)
        {
            try{
                PreparedStatement ps = conn.prepareStatement(Query.FETCH_TASK_ID_COUNT_1);
                ps.setInt(1,(Integer) l.get(0));
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    int countId = rs.getInt("count");
                    PreparedStatement ps2 = conn.prepareStatement(Query.FETCH_TASK_STATUS_1);
                    ps2.setInt(1,(Integer) l.get(0));
                    ResultSet rs2 = ps2.executeQuery();
                    if(rs2.next()){
                        String status;
                        int countSt = rs2.getInt("count");
                        if(countSt==0)
                            status = "ASSIGNED";
                        else if(countSt > 0 && countSt <= countId / 2)
                            status = "STARTED";
                        else if(countSt == countId){
                            status = "COMPLETED";
                            ++count;
                        }
                        else
                            status = "IN_PROGRESS";
                        PreparedStatement ps3 = conn.prepareStatement(Query.UPDATE_AUTO_STATUS1);
                        ps3.setString(1, status);
                        ps3.setInt(2,(Integer) l.get(0));
                        ps3.executeUpdate();
                    }
                }
            }
            catch(SQLException e){
                System.out.println("Selection Failed : "+e);
            }
        }
        return count;
    }
    public int getTaskStatusofManager(Employee em)
    {
        int count = 0;
        Selection s = ObjectCreation.getInstanceofSelection();
        List l = s.getManagerTaskId(em);
        for(int i=0;i<l.size();i++)
        {
            try{
                PreparedStatement ps = conn.prepareStatement(Query.FETCH_TASK_ID_COUNT_2);
                ps.setInt(1,(Integer) l.get(0));
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    int countId = rs.getInt("count");
                    PreparedStatement ps2 = conn.prepareStatement(Query.FETCH_TASK_STATUS_2);
                    ps2.setInt(1,(Integer) l.get(0));
                    ResultSet rs2 = ps2.executeQuery();
                    if(rs2.next()){
                        String status;
                        int countSt = rs2.getInt("count");
                        if(countSt==0)
                            status = "ASSIGNED";
                        else if(countSt > 0 && countSt <= countId / 2)
                            status = "STARTED";
                        else if(countSt == countId){
                            status = "COMPLETED";
                            ++count;
                        }
                        else
                            status = "IN_PROGRESS";
                        PreparedStatement ps3 = conn.prepareStatement(Query.UPDATE_AUTO_STATUS2);
                        ps3.setString(1, status);
                        ps3.setInt(2,(Integer) l.get(0));
                        ps3.executeUpdate();
                    }
                }
            }
            catch(SQLException e){
                System.out.println("Selection Failed : "+e);
            }
        }
        return count;
    }
    public void updateTask(int id, String status)
    {
        try{
            PreparedStatement ps3 = conn.prepareStatement(Query.UPDATE_TASK_STATUS);
            ps3.setString(1, status);
            ps3.setInt(2,id);
            ps3.executeUpdate();
            System.out.println("\n Task Updated Successfully 👍️ ");
        }
        catch(SQLException e){
            System.out.println("Selection Failed : "+e);
        }
    }
}
