package evolutionSimulator.ui;

import evolutionSimulator.Simulation;
import evolutionSimulator.SimulationConfig;
import evolutionSimulator.map.WorldMap;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;


public class SimulationController {
    private Simulation simulation;
    private Timer timer;
    private List<IUpdatable> listeners = new LinkedList<>();


    public SimulationController(SimulationConfig config) {
        this.simulation = new Simulation(config);
        this.timer = new Timer(20, event -> {
            simulation.oneDay();
            this.updateListeners();
        });
    }

    public WorldMap getMap() {
        return this.simulation.getMap();
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

    public void hundredDays() {
        for (int i = 0; i < 100; i++) {
            simulation.oneDay();
        }
        this.updateListeners();
    }
}
