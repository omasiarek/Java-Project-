package evolutionSimulator.ui;

import evolutionSimulator.Simulation;
import evolutionSimulator.SimulationConfig;

import javax.swing.*;

public class Window extends JFrame {
    public Window(SimulationConfig config) {
        super("");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Simulation simulation = new Simulation(config);

        Board board = new Board(simulation.getMap());

        Timer timer = new Timer(20, event -> {
            simulation.oneDay();
            board.update();
        });
        timer.start();

        this.add(board);

        this.setVisible(true);
    }
}
