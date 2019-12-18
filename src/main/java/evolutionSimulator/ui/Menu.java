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


        JButton skip1DayButton = new JButton("1 day");
        skip1DayButton.addActionListener(event -> {
            controller.skip1Day();
        });


        JButton skip100DaysButton = new JButton("100 days");
        skip100DaysButton.addActionListener(event -> {
            controller.skip100Days();
        });

        this.add(start);
        this.add(stop);
        this.add(skip1DayButton);
        this.add(skip100DaysButton);
    }
}
