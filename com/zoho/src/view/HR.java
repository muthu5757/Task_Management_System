package src.view;

import src.controller.Manager;
import src.controller.changeLoginCredentials;
import src.model.Employee;
import src.model.ObjectCreation;

import java.util.Scanner;

public class HR {
    Scanner in = ObjectCreation.getInstanceofScanner();
    public void hr(Employee em)
    {
        System.out.println("!+========================================+!\n");
        System.out.println("Welcome HR !! "+em.getFirstName()+" "+em.getLastName()+" 🤩️\n");
        while(true)
        {
            System.out.println("\nChoose your Choice : \n");
            System.out.println("0. Logout");
            System.out.println("1. Your Profile");
            System.out.println("2. Change Login Credentials");
            System.out.println("3. Add Manager");
            System.out.println("4. Add New Employee");
            System.out.println("5. Managers List");
            System.out.println("\n!+========================================+!");
            System.out.println("\n\n Enter your Choice : ");
            int choice = in.nextInt();
            HR_Helper hh = ObjectCreation.getInstanceofHR_Helper();
            Manager mg = new Manager();
            switch(choice)
            {
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
                    System.out.println("\n 1. From New People ");
                    System.out.println("\n 2. From Existing People ");
                    System.out.println("\nEnter Your Choice : ");
                    int ch = in.nextInt();
                    if(ch == 1){
                        mg.addManager(hh.setEmployeeRoleByHR(hh.createAccount()),em);
                    }
                    else if(ch==2){
                        mg.addManager(hh.setEmployeeRoleByHR(null),em);
                    }
                    else
                        System.out.println("\n Invalid Choice ! ");
                    break;
                case 4:
                    hh.addMemberToTeam(hh.setEmployeeRoleByHR(hh.createAccount()),em);
                    break;
                case 5:
                    new DisplayManager().Display(mg.getManagerDetailsByHR(em));
                    break;
            }
        }
    }
}
