package com.sparta.simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntakeTest {

    private Intake intake;
    @BeforeEach
    void setUp() {
        intake = new Intake();
    }

    @Test
    void addTrainees(){
        intake.addTraineeGroup();
        assertTrue(intake.getWaitingList() >= 50 && intake.getWaitingList()<=100);
    }
}