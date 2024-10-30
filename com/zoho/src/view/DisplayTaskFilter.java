package src.view;

import src.model.Employee;
import src.model.Task;

import java.util.Map;

public class DisplayTaskFilter {

    public void Display(Map<Employee, Task> task)
    {
        boolean available = false;

        System.out.println("\n =========================================================================================================================================================================================================\n");
        System.out.println(" |                                                                                  Filtered Tasks Now 👨‍💻️                                                                                               |");
        System.out.println("\n =========================================================================================================================================================================================================\n");
        System.out.printf(" %-12s | %-12s | %-12s | %-12s | %-40s | %-70s | %-10s | %-10s |", "Employee ID", "First Name", "Last Name","Task Code", "Subject", "Description", "Due Date", "Status");
        System.out.println("\n =========================================================================================================================================================================================================\n");

        for(Map.Entry m : task.entrySet()){
            available = true;
            System.out.printf(" %-12s | %-12s | %-12s | %-12s | %-40s | %-70s | %-10s | %-10s |\n",((Employee) m.getKey()).getEmployeeId(),((Employee) m.getKey()).getFirstName(),((Employee) m.getKey()).getLastName(),((Task) m.getValue()).getCode(), ((Task) m.getValue()).getSubject(),((Task) m.getValue()).getDescription(),((Task) m.getValue()).getDueDate(),((Task) m.getValue()).getStatus());
        }

        if(!available)
            System.out.println("\t\t\t\t\t\t\t\t --------------- No Tasks Available ! ------------------ ");
        System.out.println("\n =========================================================================================================================================================================================================\n");
    }
}
