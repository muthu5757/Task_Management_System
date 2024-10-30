package src.view;

import src.controller.*;
import src.controller.Coordinator;
import src.model.Employee;
import src.model.ObjectCreation;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class TeamLeader {
    Scanner in = ObjectCreation.getInstanceofScanner();

    public void teamleader (Employee em)
    {
        Updation up = new Updation();
        int count = up.getTaskStatusofTeamLeader(em);
        System.out.println("!+========================================+!\n");
        System.out.println("Welcome Team Leader !! "+em.getFirstName()+" "+em.getLastName()+" 🤩️\n");
        if(count > 0) {
            System.out.println("\n| --**  Notification 🔔️ : "+count+" Tasks had Completed Successfully 🎉️  **--|");
            System.out.println("\n| --**  Check Your Assigned Tasks Tab ☑️   **-- |");
        }
        while(true)
        {
            System.out.println("\nChoose your Choice : \n");
            System.out.println("0. Logout");
            System.out.println("1. Your Profile");
            System.out.println("2. Change Login Credentials");
            System.out.println("3. Show Co-ordinators");
            System.out.println("4. Show Employees");
            System.out.println("5. Team Profile");
            System.out.println("6. Display Tasks Assigned to You");
            System.out.println("7. Assign Task");
            System.out.println("8. Assigned Task Status");
            System.out.println("\n!+========================================+!");
            System.out.println("\n\n Enter your Choice : ");
            int choice = in.nextInt();
            Selection s = ObjectCreation.getInstanceofSelection();
            src.controller.TeamLeader tl = new src.controller.TeamLeader();
            try {
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
                        Coordinator cl = new Coordinator();
                        DisplayCoordinator dcl = new DisplayCoordinator();
                        dcl.Display(cl.getCoordinatorDetails(Query.DISPLAY_COORDINATOR_BY_TEAMLEADER, em));
                        break;
                    case 4:
                        src.controller.Employee emp = new src.controller.Employee();
                        new DisplayEmployee().Display(emp.getEmployeeDetails(Query.DISPLAY_EMPLOYEE_BY_TEAMLEADER, em));
                        break;
                    case 5:
                        new DisplayTeam().Display(s.getTeamDetails(s.getManagerIdByEmployee(Query.FETCH_MANAGER_BY_TEAMLEADER, em)));
                        break;
                    case 6:
                        new DisplayTeamLeader().TaskDisplay(tl.getTasksByTeamLeader(em, Query.DISPLAY_TASK_TO_TEAMLEADER), em, Query.DISPLAY_TASK_TO_TEAMLEADER);
                        break;
                    case 7:
                        TaskHelper th = new TaskHelper();
                        th.writeTaskByTeamLeader(em);
                        break;
                    case 8:
                        new DisplayTeamLeader().TaskDisplay(tl.getTasksByTeamLeader(em, Query.DISPLAY_TASK_BY_TEAMLEADER), em, Query.DISPLAY_TASK_BY_TEAMLEADER);
                        break;
                }
            }
            catch (NoSuchElementException n)
            { }
        }
    }
}
