package com.sparta.simulator;

import com.sparta.simulator.model.Intake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IntakeTest {

    @BeforeEach
    void setUp(){

    }
    @Test
    @DisplayName("waiting list generation test")
    void testWaitingListGeneration(){
        Intake intake = new Intake();
        for (int i = 0; i < 100; i++) {
            intake.addTraineeGroup();
            assertTrue(intake.getWaitingCount() >= 50 && intake.getWaitingCount()<=100);
        }
    }
    @Test
    void testGenerateCentre(){
        System.out.println(intake.getTrainingCentres());
        intake.createCentresRandomly();
        System.out.println(intake.getTrainingCentres());

    }
    
    
    
    @Test
    @DisplayName("check for number of full centers")
    void testnumFullCenters(){
        Intake intake = new Intake();
        TrainingHub hub = new TrainingHub();
        int index = 0;
        while(index<100){
            hub.addTrainee(new Trainee());
            index++;
        }
        intake.testAddCenter(hub);
        Assertions.assertEquals(1,intake.getFullCentres().size());
    }


}