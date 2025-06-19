package hello;
import java.util.*;
public class CalculatorApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println(" 1 Addition\n 2 Subtraction\n 3 Multiplication\n 4 Divisio\n 5 Modulus\n 6 Exit");
			System.out.print("Enter the number 1: ");
			int n1 = sc.nextInt();
			System.out.println("Enter the number 2: ");
			int n2 = sc.nextInt();
			System.out.println("Enter the choice to perform the operation: ");
			int choice = sc.nextInt();
			switch(choice) {
				case 1:
					System.out.println("Addition : "+ (n1+ n2));
					break;
				case 2:
					System.out.println("Subtraction: " + (n1 - n2));
					break;
				case 3:
					System.out.println("Multiplication: " + (n1 * n2));
					break;
				case 4:
					System.out.println("Division: "+ (n1 / n2));
					break;
				case 5:
					System.out.println("Modulus: "+ (n1 % n2));
					break;
				case 6:
					System.out.println("Exitting from the calculator");
					System.exit(0);
					break;
				
				default:
					System.out.println("Enter valid choice to perform the operation!!");
					break;
			}
			
			sc.close();
				
		}
		
		
		
		

	}

}