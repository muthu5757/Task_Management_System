package src.view;
import src.model.Employee;

class Profile
{
	public void displayProfile(Employee em)
	{
		System.out.println("\n ===================================================================\n");
        		System.out.println(" |                       Your Profile 👨‍💻️                   |");
        		System.out.println("\n ====================================================================\n");
        		System.out.println("  Employee ID    	 : " + em.getEmployeeId());
        		System.out.println("  First Name       	 : " + em.getFirstName());
        		System.out.println("  Last Name        	 : " + em.getLastName());
        		System.out.println("  Role     	         : " + em.getRole());
        		System.out.println("  Designation   	 : " + em.getDesignation());
        		System.out.println("  Email                  : " + em.getEmail());
        		System.out.println("  Mobile Number          : " + em.getMobileNumber());
        		System.out.println("  Hire Date     	 : " + em.getHireDate());
        		System.out.println("\n=====================================================================\n");
	}
}
