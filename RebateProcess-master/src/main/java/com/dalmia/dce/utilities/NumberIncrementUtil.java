package com.dalmia.dce.utilities;

public class NumberIncrementUtil {

    int instanceCounter = 0;

    static int counter = 0;

    public  NumberIncrementUtil() {

        instanceCounter = counter;
        counter++;
    }
    public static int getNum() {
    	return counter;
    }
}