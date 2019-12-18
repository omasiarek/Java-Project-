package evolutionSimulator.ui;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

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

        JButton exportButton = new JButton("Export");
        exportButton.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select destination file");
            int choice = fileChooser.showSaveDialog(this);
            if (choice == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    controller.exportToFile(file);
                } catch (IOException error) {
                    JOptionPane.showMessageDialog(this, "Something went wrong: " + error.getMessage());
                }
            }
        });

        this.add(start);
        this.add(stop);
        this.add(skip1DayButton);
        this.add(skip100DaysButton);
        this.add(exportButton);
    }
}
