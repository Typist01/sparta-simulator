package com.sparta.simulator;

import java.util.Random;

public class RandGenerator {

    public int randomTrainee(){
        Random rand = new Random();
        return  rand.nextInt(50)+50;

    }
    public int randomCenter(){
        Random rand = new Random();
        return rand.nextInt(50);
    }

}
