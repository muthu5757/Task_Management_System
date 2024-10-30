package src.view;

import src.controller.Query;
import src.controller.Selection;
import src.controller.Updation;
import src.model.Employee;
import src.model.ObjectCreation;
import src.view.DisplayCoordinator;

import java.util.Scanner;

public class Updation_Helper {

    Scanner in = ObjectCreation.getInstanceofScanner();
    public void updateTaskCompletion(Employee em)
    {
        src.controller.Employee emp = new src.controller.Employee();
        Selection s = ObjectCreation.getInstanceofSelection();
        Updation up = new Updation();
        new DisplayCoordinator().TaskDisplay(emp.getTasksToEmployee(em, Query.DISPLAY_TASK_TO_EMPLOYEE),em,Query.DISPLAY_TASK_TO_EMPLOYEE);
        System.out.println("\n Enter the Task Code : ");
        String code = in.next();
        int id = s.getTaskId(code);
        System.out.println("\n Enter Status (Started, In_Progress, Completed) : ");
        String status = in.next().toUpperCase();
        up.updateTask(id, status);
    }
    public void updateTaskCompletionByManager(Employee em)
    {
        src.controller.Employee emp = new src.controller.Employee();
        src.controller.Manager mg = new src.controller.Manager();
        Selection s = ObjectCreation.getInstanceofSelection();
        Updation up = new Updation();
        new DisplayManager().TaskStatusDisplay(mg.getTasksByManager(em),em);
        System.out.println("\n Enter the Task Code : ");
        String code = in.next();
        int id = s.getTaskId(code);
        System.out.println("\n Enter Status (Postponed or Cancelled) : ");
        String status = in.next().toUpperCase();
        if(status.equalsIgnoreCase("started") || status.equalsIgnoreCase("In_progress")|| status.equalsIgnoreCase("completed"))
        {
            System.out.println("You not able update the "+status+" of the task.");
        }
        up.updateTask(id, status);
    }
}
