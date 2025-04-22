public class Employee extends Person {

  private String deptName;
  private int employeeID;
  private static int numEmployees = 0;
  
  public Employee() {
    deptName = "";
    ++numEmployees;
    employeeID = numEmployees;
  }
  
  public Employee(String d) {
    deptName = d;
    ++numEmployees;
    employeeID = numEmployees;
  }
  
  public Employee(String n, int b, String d) {
    super(n, b);
    deptName = d;
    ++numEmployees;
    employeeID = numEmployees;
  }
  
  //
  
  public String getDeptName() {
    return deptName;
  }
  
  public static int getNumEmployees() {
    return numEmployees;
  }
  
  public int getEmployeeID() {
    return employeeID;
  }
  
  public void decrementEmployeeID() {
	  --employeeID;
  }
  
  public static void decrementNumEmployees() {
	  --numEmployees;
  }
  
  public void setDeptName(String d) {
    deptName = d;
  }
  
  @Override
  public boolean equals(Object o) {
    
    if (o == null) {
      return false;
    }
    
    if (o instanceof Employee) {
      
      Employee e = (Employee) o;
      
      if (super.equals(e)) {
        
        if (e.deptName.equals(this.deptName) && e.employeeID == this.employeeID) {
          return true;
        }
        
        return false;
        
      }
      
      return false;
      
    }
    
    return false;
    
  }
  
  @Override
  public String toString() {
    return super.toString() + String.format(" Employee: Department: %20s | Employee Number: %3d", deptName, employeeID);
  }
  
  @Override
  public int compareTo(Person p) {
    
    if (p instanceof Employee) {
      
      Employee e = (Employee) p;
      
      if (this.employeeID > e.employeeID) {
      return 1;
      }
      else if (this.employeeID < e.employeeID) {
        return -1;
      }
      else {
        return 0;
      }
      
    }
    
    return 0;
    
  }
  
  
  
  
  
  
  
  
  
  
  
}