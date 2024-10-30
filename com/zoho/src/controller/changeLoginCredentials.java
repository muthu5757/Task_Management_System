package src.controller;

import src.model.ObjectCreation;
import src.view.Checks;

import java.io.Console;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class changeLoginCredentials {

    Scanner in = ObjectCreation.getInstanceofScanner();
    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    Checks c = new Checks();
    public void verifyEmployee(){
        System.out.println("\n Enter your Username : ");
        String username = in.next();
        try  {
            PreparedStatement ps = conn.prepareStatement(Query.FETCH_PASSWORD);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                int id = rs.getInt("employee_id");
                String storedPassword = rs.getString("password").trim();
                String inputPassword;

                Console console = System.console();
                if (console == null) {
                    System.out.println("No console available. Please run in a terminal.");
                    return;
                }

                char[] passwordArray = console.readPassword("\nEnter your password: \n");
                inputPassword = new String(passwordArray).trim();
                if (storedPassword.equals(inputPassword)) {
                    while(true){
                        System.out.println("\n 1. Change Username ");
                        System.out.println("\n 2. Change Password ");
                        System.out.println("\n 3. Change Both Username and Password ");
                        System.out.println("\n 4. Go Back ");
                        System.out.println("\nEnter Your Choice : ");
                        int ch = in.nextInt();
                        if(ch == 1){
                            setUsername(id);
                            return;
                        }
                        else if(ch==2){
                            setPassword(id);
                            return;
                        }
                        else if(ch==3){
                            setUsername(id);
                            setPassword(id);
                            return;
                        }
                        else if(ch==4)
                            return;
                        else
                            System.out.println("\n Invalid Choice ! ");
                    }
                }
                else{
                    System.out.println("\n Username or Password was Incorrect ! ❌️ ");
                    System.out.println("\n Try Again Later 🔁️ ");
                    return;
                }
            }
            else
            {
                System.out.println("Username not Found !\n");
            }
        } catch (SQLException e) {
            System.out.println("SignIn failed: " + e.getMessage());
        }
    }
    private void setUsername(int id)
    {
        String username;
        while(true) {
            System.out.println("\nEnter your New Username (Don't include any WhiteSpaces): ");
            username = in.next();
            if (c.isValidUsername(username)) {
                break;
            } else {
                System.out.println("\n Invalid username. Username must contain only alphabets or alphabets with numbers. Try Again !");
            }
        }
        try  {
            PreparedStatement ps = conn.prepareStatement(Query.SET_USERNAME);
            ps.setString(1, username);
            ps.setInt(2,id);
            ps.executeUpdate();
            System.out.println("\n Your Username Changed Successfully ! 👍️ ");
        }
        catch (SQLException e) {
            System.out.println("SignIn failed: " + e.getMessage());
        }
    }
    private void setPassword(int id)
    {
        String password;
        while(true) {
            System.out.println("\nEnter your New Password (Don't include any WhiteSpaces): ");
            password = in.next();
            if (c.isValidPassword(password)) {
                break;
            } else {
                System.out.println("\n Invalid password. Password must be at least 8 characters long, contain at least one number, and one special symbol. Try Again ! ");
            }
        }
        try  {
            PreparedStatement ps = conn.prepareStatement(Query.SET_PASSWORD);
            ps.setString(1, password);
            ps.setInt(2,id);
            ps.executeUpdate();
            System.out.println("\n Your Password Changed Successfully ! 👍️ ");
        }
        catch (SQLException e) {
            System.out.println("SignIn failed: " + e.getMessage());
        }
    }
}
