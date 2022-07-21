package com.sparta.simulator.model;

public enum CourseType {
	JAVA("Java"),CSHARP("C#"),BUSINESS("Business"),DATA("Data"),DEVOPS("DevOps");
	private final String courseName;

	CourseType(String courseName){
		this.courseName = courseName;

	}

	public String getCourseName() {
		return courseName;
	}

}
