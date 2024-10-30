package src.controller;

import src.model.Employee;
import src.model.ObjectCreation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUp {
    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    public Employee signup(Employee em, String username, String password)
    {
        int desId=0;
        try{
            PreparedStatement p = conn.prepareStatement(Query.DESIGNATION_ID);
            p.setString(1,em.getDesignation());
            ResultSet r = p.executeQuery();
            if(r.next()){
                desId = r.getInt("id");
            }
            else
            {
                PreparedStatement pss = conn.prepareStatement(Query.INSERT_DESIGNATION_GET_ID);
                pss.setString(1,em.getDesignation());
                ResultSet rss = pss.executeQuery();
                if(rss.next())
                    desId = rss.getInt("id");
            }
            PreparedStatement ps = conn.prepareStatement(Query.EMPLOYEE_INSERT);
            ps.setString(1,em.getEmployeeId());
            ps.setString(2,em.getFirstName());
            ps.setString(3,em.getLastName());
            ps.setString(4,em.getEmail());
            ps.setString(5,em.getMobileNumber());
            ps.setString(6,em.getHireDate());
            ps.setInt(7,desId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                PreparedStatement ps2 = conn.prepareStatement(Query.EMPLOYEE_CREDENTIAL_INSERT);
                ps2.setInt(1, id);
                ps2.setString(2,username);
                ps2.setString(3,password);
                ps2.executeUpdate();
                em.setId(id);
                System.out.println(" \n 🎉️ Employee's Account Created Successfully ! 🎉️\n");
            }
        } catch (SQLException e) {
            System.out.println("Signup failed: " + e.getMessage());
        }
        return em;
    }
}
