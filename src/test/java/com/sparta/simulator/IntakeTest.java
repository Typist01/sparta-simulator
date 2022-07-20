package com.sparta.simulator;

import com.sparta.simulator.model.centres.Centre;
import com.sparta.simulator.model.Intake;
import com.sparta.simulator.model.Trainee;
import com.sparta.simulator.model.centres.TrainingHub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    }
    
    
    
    @Test
    @DisplayName("check for number of full centers")
    void testnumFullCenters(){
        Intake intake = new Intake();
        Centre hub = new TrainingHub();
        int index = 0;
        while(index<100){
            hub.addTrainee(new Trainee());
            index++;
        }
        intake.testAddCenter(hub);
        Assertions.assertEquals(1,intake.getFullCentres().size());
    }


}