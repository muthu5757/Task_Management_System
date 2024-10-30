package src.controller;

import src.model.ObjectCreation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TaskWriter {

    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    public void writeTasksToFile(List<Integer> l){
        String fileName = "output.csv";

        try (FileWriter fw = new FileWriter(fileName);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {

            pw.println("Employee ID,First Name,Last Name,Task Code,Subject,Description,Due Date,Status");

            for (int i = 0; i < l.size(); i++) {
                PreparedStatement ps = conn.prepareStatement(Query.FETCH_TEAM_MEMBERS_TASK);
                ps.setInt(1, (Integer) l.get(i));
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    pw.printf("%s,%s,%s,%s,%s,%s,%s,%s\n",
                            rs.getString("employee_id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("code"),
                            rs.getString("subject"),
                            rs.getString("description").replace(",", ""),
                            rs.getString("due_date"),
                            rs.getString("status"));
                }
            }

            System.out.println("\n Report Generated Successfully ! 📑️ ");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: " + e.getMessage());
        }
    }
}
