package com.sparta.simulator.model;

import java.util.Random;


// Class for creating new trainees
public class Trainee {
    private final CourseType courseType;
    private int monthsTrained = 0;

    public Trainee(){
        courseType = RandGenerator.generateRandomCourse();


    }
//    Trainee(String courseType){
//        courseType = courseType
//    }
    public void incrementTimeTrained(){
        monthsTrained++;
    }
    public int getTimeTrained(){
        return monthsTrained;
    }
    public CourseType getType() {
        return courseType;
    }

}
