package com.sparta.simulator.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BootCampTest {
	private BootCamp bootCamp;
	@BeforeEach
	void setup(){
		bootCamp = new BootCamp();
	}

	@Test
	void testLowAttendance(){
		BootCamp bootCamp = new BootCamp();

		assertTrue(bootCamp.checkForLowAttendance());
		assertTrue(bootCamp.checkForLowAttendance());
		assertTrue(bootCamp.checkForLowAttendance());
	}

	@Test
	void testAddFull(){
		for (int i = 0; i<500;i++){
			bootCamp.addTrainee(new Trainee());
		}
		assertTrue(bootCamp.isFull());
		bootCamp.addTrainee(new Trainee());
		assertTrue(bootCamp.isFull());
		//assertFalse(bootCamp.addTrainee(new Trainee()));
	}

}