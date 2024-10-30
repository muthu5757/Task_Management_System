package src.model;

public class Employee {
    private int id;
    private String role;
    private String firstName;
    private String lastName;
    private String employee_id;
    private String email;
    private String mobileNumber;
    private String hire_date;
    private String designation;
    private String department;

    public Employee(){
    }

    public Employee(int id, String emp_id, String fname,String lname, String email, String mobileNumber,String hire_date,String designation, String role) {
        this.id = id;
        this.firstName = fname;
        this.lastName = lname;
        this.employee_id = emp_id;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.hire_date = hire_date;
        this.designation = designation;
        this.role = role;
    }
    public Employee(int id, String emp_id, String fname,String lname, String email, String mobileNumber) {
        this.id = id;
        this.firstName = fname;
        this.lastName = lname;
        this.employee_id = emp_id;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
    public Employee(int id, String emp_id, String fname,String lname,String designation, String department, String role) {
        this.id = id;
        this.firstName = fname;
        this.lastName = lname;
        this.employee_id = emp_id;
        this.designation = designation;
        this.department = department;
        this.role = role;
    }

    public Employee(String employee_id, String firstname, String lastname) {
        this.employee_id = employee_id;
        this.firstName = firstname;
        this.lastName = lastname;
    }

    public void setRoleandDepartment(String role,String department) {
        this.role = role;
        this.department = department;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getRole() {
        return role;
    }
    public String setRole(String role) {
        return this.role = role;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public String getEmployeeId() {
        return employee_id;
    }
    public String getHireDate(){
        return hire_date;
    }
    public String getDesignation()
    {
        return designation;
    }
    public String getDepartment()
    {
        return department;
    }

}
