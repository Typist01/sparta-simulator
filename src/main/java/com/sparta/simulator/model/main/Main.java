package com.sparta.simulator.model.main;

import com.sparta.simulator.controller.ProjectManager;
import com.sparta.simulator.model.Trainee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static Logger logger = LogManager.getLogger("SpartaLogger");
    public static void main(String[] args) {
        ProjectManager projectManager=new ProjectManager();
        projectManager.run();
    }
}