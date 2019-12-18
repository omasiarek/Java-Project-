package evolutionSimulator.ui;

import evolutionSimulator.Simulation;

import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        super("");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Simulation simulation = new Simulation();

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
