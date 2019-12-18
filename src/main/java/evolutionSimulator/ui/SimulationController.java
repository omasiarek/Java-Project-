package evolutionSimulator.ui;

import evolutionSimulator.Simulation;
import evolutionSimulator.SimulationConfig;
import evolutionSimulator.map.WorldMap;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;


public class SimulationController {
    private Simulation simulation;
    private Timer timer;
    private List<IUpdatable> listeners = new LinkedList<>();


    public SimulationController(SimulationConfig config) {
        this.simulation = new Simulation(config);
        this.timer = new Timer(20, event -> this.skip1Day());
    }

    public WorldMap getMap() {
        return this.simulation.getMap();
    }

    public Simulation getSimulation() {
        return this.simulation;
    }

    public void addListener(IUpdatable listener) {
        this.listeners.add(listener);
    }

    private void updateListeners() {
        for (IUpdatable listener : listeners) {
            listener.update();
        }
    }

    public void start() {
        this.timer.start();
    }


    public void stop() {
        this.timer.stop();
    }

    public void skip1Day() {
        simulation.oneDay();
        this.updateListeners();
    }

    public void skip100Days() {
        for (int i = 0; i < 100; i++) {
            simulation.oneDay();
        }
        this.updateListeners();
    }

    public void exportToFile(File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            writer.write(this.simulation.exportStatistics());
        }
    }
}
