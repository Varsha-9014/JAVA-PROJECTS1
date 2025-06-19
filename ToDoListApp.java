package ABC;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListApp {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ArrayList<String> tasks=new ArrayList<String>();
		while(true) {
			System.out.println("simple To-Do-List-App");
			System.out.println("1.Add a Task");
			System.out.println("2.View a Task");
			System.out.println("3.Delete a Task");
			System.out.println("4.Exit");
			System.out.println("choose an option(1-4)");
			
			int choice=sc.nextInt();
			sc.nextLine();
			if(choice==1) {
				System.out.println("Enter your Task");
				String task=sc.nextLine();
				tasks.add(task);
				System.out.println("Task added Successfully");
			}else if(choice==2) {
				if(tasks.isEmpty()) {
					System.out.println("No Task Found");
				}else {
					System.out.println("your Tasks");
					for(int i=0;i<tasks.size();i++) {
						System.out.println((i+1)+" "+tasks.get(i));
					}
				}
			}else if(choice==3) {
				if(tasks.isEmpty()) {
					System.out.println("Nothing to delete");
				}else {
					System.out.println("Enter task Number to Delete");
					int tasknum=sc.nextInt();
					if(tasknum>1 && tasknum <=tasks.size()) {
						tasks.remove(tasknum-1);
						System.out.println("Task Deleted");
					}else {
						System.out.println("Invalid Number");
					}
				}
			}else if(choice==4) {
				System.out.println("Goodbye !!");
				break;
			}else {
				System.out.println("please choose a valid option");
			}
		}
		sc.close();
	}

}
