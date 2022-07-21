package com.sparta.simulator.controller;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public static void writeToFile(String input) {
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write(input);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
