public class Person implements Comparable<Person> {

  private String name;
  private int birthYear;
  
  public Person() {
    name = "";
    birthYear = 0;
  }
  
  public Person(String n, int b) {
    name = n;
    birthYear = b;
  }
  
  //
  
  public String getName() {
    return name;
  }
  
  public int getBirthYear() {
    return birthYear;
  }
  
  public void setName(String n) {
    name = n;
  }
  
  public void setBirthYear(int b) {
    birthYear = b;
  }
  
  @Override
  public boolean equals(Object o) {
    
    if (o == null) {
      return false;
    }
    
    if (o instanceof Person) {
      Person p = (Person) o;
      if (p.name.equals(this.name) && p.birthYear == this.birthYear) {
        return true;
      }
      return false;
    }
    
    return false;
    
  }
  
  @Override
  public String toString() {
    return String.format("Person: Name: %30s | Birth Year: %4d", name, birthYear);
  }
  
  @Override
  public int compareTo(Person p) {
    
    if (this.birthYear > p.birthYear) {
      return 1;
    }
    else if (this.birthYear < p.birthYear) {
      return -1;
    }
    else {
      return 0;
    }
    
  }
  
  
  
  
  
  
  
  
  
  
  
  

}