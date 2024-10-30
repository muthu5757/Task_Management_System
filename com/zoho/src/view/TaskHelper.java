package src.view;

import src.controller.*;
import src.controller.Coordinator;
import src.model.Employee;
import src.model.ObjectCreation;
import src.model.Task;

import java.util.Scanner;

public class TaskHelper {
    Scanner in = ObjectCreation.getInstanceofScanner();
    public void writeTask(src.model.Employee em)
    {
        int parent_task = 0;
        Selection s = ObjectCreation.getInstanceofSelection();
        DisplayCoordinator dcl= new DisplayCoordinator();
        src.controller.Coordinator cl =new Coordinator();
        src.controller.TeamLeader tl = new src.controller.TeamLeader();
        DisplayTeamLeader dtl = new DisplayTeamLeader();
        System.out.println("\n Are you send a task based on any superior task ? (Yes / No) : ");
        String response = in.next();
        if(response.equalsIgnoreCase("Yes"))
        {
            new Team().TeamTaskStatus(s.getTeamMembersId(em));
            System.out.println("\n Enter the Task Code : ");
            String pt = in.next();
            parent_task = s.getTaskId(pt);
            System.out.println("\n Parent Task Referenced Successfully ! 👍️");
        }
        Generator g = new Generator();
        String code = g.generateCode();
        System.out.println("\n Assign Task : 👷️ ");
        System.out.println("\n Choose the Assigner 🤔️ : ");
        dtl.Display(tl.getTeamLeaderDetailsByManager(em));
        dcl.Display(cl.getCoordinatorDetails(Query.DISPLAY_COORDINATOR_BY_TEAMLEADER,em));
        src.controller.Employee emp = new src.controller.Employee();
        new DisplayEmployee().Display(emp.getEmployeeDetails(Query.DISPLAY_EMPLOYEE_BY_TEAMLEADER, em));
        System.out.println("\n Enter the Team Leader ID to Assign Task : ");
        String employee = in.next();
        in.nextLine();
        System.out.println("\n Enter Task Subject : ");
        String taskSubject = in.nextLine();
        System.out.println("\n Enter Task Description : ");
        String taskDescription = in.nextLine();
        System.out.println("\n Enter its Due Date : ");
        String duedate = in.next();
        System.out.println("\n Enter its Priority (High, Medium, Low) : ");
        String Priority = in.next();
        Task task = new Task(code,taskSubject, taskDescription, duedate, Priority.toUpperCase());
        new TaskAssignment().sendTask(em,s.getDetailsofEmployee(employee),task,parent_task,Query.WRITE_TASK); }
    public void writeTaskByTeamLeader(Employee em)
    {
        int parent_task = 0;
        src.controller.TeamLeader tl = new src.controller.TeamLeader();
        src.controller.Coordinator cl =new Coordinator();
        DisplayCoordinator dcl= new DisplayCoordinator();
        Selection s = ObjectCreation.getInstanceofSelection();
        System.out.println("\n Are you send a task based on any superior task ? (Yes / No) : ");
        String response = in.next();
        if(response.equalsIgnoreCase("Yes"))
        {
            new DisplayTeamLeader().TaskDisplay(tl.getTasksByTeamLeader(em, Query.DISPLAY_TASK_TO_TEAMLEADER),em,Query.DISPLAY_TASK_TO_TEAMLEADER);
            System.out.println("\n Enter the Task Code : ");
            String pt = in.next();
            parent_task = s.getTaskId(pt);
            System.out.println("\n Parent Task Referenced Successfully ! 👍️");
        }
        Generator g = new Generator();
        String code = g.generateCode();
        System.out.println("\n Assign Task : 👷️ ");
        System.out.println("\n Choose the Assigner 🤔️ : ");
        dcl.Display(cl.getCoordinatorDetails(Query.DISPLAY_COORDINATOR_BY_TEAMLEADER,em));
        src.controller.Employee emp = new src.controller.Employee();
        new DisplayEmployee().Display(emp.getEmployeeDetails(Query.DISPLAY_EMPLOYEE_BY_TEAMLEADER, em));
        System.out.println("\n Enter the Employee ID to Assign Task : ");
        String employee = in.next();
        in.nextLine();
        System.out.println("\n Enter Task Subject : ");
        String taskSubject = in.nextLine();
        System.out.println("\n Enter Task Description : ");
        String taskDescription = in.nextLine();
        System.out.println("\n Enter its Due Date : ");
        String duedate = in.next();
        System.out.println("\n Enter its Priority (High, Medium, Low) : ");
        String Priority = in.next();
        Task task = new Task(code, taskSubject, taskDescription, duedate, Priority.toUpperCase());
        new TaskAssignment().sendTask(em,s.getDetailsofEmployee(employee),task,parent_task,Query.WRITE_TASK);
    }
    public void writeTaskByCoordinator(Employee em)
    {
        int parent_task = 0;
        src.controller.Coordinator cl = new src.controller.Coordinator();
        Selection s = ObjectCreation.getInstanceofSelection();
        System.out.println("\n Are you send a task based on any superior task ? (Yes / No) : ");
        String response = in.next();
        if(response.equalsIgnoreCase("Yes"))
        {
            new DisplayCoordinator().TaskDisplay(cl.getTasksByCoordinator(em, Query.DISPLAY_TASK_TO_COORDINATOR),em,Query.DISPLAY_TASK_TO_COORDINATOR);
            System.out.println("\n Enter the Task Code : ");
            String pt = in.next();
            parent_task = s.getTaskId(pt);
            System.out.println("\n Parent Task Referenced Successfully ! 👍️");
        }
        Generator g = new Generator();
        String code = g.generateCode();
        System.out.println("\n Assign Task : 👷️ ");
        System.out.println("\n Choose the Employee 🤔️ : ");
        src.controller.Employee emp = new src.controller.Employee();
        new DisplayEmployee().Display(emp.getEmployeeDetails(Query.DISPLAY_EMPLOYEE_BY_COORDINATOR,em));
        System.out.println("\n Enter the Employee ID to Assign Task : ");
        String employee = in.next();
        in.nextLine();
        System.out.println("\n Enter Task Subject : ");
        String taskSubject = in.nextLine();
        System.out.println("\n Enter Task Description : ");
        String taskDescription = in.nextLine();
        System.out.println("\n Enter its Due Date : ");
        String duedate = in.next();
        System.out.println("\n Enter its Priority (High, Medium, Low) : ");
        String Priority = in.next();
        Task task = new Task(code, taskSubject, taskDescription, duedate, Priority.toUpperCase());
        new TaskAssignment().sendTask(em,s.getDetailsofEmployee(employee),task,parent_task,Query.WRITE_TASK);
    }
    public void writeTaskByEmployee(Employee em)
    {
        int parent_task = 0;
        src.controller.Coordinator cl = new src.controller.Coordinator();
        Selection s = ObjectCreation.getInstanceofSelection();
        Generator g = new Generator();
        String code = g.generateCode();
        System.out.println("\n Assign Task : 👷️ ");
        String employee = em.getEmployeeId();
        in.nextLine();
        System.out.println("\n Enter Task Subject : ");
        String taskSubject = in.nextLine();
        System.out.println("\n Enter Task Description : ");
        String taskDescription = in.nextLine();
        System.out.println("\n Enter its Due Date : ");
        String duedate = in.next();
        System.out.println("\n Enter its Priority (High, Medium, Low) : ");
        String Priority = in.next();
        Task task = new Task(code, taskSubject, taskDescription, duedate, Priority.toUpperCase());
        new TaskAssignment().sendTask(em,s.getDetailsofEmployee(employee),task,parent_task,Query.WRITE_TASK);
    }
}
