package com.sparta.simulator.model;

import com.sparta.simulator.model.centres.BootCamp;
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
		assertFalse(bootCamp.isClosable());
		assertFalse(bootCamp.isClosable());
		assertTrue(bootCamp.isClosable());
	}

	@Test
	void testAddFull(){
		for (int i = 0; i<500;i++){
			assertTrue(bootCamp.addTrainee(new Trainee()));
		}
		assertTrue(bootCamp.isFull());
		assertFalse(bootCamp.addTrainee(new Trainee()));
		assertTrue(bootCamp.isFull());
	}

}