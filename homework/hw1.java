import java.util.Scanner;

public class hw1 {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		double pos, neg, total;
		String factor;
		
		System.out.println("What is the factor you want to work with?");
		factor = keyboard.nextLine();
		System.out.println("We will begin with calcuating the entropy of the factor: " + factor + ".");
		System.out.println("Please input the number of positive responses.");
		pos = keyboard.nextDouble();
		System.out.println("Please input the number of negative responses.");
		neg = keyboard.nextDouble();
		total = pos + neg;
		
		entropy(pos, neg, total);
		

	}
//	This method will calculate the entropy of a factor by having the user input the number of positive
//	responses and negative responses.
	public static void entropy(double x, double y, double t) 
	{
		double entroValP, entroValN, entroValT;
		entroValP = -x/t * Math.log(x/t);
		entroValN = -y/t * Math.log(y/t);
		entroValT = entroValP + entroValN;
		
		//Testing
		System.out.println(t);
		System.out.println(-x/t);
		System.out.println(Math.log(x/t));
		// Testing the log function
		System.out.println(Math.log(0.6));
		System.out.println(-x/t * Math.log(x/t));
		System.out.println(-y/t);
		System.out.println(Math.log(y/t));
		System.out.println(-y/t * Math.log(y/t));
		System.out.println("Hello");
		System.out.println("World");
		
		// This is the wrong number when i enter x = 3 and y = 2. I looked it up on someone's website when
		// googling "java calculate gain of entropy".
		System.out.println("The entropy is: " + entroValT);
	}
	
//	This method will calculate the remainder of a factor by having the user input the number of positive
//	responses and negative responses.
	public static void remainder() 
	{
		
	}
	
//	This method will calculate the gain of a factor by having the user input the number of positive
//	responses and negative responses.
	public static void gain() 
	{
		
	}

}
