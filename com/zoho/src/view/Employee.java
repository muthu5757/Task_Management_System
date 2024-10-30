package src.view;

import src.controller.Query;
import src.controller.Selection;
import src.controller.changeLoginCredentials;
import src.model.ObjectCreation;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Employee {
    Scanner in = ObjectCreation.getInstanceofScanner();

    public void employee (src.model.Employee em)
    {
        System.out.println("!+========================================+!\n");
        System.out.println("Welcome Team Leader !! "+em.getFirstName()+" "+em.getLastName()+" 🤩️\n");
        while(true) {
            System.out.println("\nChoose your Choice : \n");
            System.out.println("0. Logout");
            System.out.println("1. Your Profile");
            System.out.println("2. Change Login Credentials");
            System.out.println("3. Team Profile");
            System.out.println("4. Assign Tasks Yourself");
            System.out.println("5. Display Tasks Assigned to You");
            System.out.println("6. Update Task Completion");
            System.out.println("\n!+========================================+!");
            System.out.println("\n\n Enter your Choice : ");
            int choice = in.nextInt();
            Selection s = ObjectCreation.getInstanceofSelection();
            src.controller.Employee emp = new src.controller.Employee();
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
                    new DisplayTeam().Display(s.getTeamDetails(s.getManagerIdByEmployee(Query.FETCH_MANAGER_BY_EMPLOYEE, em)));
                    break;
                case 4:
                    TaskHelper th = new TaskHelper();
                    th.writeTaskByEmployee(em);
                    break;
                case 5:
                    new DisplayEmployee().TaskDisplay(emp.getTasksToEmployee(em, Query.DISPLAY_TASK_TO_EMPLOYEE), em, Query.DISPLAY_TASK_TO_EMPLOYEE);
                    break;
                case 6:
                    new Updation_Helper().updateTaskCompletion(em);
                    break;
            }
        }catch (NoSuchElementException n)
            { }
        }
    }
}
