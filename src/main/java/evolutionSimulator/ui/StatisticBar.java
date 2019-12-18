package evolutionSimulator.ui;

import evolutionSimulator.Statistic;

import javax.swing.*;
import java.awt.*;

public class StatisticBar extends JToolBar implements IUpdatable {
    private SimulationController controller;
    private JLabel animalsLabel;
    private JLabel plantsLabel;
    private JLabel avgLifeTimeLabel;
    private JLabel avgCurrentEnergyLabel;
    private JLabel avgChildrenLabel;
    private JLabel genotypeLabel;

    public StatisticBar(SimulationController controller) {
        this.controller = controller;
        controller.addListener(this);
        this.setFloatable(false);

        this.animalsLabel = new JLabel();
        this.plantsLabel = new JLabel();
        this.avgLifeTimeLabel = new JLabel();
        this.avgCurrentEnergyLabel = new JLabel();
        this.avgChildrenLabel = new JLabel();
        this.genotypeLabel = new JLabel();

        this.add(this.animalsLabel);
        this.addSeparator();
        this.add(this.plantsLabel);
        this.addSeparator();
        this.add(this.avgLifeTimeLabel);
        this.addSeparator();
        this.add(this.avgCurrentEnergyLabel);
        this.addSeparator();
        this.add(this.avgChildrenLabel);
        this.addSeparator();
        this.add(this.genotypeLabel);

        this.update();
    }

    public void update() {
        Statistic statistic = this.controller.getSimulation().dailyStatistic();
        this.animalsLabel.setText("Animals: " + statistic.counterAnimals);
        this.plantsLabel.setText("Plants: " + statistic.counterPlants);
        this.avgLifeTimeLabel.setText("Avg. lifetime: " + statistic.avgLifeTime);
        this.avgCurrentEnergyLabel.setText("Avg. energy: " + statistic.avgCurrentEnergy);
        this.avgChildrenLabel.setText("Avg. children: " + statistic.avgChildren);
        this.genotypeLabel.setText("Genotype: " + statistic.genotype);
    }
}
