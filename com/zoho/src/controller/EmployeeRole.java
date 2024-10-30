package src.controller;

import src.model.Employee;
import src.model.ObjectCreation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class EmployeeRole {
    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    public void setEmployeeRole(Employee em)
    {
        try {
                PreparedStatement ps2 = conn.prepareStatement(Query.FETCH_DEPT_ROLE_ID);
                ps2.setString(1, em.getDepartment());
                ps2.setString(2,em.getRole());
                ResultSet rs2 = ps2.executeQuery();
                if(rs2.next()){
                    int dept = rs2.getInt("dept");
                    int role_id = rs2.getInt("role");
                    PreparedStatement s = conn.prepareStatement(Query.EMPLOYEE_ISROLE);
                    s.setInt(1, em.getId());
                    ResultSet r = s.executeQuery();
                    if(r.next())
                    {
                        PreparedStatement ps3 = conn.prepareStatement(Query.UPDATE_EMPLOYEE_ROLE);
                        ps3.setInt(1,dept);
                        ps3.setInt(2, role_id);
                        ps3.setInt(3, em.getId());
                        ps3.executeUpdate();
                        System.out.println("\nEmployee's Role Updated Successfully ! 👍️\n");
                    }
                    else
                    {
                        PreparedStatement ps3 = conn.prepareStatement(Query.INSERT_EMPLOYEE_ROLE);
                        ps3.setInt(1, em.getId());
                        ps3.setInt(2,dept);
                        ps3.setInt(3, role_id);
                        ps3.executeUpdate();
                        System.out.println("\nEmployee's Role Inserted Successfully ! 👍️\n");
                    }
                }
                else{
                    System.out.println("No department or Role on given datas. ");
                    return;
                }
        } catch (SQLException e) {
            System.out.println("Role Insertion Failed: " + e.getMessage());
        }
    }
}
