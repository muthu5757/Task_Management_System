package src.view;

import src.controller.Selection;
import src.controller.changeLoginCredentials;
import src.model.Employee;
import src.model.ObjectCreation;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class superAdmin {

    Scanner in = ObjectCreation.getInstanceofScanner();
    public void superAdmin(Employee em)
    {
        System.out.println("!+========================================+!\n");
        System.out.println("Welcome SuperAdmin !! "+em.getFirstName()+" "+em.getLastName()+" 🤩️\n");
        while(true)
        {
            System.out.println("\nChoose your Choice : \n");
            System.out.println("0. Logout");
            System.out.println("1. Your Profile");
            System.out.println("2. Change Login Credentials");
            System.out.println("3. Create Admin");
            System.out.println("4. Insert New Roles ");
            System.out.println("5. Insert New Departments ");
            System.out.println("6. Show Roles and Department ");
            System.out.println("7. Show Employees, Role and Department");
            System.out.println("\n!+========================================+!");
            System.out.println("\n\n Enter your Choice : ");
            SuperAdmin_Helper sh = ObjectCreation.getInstanceofSuperAdmin_Helper();
            int choice = in.nextInt();
            try {
                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        new Profile().displayProfile(em);
                        break;
                    case 2:
                        new changeLoginCredentials().verifyEmployee();
                        break;
                    case 3:
                        System.out.println("\n 1. From New People ");
                        System.out.println("\n 2. From Existing People ");
                        System.out.println("\nEnter Your Choice : ");
                        int ch = in.nextInt();
                        if (ch == 1) {
                            sh.setEmployeeRoleBySuperAdmin(sh.createAccount());
                        } else if (ch == 2) {
                            sh.setEmployeeRoleBySuperAdmin(null);
                        } else
                            System.out.println("\n Invalid Choice ! ");
                        break;
                    case 4:
                        sh.roleInsertion();
                        break;
                    case 5:
                        sh.departmentInsertion();
                        break;
                    case 6:
                        Display d = ObjectCreation.getInstanceofDisplay();
                        Selection s = ObjectCreation.getInstanceofSelection();
                        d.displayRoles(s.getRoles());
                        d.displayDepartment(s.getDepartments());
                        break;
                    case 7:
                        Display dd = ObjectCreation.getInstanceofDisplay();
                        Selection ss = ObjectCreation.getInstanceofSelection();
                        dd.displayRoleWithEmployee(ss.getEmployeewithRole());
                        break;
                }
            }catch (NoSuchElementException n)
            { }
        }
    }
}
