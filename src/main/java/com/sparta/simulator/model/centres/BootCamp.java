package com.sparta.simulator.model.centres;

// Class for creating centres of type bootcamp.
public class BootCamp extends Centre {
	private final int MAX_LOW_ATTENDANCE_DURATION = 3;
	private int lowAttendanceDuration;

	public BootCamp() {
		super(500);
		this.lowAttendanceDuration = 0;
	}

	// checks to see if bootcamp is ready to close.
	@Override
	public boolean isClosable() {
		// Increment low attendance duration
		if (super.getTraineeList().size() < 25 && lowAttendanceDuration < MAX_LOW_ATTENDANCE_DURATION) {
			//Increment for consecutive months with less than 25
			lowAttendanceDuration++;
			return false;
		// returns true when closing condition met
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
