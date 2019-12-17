import java.util.*;

public class WorldMap {
    private int height;
    private int width;
    private Map<Vector2d, List<IMapElement>> map;
    private static OptionsParser PARSER = new OptionsParser();
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
        List<IMapElement> elements = map.get(element.getPosition());
        elements.remove(element);
        if (elements.isEmpty()) {
            if (jungle.isInJungle(element.getPosition()))
                freePlaceAtJungle.add(element.getPosition());
            else
                freePlaceAtDesert.add(element.getPosition());
            map.remove(element.getPosition());
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

    public Vector2d placeForChild(Vector2d vector) {
        for (int i = 0; i <= 7; i++) {
            Vector2d childVector = vector.add(PARSER.parseToMapDirection(i).toUnitVector());
            childVector = this.placeAtMap(childVector);
            if (!this.isOccupied(childVector)) {
                return childVector;
            }
        }
        return null;
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
        int x = vector.x % width;
        int y = vector.y % height;
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
}
