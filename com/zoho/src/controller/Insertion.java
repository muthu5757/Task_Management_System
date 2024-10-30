package src.controller;

import src.model.Department;
import src.model.ObjectCreation;
import src.model.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class Insertion {
    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    public void roleInsertion(Role r)
    {
        try{
            PreparedStatement ps = conn.prepareStatement(Query.INSERT_ROLE);
            ps.setString(1, r.getRole());
            ps.executeUpdate();
            System.out.println("\nRole Inserted Successfully ! 👍️");
        }
        catch(SQLException e)
        {
            System.out.println("Insertion Failed : "+e);
        }
    }
    public void departmentInsertion(Department d)
    {
        try
        {
            PreparedStatement ps = conn.prepareStatement(Query.INSERT_DEPARTMENT);
            ps.setString(1, d.getDepartment());
            ps.executeUpdate();
            System.out.println("\nDepartment Inserted Successfully ! 👍️");
        }
        catch(SQLException e)
        {
            System.out.println("Insertion Failed : "+e);
        }
    }
}
