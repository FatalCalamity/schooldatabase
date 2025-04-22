import java.util.*;
import java.io.*;

public class DatabaseCreator {
	
	static final String DASH_CHAIN = "-----------------------------------------";
	static final String invalidInputPrompt = "Invalid input, please try again.";
	static final String returnToHome = "Type 'h' to return to the home menu.";
	
	static Scanner scnr = new Scanner(System.in);
	
	public static void writeToFile(String fileName, boolean append, String text) {
	    
		FileWriter f = null;
		PrintWriter w = null;
	    
	    try {
	    	f = new FileWriter(fileName, append);
	    	w = new PrintWriter(f);
	    	w.println(text);
	    }
	    catch (IOException e) {
	    	System.out.println("Could not write to the file......");
	    	e.printStackTrace();
	    }
	    finally {
	    	if (w != null) {
	    		w.close();
	    	}
	    }
	    
	    
	}
	
	public static void displayFromFile(String fileName) {
	    
	    FileInputStream f = null;
	    Scanner scnr = null;
	    
	    try {
	      
	    	f = new FileInputStream(fileName);
	    	scnr = new Scanner(f);
	      
	    	while (scnr.hasNextLine()) {
	    		System.out.println(scnr.nextLine());
	    	}
	      
	    }
	    catch (IOException e) {
	    	System.out.println("Something went wrong....");
	    	e.printStackTrace();
	    }
	    
	}
	
 	public static void wait(int time) {
		
		 try {
	            Thread.sleep(time);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		 
	}
	
	public static void clearConsole() {
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	public static int getValidInt() throws GoToHomeException { // if an invalid input is detected, throws an InvalidInputException, which is checked by these methods and re-prompts accordingly - if the input "h" is detected, throws a GoToHomeException, which is handled in main
		
		String str = "";
		int x;
		
		while (true) {
			
			try {
				
				str = scnr.nextLine();
				
				if (str.equals("h")) {
					throw new GoToHomeException();
				}
				
				if (str.isBlank()) {
					throw new InvalidInputException();
				}
				
				str = str.trim();
				
				for (int i = 0; i < str.length(); ++i) {
					
					if (!Character.isDigit(str.charAt(i))) {
						throw new InvalidInputException();
					}
					
				}
				
				x = Integer.parseInt(str);
			
				break;
			}
			catch (InvalidInputException e) {
				System.out.println(invalidInputPrompt);
			}
			
		}
		
		return x;
		
	}
	
	public static int getValidInt(int bound) throws GoToHomeException {
		
		String str = "";
		int x;
		
		while (true) {
			
			try {
				
				str = scnr.nextLine();
				
				if (str.equals("h")) {
					throw new GoToHomeException();
				}
				
				if (str.isBlank()) {
					throw new InvalidInputException();
				}
				
				str = str.trim();
				
				for (int i = 0; i < str.length(); ++i) {
					
					if (!Character.isDigit(str.charAt(i))) {
						throw new InvalidInputException();
					}
					
				}
				
				x = Integer.parseInt(str);
				
				if (x < 1 || x > bound) {
					throw new InvalidInputException();
				}
				
				break;
			}
			catch (InvalidInputException e) {
				System.out.println(invalidInputPrompt);
			}
			
		}
		
		return x;
		
	}

	public static String getValidName() throws GoToHomeException {
		
		String str = "";
		
		while (true) {
			
			try {
				
				str = scnr.nextLine();
				
				if (str.equals("h")) {
					throw new GoToHomeException();
				}
				
				if (str.isBlank()) {
					throw new InvalidInputException();
				}
				
				for (int i = 0; i < str.length(); ++i) {
					
					if (!Character.isLetter(str.charAt(i)) && !Character.isWhitespace(str.charAt(i))) {
						throw new InvalidInputException();
					}
					
				}
				
				break;
			}
			catch (InvalidInputException e) {
				System.out.println(invalidInputPrompt);
			}
			
		}
		
		return str.trim().replaceAll("\\s+", " ");
		
		
	}
	
	public static boolean getValidBoolean() throws GoToHomeException {
		
		String str = "";
		
		while (true) {
			
			try {
				
				str = scnr.nextLine();
				
				if (str.equals("h")) {
					throw new GoToHomeException();
				}
				
				str = str.toLowerCase();
				
				if (!str.trim().equals("true") && !str.trim().equals("false") && !str.trim().equals("t") && !str.trim().equals("f")) {
					throw new InvalidInputException();
				}
				
				break;
			}
			catch (InvalidInputException e) {
				System.out.println(invalidInputPrompt);
			}
			
		}
		
		if (str.equals("t")) {
			str = "true";
		}
		else if (str.equals("f")) {
			str = "false";
		}
		
		return Boolean.parseBoolean(str);
		
	}
	
	public static int[] getValidIntArray(int bound, int correctLengthOfArray) throws GoToHomeException {
		
		String str = "";
		String[] strSplit;
		int[] strSplitInt;
		
		while (true) {
			
			try {
				
				str = scnr.nextLine();
				
				if (str.equals("h")) {
					throw new GoToHomeException();
				}
				
				if (str.isBlank() || str.indexOf(",,") != -1) { // if the string is blank or contains multiple commas in a row, re-prompts the user
					throw new InvalidInputException();
				}
				
				for (int i = 0; i < str.length(); ++i) { 
					
					if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != ',' && !Character.isWhitespace(str.charAt(i))) { // if any character in the string is not a digit, comma, or space, re=prompts the user
						throw new InvalidInputException();
					}
					
				}
				
				str = str.trim().replaceAll("\\s+", " "); // trims the string and replaces any instances of multiple spaces with a single space - doing this to make it easier to check if there are spaces between numbers, as that is not allowed
				
				if (str.charAt(0) == ',' || str.charAt(str.length() - 1) == ',') { // if the first or last character is a comma, re-prompts the user
					throw new InvalidInputException();
				}
				
				
				for (int i = 0; i < str.length(); ++i) {
					
					if (str.charAt(i) == ' ') {
						
						if (str.charAt(i - 1) != ',' || !Character.isDigit(str.charAt(i + 1))) { // if a space ISN'T surrounded by a comma to its left, and a digit to its right, re-prompts the user
							throw new InvalidInputException();
						}
						
					}
					
				}
			
				// if code reaches here, string is deemed valid for now
				
				str = str.replaceAll("\\s+", ""); // removes any spaces the user inputs in the string ("1, 2, 3, 4" becomes "1,2,3,4")
		
				strSplit = str.split(","); // split the new string about commas - if code reaches here, this array should only contain strings of pure digits
				
				if (strSplit.length != correctLengthOfArray) { // if the length of the array doesn't match the chosen length, re-prompts the user
					throw new InvalidInputException();
				}
				
				strSplitInt = new int[strSplit.length]; // create a new int[] with the same length as the array of digit strings
				
				for (int i = 0; i < strSplit.length; ++i) { // populate int[] with ints from the array of strings
					strSplitInt[i] = Integer.parseInt(strSplit[i]);
				}
			
				
				for (int i = 0; i < strSplitInt.length; ++i) { // if any of the ints in the array are greater than bound, or 0 (negatives are handled already as "-" is not allowed), re-prompts the user
					
					if (strSplitInt[i] > bound || strSplitInt[i] == 0) {
						throw new InvalidInputException();
					}
					
				}
				
				for (int i = 0; i < strSplitInt.length - 1; ++i) { // if the array is not in ascending order, or there are any duplicate numbers, re-prompts the user
					
					if (strSplitInt[i] >= strSplitInt[i + 1]) {
						throw new InvalidInputException();
					}
					
				}
				
				break;
				
			}
			catch (InvalidInputException e) {
				System.out.println(invalidInputPrompt);
			}
			
		}
		
		return strSplitInt;
		
	}
	
	public static void printHomeMenu(ArrayList<Course> c, ArrayList<Faculty> f, ArrayList<Student> s, ArrayList<GeneralStaff> g) {
	
	    System.out.println("School Database");
    	System.out.println(DASH_CHAIN + "\n" + DASH_CHAIN);
	
    	System.out.println("Current Students: (" + s.size() + ")");
    	System.out.println(DASH_CHAIN);
    	
    	if (s.size() == 0) {
    		System.out.println("No current students.");
    	}
    	else {
    		
    		for (int i = 0; i < s.size(); ++i) {
    			
    			if ((i + 1) % 5 == 1) {
    				System.out.print("| ");
    			}
    			
    			System.out.printf("%8s", s.get(i).getName());
    			
    			if ((i + 1) % 5 != 0 && i != s.size() - 1) {
    				System.out.print(" | ");
    			}
    			
    			if ((i + 1) % 5 == 0) {
    				System.out.println(" |");
    			}
    			
    		}
    		
    		if (s.size() % 5 != 0) {
    			System.out.println(" |");
    		}
    		
    	}
		
    	System.out.println(DASH_CHAIN);
    	System.out.println("Current Faculty: (" + f.size() + ")");
    	System.out.println(DASH_CHAIN);
    	
    	if (f.size() == 0) {
    		System.out.println("No current faculty.");
    	}
    	else {
    		
    		for (int i = 0; i < f.size(); ++i) {
    			
    			if ((i + 1) % 5 == 1) {
    				System.out.print("| ");
    			}
    			
    			System.out.printf("%8s", f.get(i).getName());
    			
    			if ((i + 1) % 5 != 0 && i != f.size() - 1) {
    				System.out.print(" | ");
    			}
    			
    			if ((i + 1) % 5 == 0) {
    				System.out.println(" |");
    			}
    			
    		}
    		
    		if (f.size() % 5 != 0) {
    			System.out.println(" |");
    		}
    		
    	}
		
		System.out.println(DASH_CHAIN);
    	System.out.println("Current Staff: (" + g.size() + ")");
    	System.out.println(DASH_CHAIN);
    	
    	if (g.size() == 0) {
    		System.out.println("No current staff.");
    	}
    	else {
    		
    		for (int i = 0; i < g.size(); ++i) {
    			
    			if ((i + 1) % 5 == 1) {
    				System.out.print("| ");
    			}
    			
    			System.out.printf("%8s", g.get(i).getName());
    			
    			if ((i + 1) % 5 != 0 && i != g.size() - 1) {
    				System.out.print(" | ");
    			}
    			
    			if ((i + 1) % 5 == 0) {
    				System.out.println(" |");
    			}
    			
    		}
    		
    		if (g.size() % 5 != 0) {
    			System.out.println(" |");
    		}
    		
    	}
    	
    	System.out.println(DASH_CHAIN);
    	System.out.println("Current Courses: (" + c.size() + ")");
    	System.out.println(DASH_CHAIN);
    	
    	if (c.size() == 0) {
    		System.out.println("No current courses.");
    	}
    	else {
    		
    		for (int i = 0; i < c.size(); ++i) {
    			
    			if ((i + 1) % 5 == 1) {
    				System.out.print("| ");
    			}
    			
    			System.out.printf("%8s", c.get(i).getCourseName());
    			
    			if ((i + 1) % 5 != 0 && i != c.size() - 1) {
    				System.out.print(" | ");
    			}
    			
    			if ((i + 1) % 5 == 0) {
    				System.out.println(" |");
    			}
    			
    		}
    		
    		if (c.size() % 5 != 0) {
    			System.out.println(" |");
    		}
    		
    	}
    	
    	System.out.println(DASH_CHAIN + "\n" + DASH_CHAIN);
    	
	}
	
	public static void printViewMenu(ArrayList<Course> c, ArrayList<Faculty> f, ArrayList<Student> s, ArrayList<GeneralStaff> g) {
		
		int num = 1;
		
		System.out.println(DASH_CHAIN);
		System.out.println("Students:");
		System.out.println(DASH_CHAIN);
		
		if (s.size() == 0) {
    		System.out.println("///");
    	}
    	else {
    		
    		for (int i = 0; i < s.size(); ++i) {
    			
    			System.out.print(num + ". ");
    			++num;
    			
    			System.out.printf("%7s", s.get(i).getName());
    			
    			if ((i + 1) % 5 != 0 && i != s.size() - 1) {
    				System.out.print(" | ");
    			}
    			
    			if ((i + 1) % 5 == 0) {
    				System.out.println("");
    			}
    			
    		}
    		
    		if (s.size() % 5 != 0) {
    			System.out.println();
    		}
    		
    	}
		
		System.out.println(DASH_CHAIN);
		System.out.println("Faculty:");
		System.out.println(DASH_CHAIN);
		
		if (f.size() == 0) {
    		System.out.println("///");
    	}
    	else {
    		
    		for (int i = 0; i < f.size(); ++i) {
    			
    			System.out.print(num + ". ");
    			++num;
    			
    			System.out.printf("%7s", f.get(i).getName());
    			
    			if ((i + 1) % 5 != 0 && i != f.size() - 1) {
    				System.out.print(" | ");
    			}
    			
    			if ((i + 1) % 5 == 0) {
    				System.out.println("");
    			}
    			
    		}
    		
    		if (s.size() % 5 != 0) {
    			System.out.println();
    		}
    		
    	}
		
		System.out.println(DASH_CHAIN);
		System.out.println("Staff:");
		System.out.println(DASH_CHAIN);
		
		if (g.size() == 0) {
    		System.out.println("///");
    	}
    	else {
    		
    		for (int i = 0; i < g.size(); ++i) {
    			
    			System.out.print(num + ". ");
    			++num;
    			
    			System.out.printf("%7s", g.get(i).getName());
    			
    			if ((i + 1) % 5 != 0 && i != g.size() - 1) {
    				System.out.print(" | ");
    			}
    			
    			if ((i + 1) % 5 == 0) {
    				System.out.println("");
    			}
    			
    		}
    		
    		if (g.size() % 5 != 0) {
    			System.out.println();
    		}
    		
    	}
		
		System.out.println(DASH_CHAIN);
		System.out.println("Courses:");
		System.out.println(DASH_CHAIN);
		
		if (c.size() == 0) {
    		System.out.println("///");
    	}
    	else {
    		
    		for (int i = 0; i < c.size(); ++i) {
    			
    			System.out.print(num + ". ");
    			++num;
    			
    			System.out.printf("%7s", c.get(i).getCourseName());
    			
    			if ((i + 1) % 5 != 0 && i != c.size() - 1) {
    				System.out.print(" | ");
    			}
    			
    			if ((i + 1) % 5 == 0) {
    				System.out.println("");
    			}
    			
    		}
    		
    		if (c.size() % 5 != 0) {
    			System.out.println();
    		}
    		
    	}
		
		System.out.println(DASH_CHAIN);
		
		
	}
	

	public static void main(String[] args) {
	
		ArrayList<Course> c = new ArrayList<>();
	    ArrayList<Faculty> f = new ArrayList<>();
	    ArrayList<Student> s = new ArrayList<>();
	    ArrayList<GeneralStaff> g = new ArrayList<>();
	    
	    ArrayList<Employee> allEmployees = new ArrayList<Employee>();
	    
	    String userInput;
	    int userNum;
	    
	    //
	    
	    System.out.println(DASH_CHAIN);
	    System.out.println("Welcome to the School Database Creator!");
	    System.out.println(DASH_CHAIN);
	    System.out.println("Please wait while we get things started...");
	    System.out.println(DASH_CHAIN);
	    wait(1300);
	    clearConsole();
	    
	    
	    // PRESET OBJECTS
	    Student s1 = new Student("binary", 2006, "CS", false);
	    Student s2 = new Student("Julian", 2006, "CS", true);
	    Course c1 = new Course(false, 168, "CMP", 4);
	    Course c2 = new Course(true, 169, "CMP", 4);
	    Course c3 = new Course(false, 666, "GAY", 6);
	    Course c4 = new Course(true, 420, "XD", 1);
	    Course c5 = new Course(false, 69, "MAT", 1);
	    Course c6 = new Course(false, 121, "ENG", 1);
	    
	    s.add(s1);
	    s.add(s2);
	    
	    c.add(c1);
	    c.add(c2);
	    c.add(c3);
	    c.add(c4);
	    c.add(c5);
	    c.add(c6);
	    
	    //
	    
    	while (true) {
    		
    		try {
    			
    			printHomeMenu(c, f, s, g);
        		
        		System.out.println("Would you like to add, edit, or view something? Type 'q' to quit and go to the final display."); // prompts the user if they want to add, edit, or view
        		
        		while (true) { // loops asking the user until they input something valid ("add", "edit", "view", or "q")
        			
        			userInput = scnr.nextLine();
        			
        			if (userInput.trim().equalsIgnoreCase("add") || userInput.trim().equalsIgnoreCase("edit") || userInput.trim().equalsIgnoreCase("view") || userInput.equals("q")) {
        				break;
        			}
        			
        			System.out.println(invalidInputPrompt); // if the user inputs something invalid, warn them and continue prompting
        			
        		}
        		
        		if (userInput.equalsIgnoreCase("q")) { // if the user types "q" to quit, exits the program
        			clearConsole();
        			break;
    			}
        		
        		if (userInput.equalsIgnoreCase("add")) { // user chooses "add", they are asked what they want to add
        			
        			clearConsole();
        			
        			System.out.println("Would you like to add a student, faculty, staff, or course? " + returnToHome);
        			
        			while (true) { // loops asking the user until they input something valid ("student", "faculty", "staff", "course", or "h")
            			
            			userInput = scnr.nextLine();
            			
            			if (userInput.trim().equalsIgnoreCase("student") || userInput.trim().equalsIgnoreCase("faculty") || userInput.trim().equalsIgnoreCase("staff") || userInput.trim().equalsIgnoreCase("course") || userInput.equals("h")) {
            				break;
            			}
            			
            			System.out.println(invalidInputPrompt); // if the user inputs something invalid, warn them and continue prompting
            			
            		}
        			
        			if (userInput.equals("h")) { // if the user types "h" to go to the home menu, returns all the way to the beginning
        				throw new GoToHomeException();
        			}
        			
        			boolean duplicateInput;
        			
        			switch (userInput.toLowerCase()) {
        			
        				case "student": // user is prompted to create a Student - asks the user for a name (String), birth year (int), major (String), and graduate status (boolean) (or "h" to return home)
        					
        					clearConsole();
        						
        					String studentName;
        					int studentBirthYear;
        					String major;
        					boolean isGrad;
        						
        					System.out.println("Please enter the student's name: (Only letters allowed) " + returnToHome);
        					
        					do { // this loop continues prompting the user until a unique name is input
        						
        						studentName = getValidName(); // prompts the user for a name - this method automatically loops until a valid name is input, or "h"
        						duplicateInput = false;
            					
            					for (Student student : s) {
            						if (student.getName().equals(studentName)) {
            							System.out.println("There is already a student with the name \"" + studentName + "\"! Please input a new name. " + returnToHome);
            							duplicateInput = true;
            						}
            					}
        						
        					}
        					while (duplicateInput);
        						
        					System.out.println("Please enter the student's birth year: (Cannot be negative) " + returnToHome);
        					studentBirthYear = getValidInt(); // prompts the user for a birth year - this method automatically loops until a valid nonnegative number is input, or "h"
        						
        					System.out.println("Please enter the student's major: (Only letters allowed) " + returnToHome);
        					major = getValidName(); // prompts the user for a major - this method automatically loops until a valid name is input, or "h"
        						
        					System.out.println("Please enter if the student is a graduate or not: (true/false) " + returnToHome);
        					isGrad = getValidBoolean(); // prompts the user for a boolean - this method automatically loops until a valid boolean is input, or "h"
        					
        					s.add(new Student(studentName, studentBirthYear, major, isGrad));
        					clearConsole();
        					System.out.println("Student successfully created! Returning to the home menu, please wait...");
        					wait(1000);
        					clearConsole();
        						
        					break;
        					
        				case "faculty": // user is prompted to create a Faculty - asks the user for a name (String), birth year (int), department (String), and tenured status (boolean) (or "h" to return home)
        					
        					clearConsole();
    						
        					String facultyName;
        					int facultyBirthYear;
        					String facultyDept;
        					boolean isTenured;
        						
        					System.out.println("Please enter the faculty's name: (Only letters allowed) " + returnToHome);
        					
        					do { // this loop continues prompting the user until a unique name is input
        						
        						facultyName = getValidName(); // prompts the user for a name - this method automatically loops until a valid name is input, or "h"
        						duplicateInput = false;
            					
            					for (Faculty faculty : f) {
            						if (faculty.getName().equals(facultyName)) {
            							System.out.println("There is already a faculty with the name \"" + facultyName + "\"! Please input a new name. " + returnToHome);
            							duplicateInput = true;
            						}
            					}
        						
        					}
        					while (duplicateInput);
        					
        					System.out.println("Please enter the faculty's birth year: (Cannot be negative) " + returnToHome);
        					facultyBirthYear = getValidInt(); // prompts the user for a birth year - this method automatically loops until a valid nonnegative number is input, or "h"
        						
        					System.out.println("Please enter the faculty's department: (Only letters allowed) " + returnToHome);
        					facultyDept = getValidName(); // prompts the user for a major - this method automatically loops until a valid name is input, or "h"
        						
        					System.out.println("Please enter if the faculty is tenured or not: (true/false) " + returnToHome);
        					isTenured = getValidBoolean(); // prompts the user for a boolean - this method automatically loops until a valid boolean is input, or "h"
        					
        					f.add(new Faculty(facultyName, facultyBirthYear, facultyDept, isTenured));
        					allEmployees.add(f.get(f.size() - 1));
        					clearConsole();
        					System.out.println("Faculty successfully created! Returning to the home menu, please wait...");
        					wait(1000);
        					clearConsole();
        					
        					break;
        					
        				case "staff": // user is prompted to create a GeneralStaff - asks the user for a name (String), birth year (int), department (String), and duty (String) (or "h" to return home)
        					
        					clearConsole();
    						
        					String staffName;
        					int staffBirthYear;
        					String staffDept;
        					String duty;
        						
        					System.out.println("Please enter the staff's name: (Only letters allowed) " + returnToHome);
        					
        					do { // this loop continues prompting the user until a unique name is input
        						
        						staffName = getValidName(); // prompts the user for a name - this method automatically loops until a valid name is input, or "h"
        						duplicateInput = false;
            					
            					for (GeneralStaff staff : g) {
            						if (staff.getName().equals(staffName)) {
            							System.out.println("There is already a staff with the name \"" + staffName + "\"! Please input a new name. " + returnToHome);
            							duplicateInput = true;
            						}
            					}
        						
        					}
        					while (duplicateInput);
        				
        					System.out.println("Please enter the staff's birth year: (Cannot be negative) " + returnToHome);
        					staffBirthYear = getValidInt(); // prompts the user for a birth year - this method automatically loops until a valid nonnegative number is input, or "h"
        					
        					System.out.println("Please enter the staff's department: (Only letters allowed) " + returnToHome);
        					staffDept = getValidName(); // prompts the user for a department - this method automatically loops until a valid name is input, or "h"
        			
        					System.out.println("Please enter the staff's duty: (Only letters allowed) " + returnToHome);
        					duty = getValidName(); // prompts the user for a duty - this method automatically loops until a valid name is input, or "h"
        				
        					g.add(new GeneralStaff(staffName, staffBirthYear, staffDept, duty));
        					allEmployees.add(g.get(g.size() - 1));
        					clearConsole();
        					System.out.println("Staff successfully created! Returning to the home menu, please wait...");
        					wait(1000);
        					clearConsole();
        					
        					break;
        					
        				case "course": // user is prompted to create a Course - asks the user for graduate status (boolean) , course number (int), course department (String), and number of credits (int) (or "h" to return home)
        					
        					clearConsole();
        					
        					boolean isGradCourse;
        					int courseNum;
        					String courseDept;
        					int numCred;
        					
        					System.out.println("Please enter if the course is a graduate course: (true/false) " + returnToHome);
        					isGradCourse = getValidBoolean(); // prompts the user for a boolean - this method automatically loops until a valid boolean is input, or "h"
        				
        					System.out.println("Please enter the course's number: (Cannot be negative) " + returnToHome);
        					courseNum = getValidInt(); // prompts the user for a course number - this method automatically loops until a valid nonnegative number is input, or "h"
        			
        					System.out.println("Please enter the course's department: (Only letters allowed) " + returnToHome);
        					courseDept = getValidName(); // prompts the user for a department - this method automatically loops until a valid name is input, or "h"
        					
        					duplicateInput = false;
        					
        					for (Course course : c) {
        						if (isGradCourse == course.isGraduateCourse() && courseNum == course.getCourseNum() && courseDept.equals(course.getCourseDept())) {
        							duplicateInput = true;
        						}
        					}
        					
        					if (duplicateInput) {
        						clearConsole();
    							System.out.println("There is already a course \"" + (isGradCourse ? "G" : "U") + courseDept + courseNum + "\"! Returning to the home menu, please wait...");
    							wait(1500);
    							clearConsole();
    							break;
        					}
        				
        					System.out.println("Please enter the course's number of credits: (Cannot be negative) " + returnToHome);
        					numCred = getValidInt(); // prompts the user for a number of credits - this method automatically loops until a valid nonnegative number is input, or "h"
        					
        					c.add(new Course(isGradCourse, courseNum, courseDept, numCred));
        					clearConsole();
        					System.out.println("Course successfully created! Returning to the home menu, please wait...");
        					wait(1000);
        					clearConsole();
        					
        					break;
        					
        			}		
        
        		}
        		else if (userInput.equalsIgnoreCase("edit")) { // user chooses "edit"
        			
        			clearConsole();
        			
        			if ((s.size() == 0 && f.size() == 0) || c.size() == 0) { // checks if there are either no people or no courses - that means editing isn't possible
        				
        				System.out.println("You can't edit anything yet! You must have at least some faculty/students and some courses first.");
        				System.out.println("Returning to the home menu, please wait...");
    					wait(1000);
    					clearConsole();
    					
        			}
        			else {
        				
        				int chosenPersonIndex; // the variable storing the index of the chosen person
        				Course[] chosenCourses = new Course[1]; // the array storing the courses that the user wants to add to their chosen person
        				int[] chosenCoursesIndices = new int[1]; // the int array storing the indices of the courses that the user wishes to add, scanned in via getValidIntArray
        				
        				boolean isChosenPersonStudent;
        				Student chosenStudent = null;
        				Faculty chosenFaculty = null;
      
        				ArrayList<Person> people = new ArrayList<>(); // ArrayList storing the students and faculty combined to display in a list
        				ArrayList<Course> availableCourses =  new ArrayList<>(); // ArrayList storing the courses that a student/faculty doesn't currently have (makes usage of checkIfCourseIsAvailable)
        				
        				System.out.println("Here is a list of all current students and faculty. Who would you like to add a course to? Please enter a number. " + returnToHome + "\n");
        				
        				for (Student student : s) { // storing students and faculty in an ArrayList of people and then displaying them in a list
        					people.add(student);
        					System.out.println(people.size() + ". " + student.getName() + " (Student)");
        				}
        				
        				for (Faculty faculty : f) {
        					people.add(faculty);
        					System.out.println(people.size() + ". " + faculty.getName() + " (Faculty)");
        				}
        				
        				userNum = getValidInt(people.size()); // prompts the user for a number matching their chosen person - this method automatically loops until a valid nonnegative number that's within the size of the array is input, or "h"
        				
        				clearConsole();
        				chosenPersonIndex = userNum - 1; // stores the index of the user's chosen person
        				
        				if (people.get(chosenPersonIndex) instanceof Student) { // store a variable determining if the chosen person is a student or faculty, as they are different classes
        					chosenStudent = (Student) people.get(chosenPersonIndex);
        					isChosenPersonStudent = true;
        				}
        				else {
        					chosenFaculty = (Faculty) people.get(chosenPersonIndex);
        					isChosenPersonStudent = false;
        				}
        				
        				for (Course course : c) { // populates availableCourses ArrayList with courses that aren't taken/taught by the chosen person
        					
        					if (isChosenPersonStudent) {
        						
        						if (chosenStudent.checkIfCourseIsAvailable(course)) {
        							availableCourses.add(course);
        						}
        						
        					}
        					else {
        						
        						if (chosenFaculty.checkIfCourseIsAvailable(course)) {
        							availableCourses.add(course);
        						}
        						
        					}
        					
        				}
        				
        				
        				if (availableCourses.size() == 0) {
        					
        					clearConsole();
        					System.out.println("There are currently no available courses for this person! They are " + (isChosenPersonStudent ? "taking" : "teaching") + " all " + c.size() + " existing courses. Returning to the home menu, please wait...");
        					wait(1500);
        					clearConsole();
        					continue;
        					
        				}
        				
        				// now prompting user to select how many courses they want to add after choosing their person, if there is at least one available course for them
        				
        				System.out.println("You have selected: " + people.get(chosenPersonIndex).getName() + (isChosenPersonStudent ? " (Student)" : " (Faculty)"));
        				
        				if (isChosenPersonStudent) {
        					
        					if (chosenStudent.getNumCoursesTaken() == 0) {
        						System.out.println("This person is not currently taking any courses.\n");
        					}
        					else {
        						
        						System.out.print("This person is currently taking " + chosenStudent.getNumCoursesTaken() + " courses: ");
        						
        						for (int i = 0; i < chosenStudent.getNumCoursesTaken(); ++i) {
        							
        							System.out.print(chosenStudent.getCoursesTaken()[i].getCourseName());
        							
        							if (i != chosenStudent.getNumCoursesTaken() - 1) {
        								System.out.print(", ");
        							}
        							
        						}
        						
        						System.out.println("\n");
        						
        					}
        					
        				}
        				else {
        					
        					if (chosenFaculty.getNumCoursesTaught() == 0) {
        						System.out.println("This person is not currently teaching any courses.\n");
        					}
        					else {
        						
        						System.out.print("This person is currently teaching " + chosenFaculty.getNumCoursesTaught() + " courses: ");
        						
        						for (int i = 0; i < chosenFaculty.getNumCoursesTaught(); ++i) {
        							
        							System.out.print(chosenFaculty.getCoursesTaught()[i].getCourseName());
        							
        							if (i != chosenFaculty.getNumCoursesTaught() - 1) {
        								System.out.print(", ");
        							}
        							
        						}
        						
        						System.out.println("\n");
        						
        					}
        					
        				}
        				
        				if (availableCourses.size() == 1) { // only one course available - displays the only available course and asks if the user wishes to add it or not
        					
        					System.out.println("There is currently only 1 course available for this person: " + availableCourses.get(0).getCourseName() + ". Would you like to add it to this person? (yes/no)");
        					
        					String yesOrNo;
        					
        					while (true) {
        						
        						yesOrNo = scnr.nextLine();
        						
        						if (yesOrNo.equals("h") || yesOrNo.trim().equalsIgnoreCase("no")) {
        							throw new GoToHomeException();
        						}
        						
        						if (yesOrNo.trim().equalsIgnoreCase("yes")) {
        							break;
        						}
        						
        						System.out.println(invalidInputPrompt);
        						
        					}
        					
        					chosenCourses[0] = availableCourses.get(0);
        					
        				}
        				else {
        					
        					System.out.println("There are currently " + availableCourses.size() + " courses available for this person. How many courses would you like to add to this person? " + returnToHome + "\n");
            				
            				for (int i = 0; i < availableCourses.size(); ++i) {
            					
            					System.out.println((i + 1) + ". " + availableCourses.get(i).getCourseName());
            					
            				}
            					
            				userNum = getValidInt(availableCourses.size()); // prompts the user for a number - this method automatically loops until a valid nonnegative number that's within the size of the array is input, or "h"
        					
        					chosenCourses = new Course[userNum]; // initializes an array of courses to be added based on how many courses the user wants to add to the person
        					
        					
        					if (chosenCourses.length == availableCourses.size()) { // if the user wants to add ALL the available courses to the person
        						
        						for (int i = 0; i < chosenCourses.length; ++i) {
        							chosenCourses[i] = availableCourses.get(i); // populate array with ALL the available courses
        						}
        						
        					}
        					else { // if the user wants to add only some of the available courses
        						
        						if (chosenCourses.length == 1) { // if the user only wants to add a single course
        							
        							System.out.println("Which course would you like to add? Specify its number as it appears in the list. " + returnToHome);
        							
        							int chosenCourseIndex = getValidInt(availableCourses.size());
        							
        							chosenCoursesIndices[0] = chosenCourseIndex;
        							
        						}
        						else { // if the user wants to add multiple courses
        							
        							System.out.println("Which courses would you like to add? Specify their numbers as they appear in the list, separating each number with a comma, from first to last. " + returnToHome);
            						
            						chosenCoursesIndices = getValidIntArray(availableCourses.size(), chosenCourses.length); // automatically loops prompting the user until they input a valid list of numbers
            						
        						}
        						
        						for (int i = 0; i < chosenCourses.length; ++i) {
        							chosenCourses[i] = availableCourses.get(chosenCoursesIndices[i] - 1);
        						}
        				
        					}
        					
        				}
        				
        				if (isChosenPersonStudent) { // adds the courses to the person
							chosenStudent.addCoursesTaken(chosenCourses);
						}
						else {
							chosenFaculty.addCoursesTaught(chosenCourses);
						}
    					
    					// final display screen indicating success
        				clearConsole();
        				System.out.println("Course(s) successfully added! Returning to the home menu, please wait...");
        				wait(1000);
        				clearConsole();
        				continue;
    					
        			}
        			
        		}
        		else if (userInput.equalsIgnoreCase("view")) { // user chooses "view"
        			
        			ArrayList<Object> allObjs = new ArrayList<Object>(); // initialize ArrayList to contain all the current students, faculty, genstaff, and courses
        			
        			for (Student student : s) {
        				allObjs.add(student);
        			}
        			
        			for (Faculty faculty : f) {
        				allObjs.add(faculty);
        			}
        			
        			for (GeneralStaff staff : g) {
        				allObjs.add(staff);
        			}
        			
        			for (Course course : c) {
        				allObjs.add(course);
        			}
        			
        			while (true) {
        				
        				if (allObjs.size() == 0) {
            				clearConsole();
            				System.out.println("Nothing to see here yet! Add some objects first. Returning to the home menu...");
            				wait(1500);
            				clearConsole();
            				break;
            			}
        				
        				int chosenObjIndex; // variable storing index of chosen object
        				Student chosenStudent = null;
        				Faculty chosenFaculty = null;
        				GeneralStaff chosenStaff = null;
        				Course chosenCourse = null;
        				
        				while (true) {
        					
        					clearConsole();
        					printViewMenu(c, f, s, g);
        					
        					/////
        					
        					int mostCoursesTaught;
        	        		int leastCoursesTaught;
        	        		
        	        		ArrayList<Faculty> facultyMostCoursesTaught = new ArrayList<Faculty>();
        	        		ArrayList<Faculty> facultyLeastCoursesTaught = new ArrayList<Faculty>();
        	        		
        	        		if (f.size() == 0) {
        	        			System.out.println("Faculty teaching the most classes: -");
        	        			System.out.println("Faculty teaching the least classes: -");
        	        		}
        	        		else {
        	        			
        	        			mostCoursesTaught = Collections.max(f).getNumCoursesTaught(); // initialize variable with most classes taught, in case there is more than one faculty teaching the most classes
        	        			leastCoursesTaught = Collections.min(f).getNumCoursesTaught(); // initialize variable with least classes taught
        	        			
        	        			
        	        			for (Faculty faculty : f) {
        	        				if (faculty.getNumCoursesTaught() == mostCoursesTaught) {
        	        					facultyMostCoursesTaught.add(faculty);
        	        				}
        	        				if (faculty.getNumCoursesTaught() == leastCoursesTaught) {
        	        					facultyLeastCoursesTaught.add(faculty);
        	        				}
        	        			}
        	        			
        	        			System.out.print("Faculty teaching the most classes: ");
        	        			
        	        			for (int i = 0; i < facultyMostCoursesTaught.size(); ++i) {
        	        				
        	        				System.out.print(facultyMostCoursesTaught.get(i).getName());
        	        				
        	        				if (i != facultyMostCoursesTaught.size() - 1) {
        	        					System.out.print(", ");
        	        				}
        	        				
        	        			}
        	        			
        	        			System.out.println(" (" + mostCoursesTaught + ")");
        	        			
        	        			
        	        			
        	        			System.out.print("Faculty teaching the least classes: ");
        	        			
        	        			for (int i = 0; i < facultyLeastCoursesTaught.size(); ++i) {
        	        				
        	        				System.out.print(facultyLeastCoursesTaught.get(i).getName());
        	        				
        	        				if (i != facultyLeastCoursesTaught.size() - 1) {
        	        					System.out.print(", ");
        	        				}
        	        				
        	        			}
        	        			
        	        			System.out.println(" (" + leastCoursesTaught + ")");
        	        			
        	        		}
        					
        					
        					/////
        					
        	        		
        	        		int mostCreditsTaken;
        	        		int leastCreditsTaken;
        	        		
        	        		ArrayList<Student> studentMostCreditsTaken = new ArrayList<Student>();
        	        		ArrayList<Student> studentLeastCreditsTaken = new ArrayList<Student>();
        	        		
        	        		if (s.size() == 0) {
        	        			System.out.println("Student taking the most credits: -");
        	        			System.out.println("Student taking the least credits: -");
        	        		}
        	        		else {
        	        			
        	        			mostCreditsTaken = Collections.max(s).getTotalCredits(); // initialize variable with most credits taken, in case there is more than one faculty teaching the most classes
        	        			leastCreditsTaken = Collections.min(s).getTotalCredits(); // initialize variable with least credits taken
        	        			
        	        			
        	        			for (Student student : s) {
        	        				if (student.getTotalCredits() == mostCreditsTaken) {
        	        					studentMostCreditsTaken.add(student);
        	        				}
        	        				if (student.getTotalCredits() == leastCreditsTaken) {
        	        					studentLeastCreditsTaken.add(student);
        	        				}
        	        			}
        	        			
        	        			System.out.print("Student taking the most credits: ");
        	        			
        	        			for (int i = 0; i < studentMostCreditsTaken.size(); ++i) {
        	        				
        	        				System.out.print(studentMostCreditsTaken.get(i).getName());
        	        				
        	        				if (i != studentMostCreditsTaken.size() - 1) {
        	        					System.out.print(", ");
        	        				}
        	        				
        	        			}
        	        			
        	        			System.out.println(" (" + mostCreditsTaken + ")");
        	        			
        	        			
        	        			
        	        			System.out.print("Student taking the least credits: ");
        	        			
        	        			for (int i = 0; i < studentLeastCreditsTaken.size(); ++i) {
        	        				
        	        				System.out.print(studentLeastCreditsTaken.get(i).getName());
        	        				
        	        				if (i != studentLeastCreditsTaken.size() - 1) {
        	        					System.out.print(", ");
        	        				}
        	        				
        	        			}
        	        			
        	        			System.out.println(" (" + leastCreditsTaken + ")");
        	        			
        	        		}
        	        		
        	        		
        	        		if (c.size() == 0) {
        	        			System.out.println("First course in catalog: - | Last course in catalog: -");
        	        		}
        	        		else {
        	        			System.out.println("First course in catalog: " + Collections.min(c).getCourseName() + " | " + "Last course in catalog: " + Collections.max(c).getCourseName());
        	        		}
        	        		
        	        		
        					
        					System.out.println("\nHere is a list of all the current objects. Which one would you like to inspect? Please enter a number as it appears in the list. " + returnToHome);
        		
        					userNum = getValidInt(allObjs.size()); // prompt user to select a number from the list of objects
        					
        					chosenObjIndex = userNum - 1;
        					
        					if (allObjs.get(chosenObjIndex) instanceof Student) {
        						
        						chosenStudent = (Student) allObjs.get(chosenObjIndex);
        						
        						clearConsole();
    							System.out.println("You chose: " + chosenStudent.getName() + (chosenStudent.isGraduate() ? " (Graduate Student)" : " (Undergraduate Student)"));
    							System.out.println("Birth year: " + chosenStudent.getBirthYear() + " | Student ID: " + chosenStudent.getStudentID() + " | Major: " + chosenStudent.getMajor());
    							
        						if (chosenStudent.getNumCoursesTaken() == 0) {
        							
        							System.out.println("\nThey are not currently taking any courses.");
        							
        						}
        						else {
        							
        							System.out.println("\nThey are currently taking " + chosenStudent.getNumCoursesTaken() + (chosenStudent.getNumCoursesTaken() == 1 ? " course: (" : " courses: (") + chosenStudent.getTotalCredits() + (chosenStudent.getTotalCredits() == 1 ? " credit)" : " credits)"));
        							
        							for (int i = 0; i < chosenStudent.getNumCoursesTaken(); ++i) {
            							
        								System.out.print((i + 1) + ". ");
            							System.out.println(chosenStudent.getCoursesTaken()[i].getCourseName() + " (" + chosenStudent.getCoursesTaken()[i].getNumCredits() + ")");
            							
            						}
            						
            						
        						}
        						
            					
        					}
        					else if (allObjs.get(chosenObjIndex) instanceof Faculty) {
        						
        						chosenFaculty = (Faculty) allObjs.get(chosenObjIndex);
        						
        						clearConsole();
    							System.out.println("You chose: " + chosenFaculty.getName() + (chosenFaculty.isTenured() ? " (Tenured Faculty)" : " (Untenured Faculty)"));
    							System.out.println("Birth Year: " + chosenFaculty.getBirthYear() + " | Employee ID: " + chosenFaculty.getEmployeeID() + " | Department: " + chosenFaculty.getDeptName());
    							
    							
        						if (chosenFaculty.getNumCoursesTaught() == 0) {
        							
        							System.out.println("\nThey are not currently teaching any courses.");
        							
        						}
        						else {
        							
        							System.out.println("\nThey are currently teaching " + chosenFaculty.getNumCoursesTaught() + (chosenFaculty.getNumCoursesTaught() == 1 ? " course: " : " courses: "));
        							
        							for (int i = 0; i < chosenFaculty.getNumCoursesTaught(); ++i) {
            							
        								System.out.print((i + 1) + ". ");
            							System.out.println(chosenFaculty.getCoursesTaught()[i].getCourseName());
            							
            						}
            						
        						}
        						
        						
        					}
        					else if (allObjs.get(chosenObjIndex) instanceof GeneralStaff) {
        						
        						chosenStaff = (GeneralStaff) allObjs.get(chosenObjIndex);
        						
        						clearConsole();
    							System.out.println("You chose: " + chosenStaff.getName() + " (General Staff)");
    							System.out.println("Birth Year: " + chosenStaff.getBirthYear() + " | Employee ID: " + chosenStaff.getEmployeeID() + " | Department: " + chosenStaff.getDeptName() + " | Duty: " + chosenStaff.getDuty());
        						
        					}
        					else if (allObjs.get(chosenObjIndex) instanceof Course) {

        						chosenCourse = (Course) allObjs.get(chosenObjIndex);
        						
        						clearConsole();
        						System.out.println("You chose: " + chosenCourse.getCourseName() + (chosenCourse.isGraduateCourse() ? " (Graduate Course)" : " (Undergraduate Course)"));
        						System.out.println("Number of credits: " + chosenCourse.getNumCredits() + "\n");
        						
        						ArrayList<Student> studentsInCourse = new ArrayList<Student>();
        						ArrayList<Faculty> facultyInCourse = new ArrayList<Faculty>();
        						
        						for (Student student : s) { // initialize ArrayList with students taking the chosen course
        							if (!student.checkIfCourseIsAvailable(chosenCourse)) {
        								studentsInCourse.add(student);
        							}
        						}
        						
        						for (Faculty faculty : f) { // initialize ArrayList with faculty teaching the chosen course
        							if (!faculty.checkIfCourseIsAvailable(chosenCourse)) {
        								facultyInCourse.add(faculty);
        							}
        						}
        						
        						if (studentsInCourse.size() == 0) {
        							System.out.println("There are no students taking this course.\n");
        						}
        						else {
        							
        							System.out.print("Students taking this course (" + studentsInCourse.size() + "): ");
        							
        							for (int i = 0; i < studentsInCourse.size(); ++i) {
            							System.out.print(studentsInCourse.get(i).getName()); // print out students in studentsInCourse list
            							
            							if (i != studentsInCourse.size() - 1) {
            								System.out.print(", ");
            							}
            							
            						}
        							
        							System.out.println("\n");
        							
        						}
        						
        						
        						if (facultyInCourse.size() == 0) {
        							System.out.println("There is no faculty teaching this course.");
        						}
        						else {
        							
        							System.out.print("Faculty teaching this course (" + facultyInCourse.size() + "): ");
        							
        							for (int i = 0; i < facultyInCourse.size(); ++i) {
            							System.out.print(facultyInCourse.get(i).getName()); // print out faculty in facultyInCourse list
            							
            							if (i != facultyInCourse.size() - 1) {
            								System.out.print(", ");
            							}
            							
            						}
        							
        							System.out.println();
        							
        						}
        						
        					}
        					
        					System.out.println("\nType \"r\" to remove this object. Type anything else to return to the view menu.");
							userInput = scnr.nextLine();
							
							if (userInput.equals("r")) {
								
								if (allObjs.get(chosenObjIndex) instanceof Student) {
									
									for (int i = s.indexOf(chosenStudent); i < s.size(); ++i) { // decrementing every ID for students above the student being removed
										s.get(i).decrementStudentID();
									}
									
									allObjs.remove(chosenStudent);
									s.remove(chosenStudent);
									
									Student.decrementNumStudents();
									
								}
								else if (allObjs.get(chosenObjIndex) instanceof Faculty) {
									
									for (int i = allEmployees.indexOf(chosenFaculty); i < allEmployees.size(); ++i) {
										allEmployees.get(i).decrementEmployeeID();
									}
								
									allObjs.remove(chosenFaculty);
									allEmployees.remove(chosenFaculty);
									f.remove(chosenFaculty);
									
									Employee.decrementNumEmployees();
									
								}
								else if (allObjs.get(chosenObjIndex) instanceof GeneralStaff) {
									
									for (int i = allEmployees.indexOf(chosenStaff); i < allEmployees.size(); ++i) {
										allEmployees.get(i).decrementEmployeeID();
									}
									
									allObjs.remove(chosenStaff);
									allEmployees.remove(chosenStaff);
									g.remove(chosenStaff);
									
									Employee.decrementNumEmployees();
									
								}
								else if (allObjs.get(chosenObjIndex) instanceof Course) {
									allObjs.remove(chosenCourse);
									c.remove(chosenCourse);
									
									for (Student student : s) {
										if (!student.checkIfCourseIsAvailable(chosenCourse)) {
											student.removeCourse(chosenCourse);
										}
									}
									
									for (Faculty faculty : f) {
										if (!faculty.checkIfCourseIsAvailable(chosenCourse)) {
											faculty.removeCourse(chosenCourse);
										}
									}
									
								}
								
							}
							
							break;
        					
        				}
        				
        			}
        			
        			
        		}
        		
        	}
        	catch (GoToHomeException e){ // triggers whenever the user wants to go to the home menu at any stage of the program, going all the way back to the start of the loop
        		
        		clearConsole();
        		System.out.println("Returning to the home menu, please wait...");
        		wait(800);
        		clearConsole();
        		continue;
        		
        	}
    		catch (ArrayFullException e){ // triggers in the EDIT tab, when the user wants to add courses but there is not enough space in the person's array
    			
    			clearConsole();
        		System.out.println("Too many courses are being added! A student may only take up to 10 courses. Returning to the home menu, please wait...");
        		wait(1500);
        		clearConsole();
        		continue;
    			
    		}
    		
    	}
    	
    	// triggers whenever the user wants to quit the menu and begin outputting stuff
    	
    	final String LONG_ASTERISK_CHAIN = "**************************************************************";
    	final String SHORT_ASTERISK_CHAIN = "************************************************";
    	String filePath = "src/real.txt";
    		
    	writeToFile(filePath, false, LONG_ASTERISK_CHAIN + "\nSCHOOL DATABASE INFO\n" + SHORT_ASTERISK_CHAIN + "\nCOURSES:");
    	
    	for (Course course : c) {
    		writeToFile(filePath, true, course.toString());
    	}
    	
    	writeToFile(filePath, true, SHORT_ASTERISK_CHAIN + "\n" + SHORT_ASTERISK_CHAIN + "\n" + "GENERAL STAFF:");
	    
    	for (GeneralStaff staff : g) {
    		writeToFile(filePath, true, staff.toString());
    	}
    	
    	writeToFile(filePath, true, SHORT_ASTERISK_CHAIN + "\n" + SHORT_ASTERISK_CHAIN + "\n" + "FACULTY:");
    	
    	for (Faculty faculty : f) {
    		writeToFile(filePath, true, faculty.toString());
    	}
    	
    	writeToFile(filePath, true, SHORT_ASTERISK_CHAIN + "\n" + SHORT_ASTERISK_CHAIN + "\n" + "STUDENTS:");
    	
    	for (Student student : s) {
    		writeToFile(filePath, true, student.toString());
    	}
    	
    	writeToFile(filePath, true, SHORT_ASTERISK_CHAIN + "\n" + LONG_ASTERISK_CHAIN);
    	
  
    	displayFromFile(filePath);
    	System.out.println("\nHere is the final display, type anything to continue and exit the program.");
    	scnr.nextLine();
    	
    	clearConsole();
    	System.out.println("Goodbye! Please check \"" + filePath + "\" for the output.");
	
   }  
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	

