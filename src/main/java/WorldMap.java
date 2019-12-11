import java.util.*;

public class WorldMap implements IWorldMap, IPositionChangeObserver{
    int height;
    int width;
    public Map<Vector2d, List<IMapElement>> map;

    public WorldMap(int width, int height) {
        this.map = new LinkedHashMap<>();
        this.height = height;
        this.width = width;
    }


    public void addElement (IMapElement element) {
        if (map.containsKey(element.getPosition())){
            map.get(element.getPosition()).add(element);
        }
        else {
            List<IMapElement> list = new ArrayList<>();
            list.add(element);
            map.put(element.getPosition(), list);
        }
    }

    public boolean isOccupied (Vector2d vector){
        if (map.containsKey(vector))
            return true;
        return false;
    }

    public void removeElement(IMapElement element) {
        List<IMapElement> elements = map.get(element.getPosition());
        elements.remove(element);
    }

    public boolean place(Animal animal) {
        return false;
    }

    public boolean isNewChild (Vector2d vector) {                       //TODO split logic of multiplication
        if (!map.containsKey(vector) || map.get(vector).size() < 2)
            return false;
        List<Animal> elements = new ArrayList<>();
        for (IMapElement element : map.get(vector)) {
            if ((element instanceof Animal)){
                elements.add((Animal) element);
            }
        }
        if (elements.size() < 2)
            return false;
        Collections.sort(elements);
        Animal firstParent = elements.get(0);
        Animal secondParent = elements.get(1);
        Animal child = firstParent.multiplication(secondParent);
        addElement(child);
        return true;
    }

    public List<Animal> getAnimals(){
        List<Animal> animals = new ArrayList<>();
        for (List<IMapElement> elements: map.values()) {
            for (IMapElement element: elements) {
                if (element instanceof Animal)
                    animals.add((Animal) element);
            }
        }
        return animals;
    }

    public Vector2d getDimension() {
        return new Vector2d (this.width, this.height);
    }

    public List<Animal> getAnimalsPerField(Vector2d vector) {
        List<Animal> animals = new ArrayList<>();
        for (IMapElement element: map.get(vector)){
            if (element instanceof Animal)
                animals.add((Animal) element);
        }
        return animals;
    }

    public boolean isPlantAtField (Vector2d vector) {
        for(IMapElement element: map.get(vector)){
            if (element instanceof Plant)
                return true;
        }
        return false;
    }

    public void run(int directions) {
        return;
    }

    public Object objectAt (Vector2d vector) {
        return null;
    }

    public void positionChanged (Vector2d oldPosition, Vector2d newPosition) {}



    public Vector2d placeAtMap(Vector2d vector) {
        if (vector.x < width && vector.x >= 0 && vector.y < height && vector.y >=0)
            return vector;
        int x = vector.x % width;
        int y = vector.y % height;
        return new Vector2d (x, y);
    }

    public boolean canMoveTo(Vector2d vector) {
        return false;
    }


}
