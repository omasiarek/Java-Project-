package evolutionSimulator.ui;

import evolutionSimulator.Simulation;
import evolutionSimulator.SimulationConfig;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window(SimulationConfig config) {
        super("");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        SimulationController simulationController = new SimulationController(config);

        Menu menu = new Menu(simulationController);
        Board board = new Board(simulationController);

        this.add(menu, BorderLayout.PAGE_START);
        this.add(board);

        this.setVisible(true);
    }
}
