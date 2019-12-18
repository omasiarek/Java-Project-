package evolutionSimulator.ui;

import evolutionSimulator.SimulationConfig;

import javax.swing.*;
import java.awt.*;

public class SimulationTab extends JPanel {
    public SimulationTab(SimulationConfig config) {
        SimulationController simulationController = new SimulationController(config);

        Menu menu = new Menu(simulationController);
        StatisticBar statisticBar = new StatisticBar(simulationController);
        Board board = new Board(simulationController);

        this.setLayout(new BorderLayout());
        this.add(menu, BorderLayout.PAGE_START);
        this.add(board);
        this.add(statisticBar, BorderLayout.PAGE_END);
    }
}
