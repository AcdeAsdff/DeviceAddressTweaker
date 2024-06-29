package com.linearity.utils;

import static com.linearity.utils.ReturnReplacements.getRandomString;

import java.security.SecureRandom;

public class ExtendedSecureRandom extends SecureRandom {

    public int[] randomIntArr(int length){
        int[] result = new int[length];
        for (int i=0;i<length;i++){
            result[i] = this.nextInt();
        }
        return result;
    }
    public String[] randomStrArr(int length){
        String[] result = new String[length];
        for (int i=0;i<length;i++){
            result[i] = getRandomString(this.nextInt(10)+3);
        }
        return result;
    }
}
