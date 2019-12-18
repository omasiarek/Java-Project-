package evolutionSimulator;

import evolutionSimulator.ui.Window;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            SimulationConfig config = SimulationConfig.readFromFile("parameters.json");
            new Window(config);
        } catch (IOException error) {
            System.out.println("Failed to load the configuration: " + error.getMessage());
        }
    }
}
