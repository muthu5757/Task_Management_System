package src.view;

import src.controller.Query;
import src.controller.TaskFilter;
import src.model.Employee;
import src.model.ObjectCreation;
import src.model.Task;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DisplayManager{
    public void Display(List<Employee> manager){
        Iterator<Employee> iterator = manager.listIterator();
        System.out.println("\n ===================================================================\n");
        System.out.println(" |                        My Managers List 👨‍💻️                   |");
        System.out.println("\n ====================================================================\n");
        System.out.printf(" %-20s | %-20s | %-20s | ","Employee ID" ,"First Name", "Last Name");
        System.out.println("\n=====================================================================\n");
        while(iterator.hasNext())
        {
            Employee em = iterator.next();
            System.out.printf("%-20s | %-20s | %-20s |",em.getEmployeeId(),em.getFirstName(),em.getLastName());
            System.out.println();
        }
        System.out.println("\n=====================================================================\n");
    }
    public void TaskStatusDisplay(Map<Employee, Task> task, Employee manager)
    {
        Scanner in = ObjectCreation.getInstanceofScanner();

            System.out.println("\n ==========================================================================================================================================================================================================\n");
            System.out.println(" |                                                                                  You Assigned Tasks 👨‍💻️                                                                                               |");
            System.out.println("\n ==========================================================================================================================================================================================================\n");
            System.out.printf(" %-12s | %-12s | %-12s | %-12s | %-40s | %-70s | %-10s | %-10s |","Employee ID" ,"First Name", "Last Name", "Task Code", "Subject", "Description", "Due Date", "Status");
            System.out.println("\n ==========================================================================================================================================================================================================\n");

            for(Map.Entry m : task.entrySet()){
                System.out.printf(" %-12s | %-12s | %-12s | %-12s | %-40s | %-70s | %-10s | %-10s |\n",((Employee) m.getKey()).getEmployeeId(),((Employee) m.getKey()).getFirstName(),((Employee) m.getKey()).getLastName(),((Task) m.getValue()).getCode(), ((Task) m.getValue()).getSubject(),((Task) m.getValue()).getDescription(),((Task) m.getValue()).getDueDate(),((Task) m.getValue()).getStatus());
            }
            System.out.println("\n ==========================================================================================================================================================================================================\n");

            while(true){
                System.out.println("\n Need Filters (Yes / No) : ");
                String choice = in.next();
                if(choice.equalsIgnoreCase("Yes"))
                {
                    new DisplayTaskFilter().Display(new TaskFilter().taskfilter(Query.DISPLAY_TASK_BY_MANAGER,manager));
                }
                else break;
            }
        }

    }