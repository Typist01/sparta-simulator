package com.sparta.simulator.model;

import java.util.Random;

// Random number generators
public class RandGenerator {

    public static int randomTrainee() {
        Random rand = new Random();
        return rand.nextInt(50) + 50;

    }

    public static int randomCenter() {
        Random rand = new Random();
        return rand.nextInt(50);
    }

    public static int generateClientRequest() {
        Random random = new Random();
        switch (random.nextInt(9)) {
            case 0, 1, 2, 3:
                return random.nextInt(15, 20); //40% chance for 15-20
            case 4, 5, 6:
                return random.nextInt(12, 25); //30% chance for 12-25
            case 7, 8:
                return random.nextInt(10, 35);    //20% chance for 10-35
            case 9:
                return random.nextInt(5, 50);        //10% chance for 5-50
        }
        return 15;
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

}
