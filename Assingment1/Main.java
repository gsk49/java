class Main {
  public static void main(String[] args) {

		//Create new courses

    Course csds = new Course("CSDS233", "Data Structures", 50);
		Course phys = new Course("PHYS115", "Intro to Physics", 300);
		Course engr = new Course("ENGR131b", "Engineering Sampler", 32);

		//Create a new courseList, 'listOfCourses'

		courseList listOfCourses = new courseList();
		
		//Add the classes you created into the courseList with addCourse();

		listOfCourses.addCourse(csds, 0);
		listOfCourses.addCourse(phys, 1);
		listOfCourses.addCourse(engr, 2);
		
		//Print out the courses in your courseList

		listOfCourses.listOfCourses();

		//Print out the size of your courseList 'listOfCourses'

		System.out.println(listOfCourses.size());

		//Remove the course at index 1 (PHYS115) using removeCourse();
		
		listOfCourses.removeCourse(1);

		//Change the capacity of the item in listOfCourses with the ID 'CSDS233' to 51

		listOfCourses.changeCapacity("CSDSS233", 51);

		//Find the course at index 0 in listOfCourses, and then find the courseID. Then do the same thing with courseName and capacity

		System.out.print(listOfCourses.getCourseWithIndex(0).getCID()+" ");
		System.out.print(listOfCourses.getCourseWithIndex(0).getCName()+" ");
		System.out.println(listOfCourses.getCourseWithIndex(0).getCap());

		//Print out the index of the courseID for PHYS115 (Nonexistent, -1) and CSDS233 (0)

		System.out.println(listOfCourses.searchCourseID("PHYS115"));
		System.out.println(listOfCourses.searchCourseID("CSDS233"));

		//Print out the index of the courseName for Data Structures (0) and Intro to Physics (nonexistent, -1)

		System.out.println(listOfCourses.searchCourseName("Data Structures"));
		System.out.println(listOfCourses.searchCourseID("Intro to Physics"));

		//Create new courses fsso and phys121

		Course fsso = new Course("FSSO186D", "Art, Culture and the City", 23);
		Course phys121 = new Course("PHYS121", "Intro to Physics and Mechanics", 205);

		//add these new courses to listOfCourses at index 9, or earlier if possible (it is)

		listOfCourses.addCourse(fsso, 9);
		listOfCourses.addCourse(phys121, 9);

		//Print out the new list of courses

		listOfCourses.listOfCourses();
  }
}


class Course{

	//Create new instance variables courseID, courseName, capacity

  private String courseID;
  private String courseName;
  private int capacity;

	//Create a new instantiation of course with the instance varibles cID, cName and cap

  Course(String cID, String cName, int cap){
    courseID = cID;
    courseName = cName;
    capacity = cap;
  }

	//creates new getter methods getCID(), getCName() and getCap()

	public String getCID(){
		return courseID;
	}
	public String getCName(){
		return courseName;
	}
	public int getCap(){
		return capacity;
	}

	//creates new setter methods setCap(), setCName() and setCID()

	public void setCap(int c){
		capacity = c;
	}
	public void setCName(String c){
		courseName = c;
	}
	public void setCID(String c){
		courseName = c;
	}
}

class courseList{

	//creates new array of courses called courseList with an iitial size of 10

	Course[] courseList = new Course[10];

	
	//New function size returns the number of courses in courseList

	public int size(){
		int counter=0;
		for (int i=0; i<courseList.length; i++){
			if (courseList[i] != null){
				counter++;
			}
		}
		return counter;
	}

	//New function addCourse that takes in inputs of a course c and int index
	//Function adds the course c into courseList at index 'index', or earlier if applicable

	public void addCourse(Course c, int index){
		A: if (courseList.length >=index+1){
			for (int i=0; i<=index;i++){
				if (courseList[i] == null){
					courseList[i] = c;
					break A;
				}
			}
			for (int x = courseList.length; x>index; x--){
				courseList[x-1] = courseList[x-2];

			}
			courseList[index] = c;
		}
	}

	//New function listOfCourses prints out every course in courseList

	public void listOfCourses(){
		System.out.println("Courses: ");
		for (int i=0; i<courseList.length; i++){
			if (courseList[i] != null){
				System.out.println(courseList[i].getCID()+" "+courseList[i].getCName()+" "+courseList[i].getCap());
			}
		}
	}

	//New function removeCourse removes the course at the given index (i) and shifts all following variables to the right and return true
	//if there isn't a course there, return false

	public boolean removeCourse(int i){
		if (i<courseList.length){
			for (int x=i; x<courseList.length-1; x++){
				courseList[x] = courseList[x+1];
			}
			return true;
		}
		return false;
	}

	//New function changeCapacity changes the capacity of the first item with a string of the inputted var 'cid' to the inputted var 'cap' and returns true
	//If there is no course with courseID == to cid in courseList, return false

	public boolean changeCapacity(String cid, int cap){
		for (int i=0; i<courseList.length; i++){
			if (courseList[i] != null){
				if (courseList[i].getCID() == cid){
					courseList[i].setCap(cap);
					return true;
				}
			}
		}
		return false;
	}

	//New Function getCourseWithIndex returns the course located @ index i
	//If there is no course at that index, returns null
	
	public Course getCourseWithIndex(int i){
		return courseList[i];
	}

	//creates new method searchCourseID() that returns the index of the first item in courseList with courseId == courseID (inputted variable)

	public int searchCourseID(String courseID){
		for (int i=0; i<courseList.length; i++){
			if (courseList[i] != null){
				if (courseList[i].getCID() == courseID){
					return i;
				}
			}
		}
		return -1;
	}

	//New method searchCourseName() that returns the first course in courseList with the courseName == courseName (inputted variable)
	
	public int searchCourseName(String courseName){
		for (int i=0; i<courseList.length; i++){
			if (courseList[i] != null){
				if (courseList[i].getCName() == courseName){
					return i;
				}
			}
		}
		return -1;
	}

}