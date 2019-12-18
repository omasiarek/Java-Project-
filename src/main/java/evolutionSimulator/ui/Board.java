package evolutionSimulator.ui;

import evolutionSimulator.fields.Vector2d;
import evolutionSimulator.map.WorldMap;
import evolutionSimulator.objects.Animal;
import evolutionSimulator.objects.Genotype;
import evolutionSimulator.objects.IMapElement;
import evolutionSimulator.objects.Plant;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Board extends JPanel implements IUpdatable {
    SimulationController controller;
    WorldMap map;
    Map<Vector2d, JLabel> labelByPosition = new LinkedHashMap<>();

    public Board(SimulationController controller) {
        this.controller = controller;
        controller.addListener(this);
        this.map = controller.getMap();
        Vector2d dimension = map.getDimension();
        this.setLayout(new GridLayout(dimension.y, dimension.x));
        for (int y = 0; y < dimension.y; y++) {
            for (int x = 0; x < dimension.x; x++) {
                Vector2d position = new Vector2d(x, y);
                JLabel label = new JLabel("");
                this.labelByPosition.put(position, label);
                this.add(label);
            }
        }

        this.update();
    }

    public void update() {
        Genotype dominantGenotype = this.controller.getSimulation().dailyStatistic().genotype;
        for (Map.Entry<Vector2d, JLabel> positionWithLabel : labelByPosition.entrySet()) {
            Vector2d position = positionWithLabel.getKey();
            JLabel label = positionWithLabel.getValue();

            List<IMapElement> elements = this.map.getElementsPerField(position);
            label.setBackground(this.colorForElements(elements, dominantGenotype));
            label.setOpaque(true);
            label.setToolTipText(this.labelForElements(elements));

        }
    }

    private Color colorForElements(List<IMapElement> elements, Genotype dominantGenotype) {
        if (elements.size() == 1) {
            IMapElement element = elements.get(0);
            if (element instanceof Plant) {
                return Color.green;
            } else if (element instanceof Animal) {
                Animal animal = (Animal) element;
                if (animal.getGenotype().equals(dominantGenotype)) {
                    return Color.blue;
                }
                return Color.orange;
            }
        } else if (elements.size() > 1) {
            return Color.red;
        }
        return null;
    }

    private String labelForElements(List<IMapElement> elements) {
        if (elements.size() == 1) {
            IMapElement element = elements.get(0);
            if (element instanceof Animal) {
                Animal animal = (Animal) element;
                return String.format(
                        "<html>Animal<br>Energy: %d<br>Genotype: %s<br>Children: %d</html>",
                        animal.getEnergy(),
                        animal.getGenotype(),
                        animal.getChildren()
                );
            }
        } else if (elements.size() > 1) {
            return String.format("%d elements", elements.size());
        }
        return null;
    }
}
