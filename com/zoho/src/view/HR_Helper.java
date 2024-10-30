package src.view;

import src.controller.*;
import src.model.Employee;
import src.model.ObjectCreation;

public class HR_Helper extends SuperAdmin_Helper {

    public Employee setEmployeeRoleByHR(Employee em)
    {
        System.out.println("\n!+*****************************************+!\n");
        System.out.println("\nInserting Employee Role :");
        if(em==null) {
            Selection s = ObjectCreation.getInstanceofSelection();
            System.out.println("\nEnter his/her Employee ID : ");
            String empId = in.next();
            em = s.getDetailsofEmployee(empId);
        }
        in.nextLine();
        System.out.println("\nEnter his/her Employee Department : ");
        String empdept = in.nextLine();
        System.out.println("\nEnter to Set Employee Role(Manager / TeamLeader / Coordinator) : ");
        String role = in.nextLine();
        if(role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("superadmin") || role.equalsIgnoreCase("hr"))
        {
            System.out.println("You Not Have a Permission to Set Role of "+role);
            return null;
        }
        em.setRoleandDepartment(role,empdept);
        new EmployeeRole().setEmployeeRole(em);
        System.out.println("!+*****************************************+!\n");
        return em;
    }
    public void addMemberToTeam(Employee em, Employee hr) {
        src.controller.Manager mg = new src.controller.Manager();
        src.controller.TeamLeader tl = new src.controller.TeamLeader();
        src.controller.Coordinator cl =new src.controller.Coordinator();
        src.controller.Employee emp = new src.controller.Employee();
        DisplayTeamLeader dtl = new DisplayTeamLeader();
        DisplayCoordinator dcl= new DisplayCoordinator();
        Selection s = ObjectCreation.getInstanceofSelection();
        System.out.println("\nAdd a New Member to a Team : ");

        String role = s.getEmployeeRole(em);
        if (role == null) return;
        System.out.println("\nChoose the Manager : ");
        new DisplayManager().Display(mg.getManagerDetailsByHR(hr));
        System.out.println("\n Enter the Manager's Employee ID : ");
        String managerId = in.next();
        Employee manager = s.getDetailsofEmployee(managerId);
        System.out.println("\n Based on his Role : ");
        String teamLeaderId;
        Employee teamleader;
        while (true) {
            switch (role.toLowerCase()) {
                case "teamleader":
                    tl.addTeamLeader(em,manager);
                    return;
                case "coordinator":
                    System.out.println("\nUnder Which Team Leader : ");
                    dtl.Display(tl.getTeamLeaderDetailsByManager(manager));
                    System.out.println("\n Enter the Team Leader's Employee ID : ");
                    teamLeaderId = in.next();
                    teamleader = s.getDetailsofEmployee(teamLeaderId);
                    cl.addCoordinator(em, teamleader, manager);
                    return;
                case "employee":
                    System.out.println("\nUnder Which Team Leader : ");
                    dtl.Display(tl.getTeamLeaderDetailsByManager(manager));
                    System.out.println("\n Enter the Team Leader's Employee ID : ");
                    teamLeaderId = in.next();
                    teamleader = s.getDetailsofEmployee(teamLeaderId);
                    System.out.println("\n Under which Co-Ordinator : ");
                    dcl.Display(cl.getCoordinatorDetails(Query.DISPLAY_COORDINATOR_BY_TEAMLEADER, teamleader));
                    System.out.println("\n Enter the Co-Ordinator's Employee ID : ");
                    String coId = in.next();
                    Employee coordinator = s.getDetailsofEmployee(coId);
                    emp.addEmployee(em, coordinator, teamleader, manager);
                    return;
                default:
                    System.out.println("You Don't Have the Permission Made Changes by this Role - " + role);
                    return;
            }
        }
    }
}
