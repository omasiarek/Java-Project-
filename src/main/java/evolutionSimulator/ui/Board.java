package evolutionSimulator.ui;

import evolutionSimulator.fields.Vector2d;
import evolutionSimulator.map.WorldMap;
import evolutionSimulator.objects.Animal;
import evolutionSimulator.objects.IMapElement;
import evolutionSimulator.objects.Plant;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Board extends JPanel {
    WorldMap map;
    Map<Vector2d, JLabel> labelByPosition = new LinkedHashMap<>();

    public Board(WorldMap map) {
        this.map = map;
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
        for (Map.Entry<Vector2d, JLabel> positionWithLabel : labelByPosition.entrySet()) {
            Vector2d position = positionWithLabel.getKey();
            JLabel label = positionWithLabel.getValue();

            List<IMapElement> elements = this.map.getElementsPerField(position);

            label.setBackground(null);
            if (elements.size() == 1) {
                IMapElement element = elements.get(0);
                if (element instanceof Plant) {
                    label.setBackground(Color.green);
                    label.setOpaque(true);
                } else if (element instanceof Animal) {
                    label.setBackground(Color.orange);
                    label.setOpaque(true);
                }
            } else if (elements.size() > 1) {
                label.setBackground(Color.red);
                label.setOpaque(true);
            }
        }
    }
}
