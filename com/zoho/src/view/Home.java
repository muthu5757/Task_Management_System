package src.view;

import src.controller.Login;
import src.model.ObjectCreation;

import java.util.Scanner;
public class Home {

    Scanner in = ObjectCreation.getInstanceofScanner();
    public void home()
    {
        while(true)
        {
            System.out.println("\n!+========================================+!\n");
            System.out.println("Welcome to FocusHub !! 🤩️ 📔️ \n");
            System.out.println("Choose your Choice : \n");
            System.out.println("0. Exit");
            System.out.println("1. Employee Login ");
            System.out.println("\n!+========================================+!");
            System.out.println("\n Enter your Choice : ");
            int choice = in.nextInt();
            if(choice==0){
		   	System.out.println("\n  ||  Thankyou 🙏️ Come Again !! 👋️  ||  \n");
		   	ObjectCreation.closeDatabaseConnection();
		       	System.exit(0);
		 }
            else if(choice==1) {
		   try{
		       new Authorize().authorize(new Login().login());
		       }
		       catch(NullPointerException n)
		       {
		       		System.out.println("\n ||  Username not Found ! Try Again !!  ||  \n");
		       }
                finally{
               ObjectCreation.closeDatabaseConnection();
                }
            }
            else
                		System.out.println("Invalid Choice. Re-Enter Again !! ");
        }
    }
}
