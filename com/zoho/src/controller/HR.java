package src.controller;

import src.model.Employee;
import src.model.ObjectCreation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HR {
    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    public void addHR(Employee hr, Employee em)
    {
        try
        {
            PreparedStatement ps = conn.prepareStatement(Query.INSERT_HR);
            ps.setInt(1, em.getId());
            ps.setInt(2,hr.getId());
            ps.executeUpdate();
            System.out.println("\nHR Allocated Successfully ! 👍️");
        }
        catch(SQLException e)
        {
            System.out.println("Insertion Failed : "+e);
        }
    }
    public List<Employee> getHRDetailsByAdmin(Employee em) {
        List<Employee> hr = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(Query.DISPLAY_HR);
            ps.setInt(1, em.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hr.add(new Employee(rs.getInt("id"), rs.getString("employee_id"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("email_id"), rs.getString("mobile_number")));
            }
        } catch (SQLException e) {
            System.out.println("Display Failed : " + e);
        }
        return hr;
    }
}
