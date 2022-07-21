package com.sparta.simulator.model;

import com.sparta.simulator.model.centres.BootCamp;
import org.junit.jupiter.api.Assertions;
import com.sparta.simulator.model.centres.Centre;
import com.sparta.simulator.model.centres.TrainingHub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
           Assertions.assertTrue(intake.getWaitingCount() >= 50 && intake.getWaitingCount()<=100);
           intake.clearWaiting();
        }
    }
    @Test
    void testGenerateCentre(){
        Intake intake = new Intake();
        System.out.println(intake.getTrainingCentres());
        intake.createCentresRandomly();
        System.out.println(intake.getTrainingCentres());

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

    @Test
    @DisplayName("Testing full centers by type")
    void testingNumFullCentersByType(){
        Intake intake = new Intake();
        TrainingHub hub = new TrainingHub();
        int i = 0;
        while(i<100){
            hub.addTrainee(new Trainee());
            i++;
        }
        intake.testAddCenter(hub);
        Assertions.assertEquals(1,intake.getFullCentreNumByType("TrainingHub"));
    }
    @Test
    @DisplayName("Testing get centre num by type")
    void testGetNumOfTraineesByCenterType(){
        Intake intake = new Intake();
        TrainingHub hub = new TrainingHub();
        int i = 0;
        while (i<50){
            hub.addTrainee(new Trainee());
            i++;
        }
        intake.testAddCenter(hub);
        Assertions.assertEquals(50,intake.getNumTraineesByCentreType("TrainingHub"));
    }
    @Test
    @DisplayName("Testing number of closed centers and by type")
    void testNumClosedCenters(){
        Intake intake = new Intake();
        TrainingHub hub = new TrainingHub();
        TrainingHub hub2 = new TrainingHub();
        TrainingHub hub3 = new TrainingHub();
        intake.testAddClosedCenter(hub);
        intake.testAddClosedCenter(hub2);
        intake.testAddClosedCenter(hub3);
        Assertions.assertEquals(3,intake.getClosedCentresNum());
        Assertions.assertEquals(3,intake.getClosedCentresNumByType("TrainingHub"));

    }
    @Test
    @DisplayName("Total num of trainees")
    void testTotalNumOfTrainees(){
        Intake intake = new Intake();
        TrainingHub hub = new TrainingHub();
        int i = 0;
        while (i<60){
            hub.addTrainee(new Trainee());
            i++;
        }
        intake.addCentre(hub);
        Assertions.assertEquals(60,intake.numOfTotalTrainees());
    }
    @Test
    @DisplayName("Total num of bootcamps")
    void testTotalNumOfBootCamps(){
        Intake intake = new Intake();
        BootCamp hub = new BootCamp();
        int i = 0;
        while (i<60){
            hub.addTrainee(new Trainee());
            i++;
        }
        intake.addCentre(hub);
        Assertions.assertEquals(1,intake.numOfBootCamps());
    }



}