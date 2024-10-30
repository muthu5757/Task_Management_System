package src.view;

import src.controller.EmployeeRole;
import src.controller.Selection;
import src.model.Employee;
import src.model.ObjectCreation;

public class Admin_Helper extends SuperAdmin_Helper{
    public Employee setEmployeeRoleByAdmin(Employee em)
    {
        System.out.println("\n!+*****************************************+!\n");
        Display d = ObjectCreation.getInstanceofDisplay();
        Selection ss = ObjectCreation.getInstanceofSelection();
        System.out.println("\nInserting Employee Role :");
        if(em==null) {
            Selection s = ObjectCreation.getInstanceofSelection();
            System.out.println("\nEnter his/her Employee ID : ");
            String empId = in.next();
            em = s.getDetailsofEmployee(empId);
        }
        in.nextLine();
        d.displayDepartment(ss.getDepartments());
        System.out.println("\nEnter his/her Employee Department : ");
        String empdept = in.nextLine();
        d.displayRoles(ss.getRoles());
        System.out.println("\nEnter to Set Employee Role(HR / Manager / TeamLeader / Coordinator) : ");
        String role = in.nextLine();
        if(role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("superadmin"))
        {
            System.out.println("You Not Have a Permission to Set Role of "+role);
            return  null;
        }
        em.setRoleandDepartment(role,empdept);
        new EmployeeRole().setEmployeeRole(em);
        System.out.println("!+*****************************************+!\n");
        return em;
    }
}
