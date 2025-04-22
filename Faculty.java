public class Faculty extends Employee {

  private Course[] coursesTaught;
  private int numCoursesTaught;
  private boolean isTenured;
  
  public Faculty() {
    coursesTaught = new Course[10];
    numCoursesTaught = 0;
    isTenured = false;
  }
  
  public Faculty(boolean t) {
    coursesTaught = new Course[10];
    numCoursesTaught = 0;
    isTenured = t;
  }
  
  public Faculty(String d, boolean t) {
    super(d);
    coursesTaught = new Course[10];
    numCoursesTaught = 0;
    isTenured = t;
  }
  
  public Faculty(String n, int b, String d, boolean t) {
    super(n, b, d);
    coursesTaught = new Course[10];
    numCoursesTaught = 0;
    isTenured = t;
  }
  
  //
  
  public boolean isTenured() {
    return isTenured;
  }
  
  public void setIsTenured(boolean t) {
    isTenured = t;
  }
  
  public int getNumCoursesTaught() {
    return numCoursesTaught;
  }
  
  public void addCourseTaught(Course c) throws ArrayFullException {
    
    if (numCoursesTaught != 10) {
      coursesTaught[numCoursesTaught] = c;
      ++numCoursesTaught;
    }
    else {
    	throw new ArrayFullException();
    }
    
  }
  
  public void addCoursesTaught(Course[] c) throws ArrayFullException {
    
    if (numCoursesTaught + c.length <= 10) {
      
      for (int i = 0; i < c.length; ++i) {
        addCourseTaught(c[i]);
      }
      
    }
    else {
    	throw new ArrayFullException();
    }
  }
  
  
  public Course getCourseTaught(int i) {
    
    if (i < 0 || i >= numCoursesTaught) {
      return null;
    }
    else {
      return coursesTaught[i];
    }
    
  }
  
  public String getCourseTaughtAsString(int i) {
    
    if (i < 0 || i >= numCoursesTaught) {
      return "";
    }
    else {
      return coursesTaught[i].getCourseDept() + "-" + coursesTaught[i].getCourseNum();
    }
    
  }
  
  public String getAllCoursesTaughtAsString() {
    
    String s = "";
    
    for (int i = 0; i < numCoursesTaught; ++i) {
      s = s + getCourseTaughtAsString(i);
      
      if (i != numCoursesTaught - 1) {
        s = s + ", ";
      }
      
    }
    
    return s;
    
  }
  
  @Override
  public boolean equals(Object o) {
    
    if (o == null) {
      return false;
    }
    
    if (o instanceof Faculty) {
      
      Faculty f = (Faculty) o;
      
      if (super.equals(f)) {
        
        if (f.numCoursesTaught == this.numCoursesTaught && f.isTenured == this.isTenured) {
          
          for (int i = 0; i < this.numCoursesTaught; ++i) {
            
            if (!f.coursesTaught[i].equals(this.coursesTaught[i])) {
              return false;
            }
            
          }
          
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
    
    String s = (isTenured) ? "Is Tenured" : "Not Tenured";
    
    return super.toString() + String.format(" Faculty: %11s | Number of Courses Taught: %3d | Courses Taught: %s", s, numCoursesTaught, getAllCoursesTaughtAsString());

  }
  
  @Override
  public int compareTo(Person p) {
    
    if (p instanceof Faculty) {
      
      Faculty f = (Faculty) p;
      
      if (this.numCoursesTaught > f.numCoursesTaught) {
      return 1;
      }
      else if (this.numCoursesTaught < f.numCoursesTaught) {
        return -1;
      }
      else {
        return 0;
      }
      
    }
    
    return 0;
    
  }
  
  
  public boolean checkIfCourseIsAvailable(Course c) {
	  
	  for (int i = 0; i < numCoursesTaught; ++i) {
		  
		  if (coursesTaught[i] == c) {
			  return false;
		  }
		  
	  }
	  
	  return true;
	  
  }
  
  public Course[] getCoursesTaught() {
	  return coursesTaught;
  }
  
  public void removeCourse(Course c) {
	  
	  Course[] newCoursesTaught = new Course[10];
	  int j = 0;
	  
	  for (int i = 0; i < numCoursesTaught; ++i) {
		  
		  if (coursesTaught[i] != c) {
			  newCoursesTaught[j] = coursesTaught[i];
			  ++j;
		  }
		  
	  }
	  
	  coursesTaught = newCoursesTaught;
	  numCoursesTaught = j;
	  
  }
  

}