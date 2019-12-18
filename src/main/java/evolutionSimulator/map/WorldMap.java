package evolutionSimulator.map;

import evolutionSimulator.Generator;
import evolutionSimulator.fields.MapDirection;
import evolutionSimulator.fields.Vector2d;
import evolutionSimulator.objects.Animal;
import evolutionSimulator.objects.IMapElement;
import evolutionSimulator.objects.Plant;

import java.util.*;

public class WorldMap {
    private int height;
    private int width;
    private Map<Vector2d, List<IMapElement>> map;
    private List<Vector2d> freePlaceAtDesert;
    private List<Vector2d> freePlaceAtJungle;
    private Jungle jungle;

    public WorldMap(int width, int height, double percentOfJungle) {
        this.map = new LinkedHashMap<>();
        this.height = height;
        this.width = width;
        this.jungle = new Jungle(width, height, percentOfJungle);
        this.initializeList();
    }

    private void initializeList() {
        this.freePlaceAtJungle = new LinkedList<>();
        this.freePlaceAtDesert = new LinkedList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Vector2d vector = new Vector2d(x, y);
                if (jungle.isInJungle(vector)) {
                    freePlaceAtJungle.add(vector);
                } else {
                    freePlaceAtDesert.add(vector);
                }
            }
        }
    }

    public void addElement(IMapElement element) {
        if (map.containsKey(element.getPosition())) {
            map.get(element.getPosition()).add(element);
        } else {
            List<IMapElement> list = new ArrayList<>();
            list.add(element);
            map.put(element.getPosition(), list);
        }
        freePlaceAtDesert.remove(element.getPosition());
        freePlaceAtJungle.remove(element.getPosition());
    }

    private boolean isOccupied(Vector2d vector) {
        return map.containsKey(vector);
    }

    public void removeElement(IMapElement element) {
        Vector2d position = element.getPosition();
        List<IMapElement> elements = map.get(position);
        elements.remove(element);
        if (elements.isEmpty()) {
            if (jungle.isInJungle(position))
                freePlaceAtJungle.add(position);
            else
                freePlaceAtDesert.add(position);
            map.remove(position);
        }
    }

    public List<Animal> getAnimals() {
        List<Animal> animals = new ArrayList<>();
        for (Vector2d vector2d : map.keySet()) {
            animals.addAll(getAnimalsPerField(vector2d));
        }
        return animals;
    }

    public List<Animal> getAnimalsPerField(Vector2d vector) {
        List<Animal> animals = new ArrayList<>();
        for (IMapElement element : map.get(vector)) {
            if (element instanceof Animal)
                animals.add((Animal) element);
        }
        return animals;
    }

    public List<IMapElement> getElementsPerField(Vector2d vector) {
        if (!this.map.containsKey(vector)) {
            return new LinkedList<>();
        }
        return this.map.get(vector);
    }

    public Vector2d placeForChild(Vector2d vector) {
        List<Vector2d> places = new LinkedList<>();
        for (int i = 0; i <= 7; i++) {
            Vector2d childVector = vector.add(MapDirection.parseToMapDirection(i).toUnitVector());
            childVector = this.placeAtMap(childVector);
            if (!this.isOccupied(childVector)) {
                places.add(childVector);
            }
        }
        if(places.isEmpty())
             return null;
        return places.get(Generator.GENERATOR.nextInt(places.size()));
    }

    public List<Plant> getPlants() {
        List<Plant> plants = new ArrayList<>();
        for (List<IMapElement> elements : map.values()) {
            for (IMapElement element : elements) {
                if (element instanceof Plant)
                    plants.add((Plant) element);
            }
        }
        return plants;
    }

    public Vector2d placeAtMap(Vector2d vector) {
        if (vector.x < width && vector.x >= 0 && vector.y < height && vector.y >= 0)
            return vector;
        int x = (width + vector.x) % width;
        int y = (height + vector.y) % height;
        return new Vector2d(x, y);
    }

    public Set<Vector2d> getOccupatedFields() {
        return map.keySet();
    }

    public List<Vector2d> getFreePlaceAtDesert() {
        return freePlaceAtDesert;
    }

    public List<Vector2d> getFreePlaceAtJungle() {
        return freePlaceAtJungle;
    }

    public Vector2d getDimension() {
        return new Vector2d(this.width, this.height);
    }
}
