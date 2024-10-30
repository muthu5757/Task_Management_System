package src.controller;

import src.model.Employee;
import src.model.ObjectCreation;
import src.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class TaskFilter {

    Connection conn = ObjectCreation.getInstanceofDatabaseConnection();

    public Map<Employee, Task> taskfilter(String Query, Employee em) {
        Map<Employee, Task> task = new LinkedHashMap<>();
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
            String status = sc.nextLine();

            int indexOfQuestionMark = Query.indexOf('?');
            if (indexOfQuestionMark != -1) {
                Query = Query.substring(0, indexOfQuestionMark + 1);
            }

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

            PreparedStatement ps = conn.prepareStatement(query.toString());
            ps.setInt(1, em.getId());

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
                Employee emp = new Employee(rs.getString("employee_id"),rs.getString("firstname"),rs.getString("lastname"));
                Task t = new Task(
                        rs.getInt("id"),rs.getString("code"), rs.getString("subject"),rs.getString("description"),rs.getString("due_date"),rs.getString("status"));
                task.put(emp,t);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        return task;
    }
}
