package com.sparta.simulator.model;

import java.util.Random;

public class RandGenerator {

    public static int randomTrainee(){
        Random rand = new Random();
        return  rand.nextInt(50)+50;

    }
    public static int randomCenter(){
        Random rand = new Random();
        return rand.nextInt(50);
    }
    public static int generateClientRequest() {
        Random random = new Random();
        switch (random.nextInt(9)){
            case 0,1,2,3: return random.nextInt(15,20); //40% chance for 15-20
            case 4 ,5 ,6: return random.nextInt(12,25); //30% chance for 12-25
            case 7,8 : return random.nextInt(10,35);    //20% chance for 10-35
            case 9 :return random.nextInt(5,50);        //10% chance for 5-50
        }
        return 15;
    }

}
