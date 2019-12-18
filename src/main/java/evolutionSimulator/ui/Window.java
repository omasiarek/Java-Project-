package evolutionSimulator.ui;

import evolutionSimulator.SimulationConfig;

import javax.swing.*;

public class Window extends JFrame {
    public Window(SimulationConfig config) {
        super("");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane simulationTabs = new JTabbedPane();
        simulationTabs.add("Map 1", new SimulationTab(config));
        simulationTabs.add("Map 2", new SimulationTab(config));

        this.add(simulationTabs);

        this.setVisible(true);
    }
}
