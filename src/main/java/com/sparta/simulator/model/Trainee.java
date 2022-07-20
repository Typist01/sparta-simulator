package com.sparta.simulator.model;

import java.util.Random;

public class Trainee {
    private String courseType;
    private int monthsTrained = 0;

    public Trainee(){
        int randomNum = new Random().nextInt(5);

        if (randomNum == 0)
        {
            courseType = "Java";
        }
        else if (randomNum == 1)
        {
            courseType = "C#";
        }
        else if(randomNum == 2)
        {
            courseType = "Data";
        }
        else if(randomNum == 3)
        {
            courseType = "DevOps";
        }
        else if(randomNum == 4)
        {
            courseType = "Business";
        }
    }
//    Trainee(String courseType){
//        courseType = courseType
//    }
    public void incrementTimeTrained(){
        monthsTrained++;
    }
    public int getTimetrained(){
        return monthsTrained;
    }
    public String getType() {
        return courseType;
    }

}
