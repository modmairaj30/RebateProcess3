package com.dalmia.dce.utilities;

public class SplitingStringUtil {
	public static void main(String[] args) {
		String abc="Bill Value Based Discount";
		String[] a = abc.split(" ");
		
		String s="";
	for(int i=0;i<a.length;i++) {
			s+=a[i].substring(0, 1);
		}
	
		
		
		
	    System.out.println(s);
	   
	}
}
