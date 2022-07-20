package com.sparta.simulator.model;

import java.util.Random;

public class Trainee {
    private String courseType;
    private boolean benchStatus;
    private int monthsTrained = 0;

    private final int MAX_BENCHTIME = 3;
    private int traineeLife;
    public Trainee(){
        int randomNum = new Random().nextInt(5);
        benchStatus = false;
        traineeLife = 0;
        if (randomNum == 0) {
            courseType = "Java";
        }
        else if (randomNum == 1) {
            courseType = "C#";
        }
        else if(randomNum == 2) {
            courseType = "Data";
        }
        else if(randomNum == 3) {
            courseType = "DevOps";
        }
        else if(randomNum == 4) {
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
    public boolean finishedTraining(){
        if(MAX_BENCHTIME > traineeLife){
            traineeLife++;
            return false;
        }
        else return true;
    }
    public void benchTrainee(){
        this.benchStatus = true;
    }
    public boolean getbenchStatus(){
        return this.benchStatus;
    }

}
