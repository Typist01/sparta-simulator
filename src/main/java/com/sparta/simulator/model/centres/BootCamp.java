package com.sparta.simulator.model.centres;

public class BootCamp extends Centre {
	private final int MAX_LOW_ATTENDANCE_DURATION = 3;
	private int lowAttendanceDuration;

	public BootCamp() {
		super(500);
		this.lowAttendanceDuration = 0;
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
