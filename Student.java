public class Student extends Person {

  private static int numStudents = 0;
  private int studentID;
  private Course[] coursesTaken = new Course[10];
  private int numCoursesTaken;
  private boolean isGraduate;
  private String major = "undeclared";
  
  
  
  public Student() {
    numCoursesTaken = 0;
    isGraduate = false;
    ++numStudents;
    studentID = numStudents;
  }
  
  public Student(boolean g) {
    numCoursesTaken = 0;
    isGraduate = g;
    ++numStudents;
    studentID = numStudents;
  }
  
  public Student(String m, boolean g) {
    numCoursesTaken = 0;
    isGraduate = g;
    major = m;
    ++numStudents;
    studentID = numStudents;
  }
  
  public Student(String n, int b, String m, boolean g) {
    super(n, b);
    numCoursesTaken = 0;
    isGraduate = g;
    major = m;
    ++numStudents;
    studentID = numStudents;
  }
  
  //
  
  public boolean isGraduate() {
    return isGraduate;
  }
  
  public int getNumCoursesTaken() {
    return numCoursesTaken;
  }
  
  public static int getNumStudents() {
    return numStudents;
  }
  
  public int getStudentID() {
    return studentID;
  }
  
  public void decrementStudentID() {
	  --studentID;
  }
  
  public static void decrementNumStudents() {
	  --numStudents;
  }
  
  public String getMajor() {
    return major;
  }
  
  public void setIsGraduate(boolean g) {
    isGraduate = g;
  }
  
  public void setMajor(String m) {
    major = m;
  }
  
  public void addCourseTaken(Course c) throws ArrayFullException {
    
    if (numCoursesTaken != 10) {
      coursesTaken[numCoursesTaken] = c;
      ++numCoursesTaken;
    }
    else {
    	throw new ArrayFullException();
    }
    
  }
  
  public void addCoursesTaken(Course[] c) throws ArrayFullException {
    
    if (numCoursesTaken + c.length <= 10) {
      
      for (int i = 0; i < c.length; ++i) {
        addCourseTaken(c[i]);
      }
      
    }
    else {
    	throw new ArrayFullException();
    }
    
  }
  
  public Course getCourseTaken(int i) {
    
    if (i < 0 || i >= numCoursesTaken) {
      return null;
    }
    else {
      return coursesTaken[i];
    }
  }
  
  public String getCourseTakenAsString(int i) {
    
    if (i < 0 || i >= numCoursesTaken) {
      return "";
    }
    else {
      return coursesTaken[i].getCourseDept() + "-" + coursesTaken[i].getCourseNum();
    }
    
  }
  
  public String getAllCoursesTakenAsString() {
    
    String s = "";
    
    for (int i = 0; i < numCoursesTaken; ++i) {
      s = s + getCourseTakenAsString(i);
      
      if (i != numCoursesTaken - 1) {
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
    
    if (o instanceof Student) {
      
      Student s = (Student) o;
      
      if (super.equals(s)) {
        
        if (s.studentID == this.studentID && s.numCoursesTaken == this.numCoursesTaken && s.isGraduate == this.isGraduate && s.major.equals(this.major)) {
          
          for (int i = 0; i < this.numCoursesTaken; ++i) {
            
            if (!s.coursesTaken[i].equals(this.coursesTaken[i])) {
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
    
    String s = (isGraduate) ? "Graduate" : "Undergraduate";
    
    return super.toString() + String.format(" Student: studentID: %04d | Major %20s | %14s | Number of Courses Taken: %3d | Courses Taken: %s", studentID, major, s, numCoursesTaken, getAllCoursesTakenAsString());
    
  }
  
  public int getTotalCredits() {
    
    int total = 0;
    
    for (int i = 0; i < numCoursesTaken; ++i) {
      total = total + coursesTaken[i].getNumCredits();
    }
    
    return total;
    
  }
  
  @Override
  public int compareTo(Person p) {
    
    if (p instanceof Student) {
      
      Student s = (Student) p;
      
      int otherCredits = s.getTotalCredits();
      int thisCredits = this.getTotalCredits();
      
      if (thisCredits > otherCredits) {
      return 1;
      }
      else if (thisCredits < otherCredits) {
        return -1;
      }
      else {
        return 0;
      }
      
    }
    
    return 0;
    
  }

  
  public boolean checkIfCourseIsAvailable(Course c) {
	  
	  for (int i = 0; i < numCoursesTaken; ++i) {
		  
		  if (coursesTaken[i] == c) {
			  return false;
		  }
		  
	  }
	  
	  return true;
	  
  }
  
  public Course[] getCoursesTaken() {
	  return coursesTaken;
  }
  
  public void removeCourse(Course c) {
	  
	  Course[] newCoursesTaken = new Course[10];
	  int j = 0;
	  
	  for (int i = 0; i < numCoursesTaken; ++i) {
		  
		  if (coursesTaken[i] != c) {
			  newCoursesTaken[j] = coursesTaken[i];
			  ++j;
		  }
		  
	  }
	  
	  coursesTaken = newCoursesTaken;
	  numCoursesTaken = j;
	  
  }
  
  

}