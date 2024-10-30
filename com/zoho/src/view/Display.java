package src.view;

import src.model.Department;
import src.model.Employee;
import src.model.Role;

import java.util.Iterator;
import java.util.List;

public class Display {

    public void displayRoleWithEmployee(List<Employee> l)
    {
        Iterator<Employee> iterator = l.listIterator();
        System.out.println("\n=============================================================================================================================================== \n");
        System.out.println("|                                          Employees Role and Department : 🏬️                                                      |");
        System.out.println("\n===============================================================================================================================================\n");
        System.out.printf("%-20s | %-20s | %-20s | %-30s | %-20s | %-20s","Employee ID" ,"First Name", "Last Name", "Designation","Department" ,"Roles");
        System.out.println("\n===============================================================================================================================================\n");
        while(iterator.hasNext())
        {
            Employee em = iterator.next();
            System.out.printf("%-20s | %-20s | %-20s | %-30s | %-20s | %-20s",em.getEmployeeId(),em.getFirstName(),em.getLastName(),em.getDesignation(),em.getDepartment(),em.getRole());
            System.out.println();
        }
        System.out.println("\n================================================================================================================================================\n");
    }
    public void displayRoles(List<Role> l)
    {
        Iterator<Role> iterator = l.listIterator();
        System.out.println("\n ================================================================= \n");
        System.out.println("|                     Employees Roles : 🏬️                |");
        System.out.println("\n ==================================================================\n");
        System.out.printf(" %-20s  %s","","Roles");
        System.out.println("\n===================================================================\n");
        while(iterator.hasNext())
        {
            Role em = iterator.next();
            System.out.printf(" %-20s %s","",em.getRole());
            System.out.println();
        }
        System.out.println("\n ==================================================================\n");
    }
    public void displayDepartment(List<Department> l)
    {
        Iterator<Department> iterator = l.listIterator();
        System.out.println("\n ============================================================== \n");
        System.out.println("|                    Employees Departments : 🏬️           |");
        System.out.println("\n ===============================================================\n");
        System.out.printf(" %-20s  %s","","Department");
        System.out.println("\n=================================================================\n");
        while(iterator.hasNext())
        {
            Department em = iterator.next();
            System.out.printf(" %-20s  %s","",em.getDepartment());
            System.out.println();
        }
        System.out.println("\n =================================================================\n");
    }
}
