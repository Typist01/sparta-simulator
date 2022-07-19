package com.sparta.simulator.model;

public class BootCamp extends Centre {
	private final int CAPACITY = 500;
	private final int MAX_LOW_ATTENDANCE_DURATION = 3;
	private int lowAttendanceDuration;

	BootCamp() {
		this.lowAttendanceDuration = 0;
	}

	@Override
	void addTrainee(Trainee trainee) {
		if (!isFull()) {
			super.getTraineeList().add(trainee);
		}
	}

	@Override
	boolean isFull() {
		return (super.getTraineeList().size() == CAPACITY);
	}


	@Override
	public boolean isClosable() {
		if (super.getTraineeList().size() < 25 && lowAttendanceDuration < MAX_LOW_ATTENDANCE_DURATION) {
			//Increment for consecutive months with less than 25
			lowAttendanceDuration++;
			return false;

		} else if (MAX_LOW_ATTENDANCE_DURATION == lowAttendanceDuration) {
			//Has reached 3 consecutive months.
			return true;

		} else {
			//Reset since it's not reached 3 consecutive months.
			lowAttendanceDuration = 0;
			return false;
		}

	}

}
