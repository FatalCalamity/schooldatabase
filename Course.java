public class Course implements Comparable<Course> {

  private boolean isGraduateCourse;
  private int courseNum;
  private String courseDept;
  private int numCredits;
  
  public Course(boolean c, int n, String d, int cr) {
    
    isGraduateCourse = c;
    courseNum = n;
    courseDept = d;
    numCredits = cr;
    
  }
  
  //
 
  public boolean isGraduateCourse() {
    return isGraduateCourse;
  }
  
  public int getCourseNum() {
    return courseNum;
  }
 
  public String getCourseDept() {
    return courseDept;
  }
  
  public int getNumCredits() {
    return numCredits;
  }
  
  public String getCourseName() {
    
    if (isGraduateCourse) {
      return "G" + courseDept + courseNum;
    }
    return "U" + courseDept + courseNum;
  
  }
  
  @Override
  public boolean equals(Object o) {
    
    if (o == null) {
      return false;
    }
    
    if (o instanceof Course) {
      Course c = (Course) o;
      if (c.isGraduateCourse == this.isGraduateCourse && c.courseNum == this.courseNum && c.courseDept.equals(this.courseDept) && c.numCredits == this.numCredits) {
        return true;
      }
      return false;
    }
    
    return false;
    
  }
  
  @Override
  public String toString() {
    
    String s = (isGraduateCourse) ? "Graduate" : "Undergraduate";
    
    return String.format("Course: %3s-%3d | Number of Credits: %02d | ", courseDept, courseNum, numCredits) + s;
    
  }
 
  @Override
  public int compareTo(Course c) {
    
    if (this.courseNum > c.courseNum) {
      return 1;
    }
    else if (this.courseNum < c.courseNum) {
      return -1;
    }
    else {
      return 0;
    }
    
  }
  
 

}