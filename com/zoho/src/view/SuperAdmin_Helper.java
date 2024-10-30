package src.view;

import src.controller.EmployeeRole;
import src.controller.Insertion;
import src.controller.Selection;
import src.controller.SignUp;
import src.model.Department;
import src.model.Employee;
import src.model.ObjectCreation;
import src.model.Role;

import java.util.Scanner;

public class SuperAdmin_Helper {

    Scanner in = ObjectCreation.getInstanceofScanner();
    public Employee createAccount()
    {
        Checks c = new Checks();
        System.out.println("\n!+*****************************************+!\n");
        System.out.println("Account Creation : \n");
        in.nextLine();
        System.out.println("\nEnter his/her First Name : ");
        String firstName = in.nextLine();
        System.out.println("\nEnter his/her Last Name : ");
        String lastName = in.nextLine();
        System.out.println("\nEnter Designation of the Employee : ");
        String designation = in.nextLine();
        String email;
        while(true)
        {
            System.out.println("\nEnter his/her E-mail Id (Must be Unique) : ");
            email = in.next();
            if (c.isEmailId(email))
                break;
            else
                System.out.println("Invalid email address. Please Re-Enter Again ! 🔁️");
        }
        String number;
        while(true)
        {
            System.out.println("\nEnter his/her Mobile Number : ");
            number = in.next();
            if (c.isMobileNumber(number))
                break;
            else
                System.out.println("Invalid mobile number. Please Re-Enter Again ! 🔁️");
        }
        System.out.println("\nEnter Hire-Date of the Employee : ");
        String hire_date = in.next();

        Generator generate = new Generator();
        String employeeId = generate.generateEmployeeId();
        String username = generate.generateUsername(firstName, lastName);
        String password = generate.generatePassword();

        Employee e = new Employee(0,employeeId, firstName, lastName, email, number,hire_date,designation,null);
        Employee em = new SignUp().signup(e, username, password);

        System.out.println("\n ---------------------------------------------");
        System.out.println("\n New Employee Credentials is 🔐️ : ");
        System.out.println("\n Employee ID: " + employeeId);
        System.out.println(" Username: " + username);
        System.out.println(" Password: " + password);
        System.out.println("\n ---------------------------------------------\n");

        System.out.println("\n!+*****************************************+!\n");

        return em;
    }
    public void setEmployeeRoleBySuperAdmin(Employee em)
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
        System.out.println("\nEnter to Set Employee Role(Admin / HR / Manager / TeamLeader / Coordinator) : ");
        String role = in.nextLine();
        em.setRoleandDepartment(role,empdept);
        new EmployeeRole().setEmployeeRole(em);
        System.out.println("!+*****************************************+!\n");
    }
    public void roleInsertion()
    {
        Insertion ins = ObjectCreation.getInstanceofInsertion();
        System.out.println("\n!+*****************************************+!\n");
        System.out.println("\nInserting New Role :");
        in.nextLine();
        System.out.println("\nEnter New Role : ");
        String role = in.nextLine();
        ins.roleInsertion(new Role(role));
        System.out.println("!+*****************************************+!\n");
    }
    public void departmentInsertion()
    {
        Insertion ins = ObjectCreation.getInstanceofInsertion();
        System.out.println("\n!+*****************************************+!\n");
        System.out.println("\nInserting New Department :");
        in.nextLine();
        System.out.println("\nEnter New Department : ");
        String department = in.nextLine();
        ins.departmentInsertion(new Department(department));
        System.out.println("!+*****************************************+!\n");
    }
}
