package com.sparta.simulator.model;

public class BootCamp extends Centre implements CentreClosable{
	private final int CAPACITY = 500;
	private final int MAX_LOW_ATTENDANCE_DURATION = 3;
	private final int MAX_LOW_ATTENDANCE_TRAINEES = 25;
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
	@Override
	public boolean checkLowAttendance(){
		if (super.getTraineeList().size() <= MAX_LOW_ATTENDANCE_TRAINEES || MAX_LOW_ATTENDANCE_DURATION != lowAttendanceDuration) {
			if (lowAttendanceDuration < MAX_LOW_ATTENDANCE_DURATION) {
				lowAttendanceDuration++;
				return true;
			}
		}
		else {
			lowAttendanceDuration = 0;
			return false;
		}
		return false;
	}


	@Override
	public boolean isClosed() {
		return MAX_LOW_ATTENDANCE_DURATION == lowAttendanceDuration;
	}

}
