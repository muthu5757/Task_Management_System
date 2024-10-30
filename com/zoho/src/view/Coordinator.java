package src.view;

import src.controller.Query;
import src.controller.Selection;
import src.controller.changeLoginCredentials;
import src.model.Employee;
import src.model.ObjectCreation;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Coordinator {
    Scanner in = ObjectCreation.getInstanceofScanner();

    public void Coordinator (Employee em)
    {
        System.out.println("!+========================================+!\n");
        System.out.println("Welcome Co-Ordinator !! "+em.getFirstName()+" "+em.getLastName()+" 🤩️\n");
        while(true) {
            System.out.println("\nChoose your Choice : \n");
            System.out.println("0. Logout");
            System.out.println("1. Your Profile");
            System.out.println("2. Change Login Credentials");
            System.out.println("3. Show Employees");
            System.out.println("4. Team Profile");
            System.out.println("5. Display Tasks Assigned to You");
            System.out.println("6. Assign Task");
            System.out.println("7. Assigned Task Status");
            System.out.println("\n!+========================================+!");
            System.out.println("\n\n Enter your Choice : ");
            int choice = in.nextInt();
            Selection s = ObjectCreation.getInstanceofSelection();
            src.controller.Coordinator cl = new src.controller.Coordinator();
            try{
            switch (choice) {
                case 0:
                    System.out.println("\n Logout Successfully ! 👋️\n");
                    return;
                case 1:
                    new Profile().displayProfile(em);
                    break;
                case 2:
                    new changeLoginCredentials().verifyEmployee();
                    break;
                case 3:
                    src.controller.Employee emp = new src.controller.Employee();
                    new DisplayEmployee().Display(emp.getEmployeeDetails(Query.DISPLAY_EMPLOYEE_BY_COORDINATOR, em));
                    break;
                case 4:
                    new DisplayTeam().Display(s.getTeamDetails(s.getManagerIdByEmployee(Query.FETCH_MANAGER_BY_COORDINATOR, em)));
                    break;
                case 5:
                    new DisplayCoordinator().TaskDisplay(cl.getTasksByCoordinator(em, Query.DISPLAY_TASK_TO_COORDINATOR), em, Query.DISPLAY_TASK_TO_COORDINATOR);
                    break;
                case 6:
                    TaskHelper th = new TaskHelper();
                    th.writeTaskByCoordinator(em);
                    break;
                case 7:
                    new DisplayCoordinator().TaskDisplay(cl.getTasksByCoordinator(em, Query.DISPLAY_TASK_BY_COORDINATOR), em, Query.DISPLAY_TASK_BY_COORDINATOR);
                    break;
            }
        }catch (NoSuchElementException n)
            { }
        }
    }
}
