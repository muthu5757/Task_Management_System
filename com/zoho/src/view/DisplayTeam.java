package src.view;

import src.model.Employee;

import java.util.Iterator;
import java.util.List;

public class DisplayTeam {
    public void Display(List<Employee> team){
        Iterator<Employee> iterator = team.listIterator();
        System.out.println("\n ===========================================================================================\n");
        System.out.println(" |                               Team Profile 👨‍💻️                   |");
        System.out.println("\n ============================================================================================\n");
        System.out.printf(" %-20s | %-20s | %-20s | %-20s |","Employee ID" ,"First Name", "Last Name", "Role");
        System.out.println("\n=============================================================================================\n");
        while(iterator.hasNext())
        {
            Employee em = iterator.next();
            System.out.printf("%-20s | %-20s | %-20s | %-20s |",em.getEmployeeId(),em.getFirstName(),em.getLastName(),em.getRole());
            System.out.println();
        }
        System.out.println("\n==============================================================================================\n");
    }
}
