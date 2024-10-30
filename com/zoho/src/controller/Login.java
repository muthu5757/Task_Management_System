package src.controller;
import src.model.ObjectCreation;
import src.model.Employee;

import java.io.Console;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Login {

    public Employee login()
    {
        Scanner in = ObjectCreation.getInstanceofScanner();
        Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
        String role;
        try {
            System.out.println("\n!+*****************************************+!\n");
            System.out.println("Login : ");
            in.nextLine();
            System.out.println("\nEnter your Username : ");
            String username = in.nextLine();

            PreparedStatement ps = conn.prepareStatement(Query.FETCH_PASSWORD);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                int id = rs.getInt("employee_id");
                String storedPassword = rs.getString("password").trim();
                String inputPassword;
                int times = 0;

                while (true) {
                    Console console = System.console();
                    if (console == null) {
                        System.out.println("No console available. Please run in a terminal.");
                        return null;
                    }
                    if(times > 3)
                    {
                        System.out.println("\n TimeLimit Exceed.... Try Again Later..!! \n");
                    }
                    char[] passwordArray = console.readPassword("\nEnter your password: \n");
                    inputPassword = new String(passwordArray).trim();
                    if (!storedPassword.equals(inputPassword)) {
                        System.out.println("Incorrect password. Please try again.");
                        ++times;
                    }
                    else {

                        ps = conn.prepareStatement(Query.FETCH_ROLE);
                        ps.setInt(1, id);
                        rs = ps.executeQuery();
                        if(rs.next())
                            role =  rs.getString("name");
                        else {
                            System.out.println("No Role for this Employee !. Please set role and proceed again !");
                            return null;
                        }
                        ps = conn.prepareStatement(Query.FETCH_EMPLOYEE_DETAILS_BY_ID);
                        ps.setInt(1, id);
                        rs = ps.executeQuery();
                        if(rs.next())
                        {
                            Employee em = new Employee(id,rs.getString("employee_id"),rs.getString("firstname"),rs.getString("lastname"),rs.getString("email_id"), rs.getString("mobile_number"),rs.getString("hire_date"),rs.getString("designation_name"),role);
                            System.out.println(" \nLogin Successfully !  👍️\n");
                            return em;
                        }
                    }
                }
            }
            else
            {
                System.out.println("\n InCorrect Username !\n");
            }
        } catch (SQLException e) {
            System.out.println("SignIn failed: " + e.getMessage());
        }
        return null;
    }
}
