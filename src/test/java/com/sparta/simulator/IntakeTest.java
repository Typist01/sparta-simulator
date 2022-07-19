package com.sparta.simulator;

import com.sparta.simulator.model.Intake;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntakeTest {

    @Test
    void testWaitingListGeneration(){
        for (int i = 0; i < 100; i++) {
            Intake intake = new Intake();
            intake.addTraineeGroup();
            assertTrue(intake.getWaitingCount() >= 50 && intake.getWaitingCount()<=100);
        }
    }
    @Test
    void testtotaltrainees(){

    }

}