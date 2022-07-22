package com.sparta.simulator.model.centres;

import com.sparta.simulator.model.RandGenerator;
import com.sparta.simulator.model.Trainee;
import com.sparta.simulator.model.centres.BootCamp;
import com.sparta.simulator.model.centres.Centre;
import com.sparta.simulator.model.centres.TrainingHub;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrainingHubTest {
    private Centre centre;
    @BeforeEach
    void setup(){
        centre = new TrainingHub();
    }

    @Test
    void isClosable() {
        for (int i = 0; i < 25; i++) {
            if (i<24){
                assertTrue(centre.isClosable());
            }
            assertTrue(centre.addTrainee(new Trainee(RandGenerator.generateCourse())));
        }
        assertTrue(centre.addTrainee(new Trainee(RandGenerator.generateCourse())));
        //Shouldnt be closable since it has hit the minimum amount of trainees.
        assertFalse(centre.isClosable());

    }

    @Test
    void isFull(){
        //Bootcamp is empty so shouldnt be full.
        assertFalse(centre.isFull());
        for (int i = 0; i<100;i++){
            if (i<99){
                assertFalse(centre.isFull());
            }
            //can add up to 100 trainees
            assertTrue(centre.addTrainee(new Trainee(RandGenerator.generateCourse())));
        }
        //bootcamp should be full
        assertTrue(centre.isFull());
        //Shouldnt be able to add another employee.
        assertFalse(centre.addTrainee(new Trainee(RandGenerator.generateCourse())));
        //Check is still full
        assertTrue(centre.isFull());
    }
    @Test
    void acceptsTrainee(){
        Trainee trainee = new Trainee(RandGenerator.generateCourse());
        assertTrue(centre.acceptsTrainee(trainee));

    }
}