public class GeneralStaff extends Employee {

  private String duty;
  
  public GeneralStaff() {
    duty = "";
  }
  
  public GeneralStaff(String du) {
    duty = du;
  }
  
  public GeneralStaff(String d, String du) {
    super(d);
    duty = du;
  }
  
  public GeneralStaff(String n, int b, String d, String du) {
    super(n, b, d);
    duty = du;
  }
  
  //
  
  public String getDuty() {
    return duty;
  }
  
  @Override
  public boolean equals(Object o) {
    
    if (o == null) {
      return false;
    }
    
    if (o instanceof GeneralStaff) {
      
      GeneralStaff s = (GeneralStaff) o;
      
      if (super.equals(s)) {
        
        if (s.duty.equals(this.duty)) {
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
    return super.toString() + String.format(" GeneralStaff: Duty: %10s", duty);
  }
  
  
  

}