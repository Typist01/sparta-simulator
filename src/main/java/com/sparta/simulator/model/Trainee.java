package com.sparta.simulator.model;

import java.util.Random;


// Class for creating new trainees
public class Trainee {
    private final CourseType courseType;
    private int monthsTrained = 0;

    public Trainee(CourseType courseType){
        this.courseType = courseType;


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
