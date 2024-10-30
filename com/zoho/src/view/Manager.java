package src.view;

import src.controller.*;
import src.model.Employee;
import src.model.ObjectCreation;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Manager {
    Scanner in = ObjectCreation.getInstanceofScanner();
    public void manager(Employee em)
    {
        Updation up = new Updation();
        int count = up.getTaskStatusofManager(em);
        System.out.println("!+===============================================+!\n");
        System.out.println("Welcome Manager !! "+em.getFirstName()+" "+em.getLastName()+" 🤩️\n");
        if(count > 0) {
            System.out.println("\n| --**  Notification 🔔️ : "+count+" Tasks had Completed Successfully 🎉️  **--|");
            System.out.println("\n| --**  Check Your Assigned Tasks Tab ☑️   **-- |");
        }
        try {
            while(true) {
            System.out.println("\nChoose your Choice : \n");
            System.out.println("0. Logout");
            System.out.println("1. Your Profile");
            System.out.println("2. Change Login Credentials");
            System.out.println("3. Show Team Leaders");
            System.out.println("4. Show Co-ordinators");
            System.out.println("5. Show Employees");
            System.out.println("6. Team Profile");
            System.out.println("7. Assign Task");
            System.out.println("8. Assigned Task Status");
            System.out.println("9. Team Tasks Status");
            System.out.println("10. Task Status Updation");
            System.out.println("\n!+========================================+!");
            System.out.println("\n\n Enter your Choice : ");
            int choice = in.nextInt();
            Selection s = ObjectCreation.getInstanceofSelection();
            src.controller.Manager mg = new src.controller.Manager();
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
                    src.controller.TeamLeader tl = new src.controller.TeamLeader();
                    DisplayTeamLeader dtl = new DisplayTeamLeader();
                    dtl.Display(tl.getTeamLeaderDetailsByManager(em));
                    break;
                case 4:
                    src.controller.Coordinator cl = new src.controller.Coordinator();
                    DisplayCoordinator dcl = new DisplayCoordinator();
                    dcl.Display(cl.getCoordinatorDetails(Query.DISPLAY_COORDINATOR_BY_MANAGER, em));
                    break;
                case 5:
                    src.controller.Employee emp = new src.controller.Employee();
                    new DisplayEmployee().Display(emp.getEmployeeDetails(Query.DISPLAY_EMPLOYEE_BY_MANAGER, em));
                    break;
                case 6:
                    new DisplayTeam().Display(s.getTeamDetails(em));
                    break;
                case 7:
                    TaskHelper th = new TaskHelper();
                    th.writeTask(em);
                    break;
                case 8:
                    new DisplayManager().TaskStatusDisplay(mg.getTasksByManager(em), em);
                    break;
                case 9:
                    new Team().TeamTaskStatus(s.getTeamMembersId(em));
                    break;
                case 10:
                    new Updation_Helper().updateTaskCompletionByManager(em);
                    break;
            }
        }
        }catch (Exception n)
        {
            manager(em);
        }
    }
}
