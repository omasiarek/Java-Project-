package evolutionSimulator.ui;

import javax.swing.*;

public class Menu extends JToolBar {
    public Menu(SimulationController controller) {
        this.setFloatable(false);

        JButton start = new JButton("Start");
        start.addActionListener(event -> {
            controller.start();
        });

        JButton stop = new JButton("Stop");
        stop.addActionListener(event -> {
            controller.stop();
        });


        JButton hundredDays = new JButton("100 days");
        hundredDays.addActionListener(event -> {
            controller.hundredDays();
        });

        this.add(start);
        this.add(stop);
        this.add(hundredDays);
    }
}
