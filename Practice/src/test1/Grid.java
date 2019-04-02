package test1;

import java.util.Arrays;
import java.util.Scanner;

public class Grid {

	public static void main(String[] args) {
		Scanner S = new Scanner(System.in);
		int nums[] = new int[10];
		
		

		
		for(int i =0; i< 10; i++) {
		nums[i]= S.nextInt();		
		}
		Arrays.sort(nums);
		
		
		for(int i = 0; i<10; i++)
			System.out.print(" " +nums[i]);
		
	}
}
