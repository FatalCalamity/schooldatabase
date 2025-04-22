import java.util.*;
import java.io.*;

public class Driver_SchoolDB {
	
    public static void main(String[] args) throws IOException {
      
     FileInputStream in = null;
     Scanner scnrin = null;
      
     ArrayList<Course> c = new ArrayList<>();
     ArrayList<Faculty> f = new ArrayList<>();
     ArrayList<Student> s = new ArrayList<>();
     ArrayList<GeneralStaff> g = new ArrayList<>();
      
     try {
    	 
    	 in = new FileInputStream("SchoolDB_Initial.txt");
    	 scnrin = new Scanner(in);
    	  
    	 while (scnrin.hasNextLine()) {
    		  
    		 String currLine = scnrin.nextLine();
    		 
    		 System.out.println(currLine);
    		 
    		 if (currLine.isBlank()) {
    			 continue;
    		 }
    		 else {
    			 
    			 String[] currLineParsed = currLine.split(":|,");
    			 
    			 for (int i = 0; i < currLineParsed.length; ++i) {
    				 currLineParsed[i] = currLineParsed[i].trim();
    			 }
    			 
    			 switch (currLineParsed[0]) {
    			 	case "Course":
    			 		
    			 		c.add(new Course(Boolean.parseBoolean(currLineParsed[1]), Integer.parseInt(currLineParsed[2]), currLineParsed[3], Integer.parseInt(currLineParsed[4])));
    			 		break;
    			 		
    			 	case "Faculty":
    			 		
    			 		switch (currLineParsed.length) {
    			 			case 1:
    			 				f.add(new Faculty());
    			 				break;
    			 			case 2:
    			 				f.add(new Faculty(Boolean.parseBoolean(currLineParsed[1])));
    			 				break;
    			 			case 3:
    			 				f.add(new Faculty(currLineParsed[1], Boolean.parseBoolean(currLineParsed[2])));
    			 				break;
    			 			case 5:
    			 				f.add(new Faculty(currLineParsed[1], Integer.parseInt(currLineParsed[2]), currLineParsed[3], Boolean.parseBoolean(currLineParsed[4])));
    			 				break;
    			 		}
    			 		
    			 		break;
    			 		
    			 	case "Student":
    			 		
    			 		switch (currLineParsed.length) {
			 				case 1:
			 					s.add(new Student());
			 					break;
			 				case 2:
			 					s.add(new Student(Boolean.parseBoolean(currLineParsed[1])));
			 					break;
			 				case 3:
			 					s.add(new Student(currLineParsed[1], Boolean.parseBoolean(currLineParsed[2])));
			 					break;
			 				case 5:
			 					s.add(new Student(currLineParsed[1], Integer.parseInt(currLineParsed[2]), currLineParsed[3], Boolean.parseBoolean(currLineParsed[4])));
			 					break;
    			 		}
    			 		
    			 		break;
    			 		
    			 	case "GeneralStaff":
    			 		
    			 		switch (currLineParsed.length) {
    			 			case 1:
    			 				g.add(new GeneralStaff());
    			 				break;
    			 			case 2:
    			 				g.add(new GeneralStaff(currLineParsed[1]));
    			 				break;
    			 			case 3:
    			 				g.add(new GeneralStaff(currLineParsed[1], currLineParsed[2]));
    			 				break;
    			 			case 5:
    			 				g.add(new GeneralStaff(currLineParsed[1], Integer.parseInt(currLineParsed[2]), currLineParsed[3], currLineParsed[4]));
    			 		}
    			 		
    			 		break;
    			 }
    			 
    		 }
    	
    		  
    	 }
    	  
    	 System.out.println();
    	 in.close();
    	  
     }
     catch (IOException e) {
    	 System.out.println("File Not Found... " + e.getMessage());
     }
      
      
     //
      
      
      final String LONG_ASTERISK_CHAIN = "**************************************************************";
      final String SHORT_ASTERISK_CHAIN = "************************************************";
      
      
      System.out.println(LONG_ASTERISK_CHAIN);
      System.out.println("SCHOOL DATABASE INFO:\n");
      System.out.println(SHORT_ASTERISK_CHAIN);
      
      System.out.println("COURSES:");
      
      for (Course course : c) {
    	  System.out.println(course.toString());
      }
      
      System.out.println(SHORT_ASTERISK_CHAIN + "\n" + SHORT_ASTERISK_CHAIN);
      
      System.out.println("PERSONS:");
      
      //
      
      System.out.println(SHORT_ASTERISK_CHAIN + "\n" + SHORT_ASTERISK_CHAIN);
      
      System.out.println("EMPLOYEES:");
      
      //
      
      System.out.println(SHORT_ASTERISK_CHAIN + "\n" + SHORT_ASTERISK_CHAIN);
      
      System.out.println("GENERAL STAFF:");
      
      for (GeneralStaff gen : g) {
    	  System.out.println(gen.toString());
      }
      
      System.out.println(SHORT_ASTERISK_CHAIN + "\n" + SHORT_ASTERISK_CHAIN);
      
      System.out.println("FACULTY:");
      
      for (Faculty faculty : f) {
    	  System.out.println(faculty.toString());
      }
      
      System.out.println(SHORT_ASTERISK_CHAIN + "\n" + SHORT_ASTERISK_CHAIN);
      
      System.out.println("STUDENTS:");
      
      for (Student student : s) {
    	  System.out.println(student.toString());
      }
      
      System.out.println(SHORT_ASTERISK_CHAIN + "\n" + LONG_ASTERISK_CHAIN + "\n");
      
  }
    
}