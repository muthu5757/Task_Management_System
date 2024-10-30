	package src.view;

	import src.model.Employee;

	public class Authorize {

	    public void authorize(Employee em)
	    {
	        switch(em.getRole().toLowerCase())
	        {
		   case "superadmin" :
		       new superAdmin().superAdmin(em);
		       break;
		   case "admin" :
		       new Admin().admin(em);
		       break;
		   case "hr" :
		       new HR().hr(em);
		       break;
		   case "manager" :
		        new Manager().manager(em);
		       break;
		   case "teamleader" :
		        new TeamLeader().teamleader(em);
		       break;
		   case "coordinator" :
		        new Coordinator().Coordinator(em);
		       break;
		   case "employee" :
		        new src.view.Employee().employee(em);
		       break;
		   default :
		       System.out.println("Something Went Wrong!! Please Correct !");
	        }
	    }
	}
