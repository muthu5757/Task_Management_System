package src.view;

import src.model.Employee;

import java.util.Iterator;
import java.util.List;

public class DisplayHR {

    public void Display(List<Employee> hr){
        Iterator<Employee> iterator = hr.listIterator();
        System.out.println("\n ===================================================================\n");
        System.out.println(" |                              My HRs List 👨‍💻️                   |");
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
}
