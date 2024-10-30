package src.controller;

import src.model.ObjectCreation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TaskFilterTeam {
    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();
    public void taskfilterteam(List<Integer> teamIds) {
        boolean available = false;

        String Query = "select e.employee_id, e.firstname, e.lastname,hl.code, hl.subject, hl.description, hl.due_date, hl.status from employee e join tasks hl on hl.employee = e.id where employee = ? ";
        try {
            Scanner sc = ObjectCreation.getInstanceofScanner();
            sc.nextLine();

            System.out.println("\nEnter Employee ID to filter (or press Enter to skip): ");
            String employeeId = sc.nextLine();

            System.out.println("\nEnter Subject to filter (or press Enter to skip): ");
            String subject = sc.nextLine();

            System.out.println("\nEnter Due Date (YYYY-MM-DD) to filter (or press Enter to skip): ");
            String dueDate = sc.nextLine();

            System.out.println("\nEnter Status to filter (or press Enter to skip): ");
            String status = sc.nextLine().toUpperCase();

            System.out.println("\n =========================================================================================================================================================================================================\n");
            System.out.println(" |                                                                                  You Assigned Tasks 👨‍💻️                                                                                               |");
            System.out.println("\n =========================================================================================================================================================================================================\n");
            System.out.printf(" %-12s | %-12s | %-12s | %-12s | %-40s | %-70s | %-10s | %-10s |", "Employee ID", "First Name", "Last Name", "Task Code", "Subject", "Description", "Due Date", "Status");
            System.out.println("\n =========================================================================================================================================================================================================\n");

            StringBuilder query = new StringBuilder(Query);

            if (!employeeId.isEmpty()) {
                query.append(" AND employee_id = ?");
            }
            if (!subject.isEmpty()) {
                query.append(" AND LOWER(subject) LIKE ?");
            }
            if (!dueDate.isEmpty()) {
                query.append(" AND due_date = cast(? as date)");
            }
            if (!status.isEmpty()) {
                query.append(" AND status = ?::new_task_status");
            }

            for (Integer teamId : teamIds) {

                PreparedStatement ps = conn.prepareStatement(query.toString());
                ps.setInt(1, teamId);

                int paramIndex = 2;

                if (!employeeId.isEmpty()) {
                    ps.setString(paramIndex++, employeeId);
                }
                if (!subject.isEmpty()) {
                    ps.setString(paramIndex++, "%" + subject.toLowerCase() + "%");
                }
                if (!dueDate.isEmpty()) {
                    ps.setString(paramIndex++, dueDate);
                }
                if (!status.isEmpty()) {
                    ps.setString(paramIndex++, status.toUpperCase());
                }

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    available = true;
                    System.out.printf(" %-12s | %-12s | %-12s | %-12s | %-40s | %-70s | %-10s | %-10s |\n",
                            rs.getString("employee_id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("code"),
                            rs.getString("subject"),
                            rs.getString("description"),
                            rs.getString("due_date"),
                            rs.getString("status"));
                }
            }

            if (!available)
                System.out.println("\t\t\t\t\t\t\t\t --------------- No Tasks Available ! ------------------ ");

            System.out.println("\n =========================================================================================================================================================================================================\n");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
