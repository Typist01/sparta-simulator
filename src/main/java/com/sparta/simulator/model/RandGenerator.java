package com.sparta.simulator.model;

import java.util.Random;

// Random number generators
public class RandGenerator {
    private static final Random random= new Random();
    private static final double PI = 3.14;

    public static int randomTrainee(){
        return  random.nextInt(50)+50;

    }
    public static int randomCenter(){
        return random.nextInt(50);
    }
    public static int generateClientMaxTrainees() {
        switch (random.nextInt(9)){
            case 0,1,2,3: return random.nextInt(15,20); //40% chance for 15-20
            case 4 ,5 ,6: return random.nextInt(12,25); //30% chance for 12-25
            case 7,8 : return random.nextInt(10,35);    //20% chance for 10-35
            case 9 :return random.nextInt(5,50);        //10% chance for 5-50
        }
        return 15;
    }
    public static CourseType generateCourse(){
        int random = new Random().nextInt(5);
        return CourseType.values()[random];
    }

    public static int generateClientRequestStepped() {
        Random random = new Random();
        double randNum = random.nextFloat(0, 1);
        if (randNum <= 0.2) {
            return random.nextInt(1, 15); //20% chance
        } else if (randNum <= 0.6) {
            return random.nextInt(15, 30); // 40% chance
        } else if (randNum <= 0.9) {
            return random.nextInt(30, 60); // 30% chance
        } else {
            return random.nextInt(60, 100); // 40% chance
        }
    }


    // Alternative random generator without steps (rejection method)
    // Might be very inefficient because it uses a normalised gaussian
    // The probability of any number a range of numbers being generated should be the area under the gaussian curve within that range.
    // the range is restricted from 0 to 100.
    public static double getComparison(){
        return new Random().nextDouble(0, 1);
    }
    public static double funcGaussian(int x){
        double a = 11; // lower values concentrates the gaussian close to the mean value.
        double b = 38; // determines the center of the gaussian
        return ((1/(a*Math.sqrt(2*PI)))*Math.exp((-0.5*((x-b)/a))));
    }
    public static int generateRandomFromGaussian(){
        int rand = new Random().nextInt(15, 100);
        if (funcGaussian(rand)>getComparison()){
            return rand;
        } else
            return generateRandomFromGaussian();
    }

}
