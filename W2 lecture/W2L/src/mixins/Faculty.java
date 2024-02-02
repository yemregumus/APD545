package mixins;

class Person {

	public Person () {
		System.out.println("Person no-arg's constructor is being called");
	}
}

class Employee extends Person{
	public Employee() {
		//super();
		//this("Calling Employee's overloaded constructor...");
		System.out.println("Employee;s no-arg's constructor is called");
	}

	public Employee(String string) {
		super();
		System.out.println(string);
	}
}

public class Faculty extends Employee{
	public String name;
	
	public static void main(String[] args) {
		Faculty f = new Faculty(); //Anonymous object
		int f1;
		System.out.println(f); //&f
		f.name = "Mike";
		System.out.println(f.name);
		printF (f);
		System.out.println(f.name);
		//printFS(f1);
	}
	
	public Faculty() {
		super();
		System.out.println("Faculty's no arg's constructor is bein called");
	}
	public static void printFS(int s) {
		
	}
	public static void printF(Faculty obj) {
		System.out.println(obj);
		obj.name = "Jack";
	}
}