package src.view;

import src.controller.HR;
import src.controller.changeLoginCredentials;
import src.model.Employee;
import src.model.ObjectCreation;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Admin {

    Scanner in = ObjectCreation.getInstanceofScanner();
    public void admin(Employee em)
    {
        System.out.println("!+========================================+!\n");
        System.out.println("Welcome Admin !! "+em.getFirstName()+" "+em.getLastName()+" 🤩️\n");
        while(true)
        {
            System.out.println("\nChoose your Choice : \n");
            System.out.println("0. Logout");
            System.out.println("1. Your Profile");
            System.out.println("2. Change Login Credentials");
            System.out.println("3. Account Creation for New HR");
            System.out.println("4. My HRs List");
            System.out.println("\n!+========================================+!");
            System.out.println("\n\n Enter your Choice : ");
            int choice = in.nextInt();
            Admin_Helper ah = ObjectCreation.getInstanceofAdmin_Helper();
            HR hr = ObjectCreation.getInstanceofHR();
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
                        System.out.println("\n 1. From New People ");
                        System.out.println("\n 2. From Existing People ");
                        System.out.println("\nEnter Your Choice : ");
                        int ch = in.nextInt();
                        if (ch == 1) {
                            hr.addHR(ah.setEmployeeRoleByAdmin(ah.createAccount()), em);
                        } else if (ch == 2) {
                            hr.addHR(ah.setEmployeeRoleByAdmin(null), em);
                        } else
                            System.out.println("\n Invalid Choice ! ");
                        break;
                    case 4:
                        new DisplayHR().Display(hr.getHRDetailsByAdmin(em));
                        break;
                }
            }catch (NoSuchElementException n)
            { }
        }
    }
}
