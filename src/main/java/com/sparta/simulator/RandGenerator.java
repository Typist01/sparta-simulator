package com.sparta.simulator;

import java.util.Random;

public class RandGenerator {

    public int randomTrainee(){
        Random rand = new Random();
        return  rand.nextInt(50,100);

    }
    public int randomCenter(){
        Random rand = new Random();
        return rand.nextInt(0,50);
    }

}
