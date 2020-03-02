package com.experiment;

public class Cal {
	private static int[] coins= {1,1,1,5,5,10,20,50};
	public  Boolean Judge(int sum) {
		Boolean flag=false;
		for(int i=coins.length-1;i>-1;i--) {
			if(sum-coins[i]>=0) {
				sum-=coins[i];
			}
		}
		if(sum==0) {
			flag=true;		
		}		
		return flag;
	}
}
