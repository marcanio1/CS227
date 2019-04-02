package mini1;

import java.util.Scanner;
import java.math.*;

//import static mini1.LoopTheLoopyLoopingLoopers.*;

public class Looptest {

	public static void main(String[] args) {
		int total = 0;
		double tip= 0;
		int rate=0;
		int temptotal;
		int splitnum =0;
		String split;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the total amount: ");
		total = input.nextInt();
		System.out.println("Rate your experice from 1-5: ");
		rate= input.nextInt();
		if(rate<=0 || rate >5) {
			System.out.println("The rate you gave was out of range please enter a new rating: ");
			rate= input.nextInt();
		}
		if (rate  == 1) {
			tip = .05;
			System.out.println("Sorry your experience was not good");
		}
		else if (rate  == 2) {
			tip = .10;
			System.out.println("Sorry your experience was not up to standards");
		}
		else if(rate ==3) {
			tip = .15;
			System.out.println("I hope you had a good time and we apprecatie your feedback");
		}
		else if(rate ==4) {
			tip = .2;
			System.out.println("Thank you for coming and we appreciate your service!");
		}
		else if(rate ==5) {
			tip = .25;
			System.out.println("Thank you for coming and we are glad you enjoyed your time!");
		}
		temptotal = total;
		temptotal *= tip;
		System.out.println("We reccomened that you tip : $" +temptotal);
		System.out.println("Would you like to split the check? (yes/no):");
		split = input.next();
		if (split.equals("yes")) {
			System.out.println("How many ways do you want to split the check? ");
			splitnum = input.nextInt();
			total = total + temptotal;
			total /= splitnum;
			System.out.println("Thanks for giving us " + rate + " stars. Your total per person will be: $"+ total);
		}
		else if (split.equals("no")) {
		total = total + temptotal;
		System.out.println("Thanks for giving us " + rate + " stars. Your total will be: $"+ total);
		}
	}

	
	
}

