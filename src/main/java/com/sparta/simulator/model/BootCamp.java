package com.sparta.simulator.model;

public class BootCamp extends Centre {
	private final int CAPACITY = 500;
	@Override
	void addTrainee() {
		if (!isFull()){
			super.getTraineeList().add()
		}

	}

	@Override
	boolean isFull() {
		return super.getTraineeList() <=CAPACITY;
	}



}
