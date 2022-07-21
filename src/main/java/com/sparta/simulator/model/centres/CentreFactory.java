package com.sparta.simulator.model.centres;

import com.sparta.simulator.model.GenerateCentreException;

public class CentreFactory {
	public static Centre createCentre(CentreType centreType) throws GenerateCentreException {
		switch (centreType) {
			case BOOTCAMP -> {
				return new BootCamp();
			}
			case TECH_CENTRE -> {
				return new TechCenter();
			}
			case TRAINING_HUB -> {
				return new TrainingHub();
			}
			default -> throw new GenerateCentreException("Error while creating centre.");

		}
	}
}
