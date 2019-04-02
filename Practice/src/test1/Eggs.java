package test1;
import java.util.*;
public class Eggs {

	public static void main(String[] args) {
		int flats;
		int dozens;
		int halfdozens;
		int leftover;
		int price;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("How many eggs ");
        int eggs = input.nextInt();
        System.out.println("Do you want brown eggs (yes/no) ");
        String browneggs = input.next();
        
        
        flats = eggs / 30;
        leftover = eggs % 30;
        dozens = leftover / 12;
        leftover %= 12;
        halfdozens = leftover /6;
        leftover %= 6;
        if(leftover > 0)
        	halfdozens++;
        dozens = dozens + halfdozens/2;
        halfdozens = halfdozens%2;
        	
        
        System.out.println(flats + " flats");
        System.out.println(dozens + " dozens");
        System.out.println(halfdozens + " half dozens");
        price = (int) ((flats * 6.50)+ (dozens * 3)+ (halfdozens *2));
        if (browneggs.equals("yes")) {
        	price *= .2; 
        	System.out.println("Price " + price);
        }
        else 
        	System.out.println("Price " +price);
        	
	}

}
