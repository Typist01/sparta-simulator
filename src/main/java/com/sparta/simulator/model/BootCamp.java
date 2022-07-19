package com.sparta.simulator.model;

public class BootCamp extends Centre {
	private final int CAPACITY = 500;
	private final int MAX_LOW_ATTENDANCE = 3;
	private int lowAttendanceDuration;

	BootCamp(){
		this.lowAttendanceDuration = 0;
	}

	@Override
	void addTrainee(Trainee trainee) {
		if (!isFull()){
			super.getTraineeList().add(trainee);
		}
	}

	@Override
	boolean isFull() {
		return (super.getTraineeList().size() == CAPACITY);
	}

	boolean checkForLowAttendance(){
		return ++lowAttendanceDuration == MAX_LOW_ATTENDANCE;
	}





}
