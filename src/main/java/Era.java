import java.util.Collections;
import java.util.List;

import static java.lang.Math.floor;


public class Era {
    private WorldMap map;

    public Era(WorldMap map) {
        this.map = map;
    }

    public void deleteDeathAnimals() {
        List<Animal> animals = map.getAnimals();
        for (Animal animal : animals) {
            if (!animal.isAlive())
                map.removeElement(animal);
        }
    }

    public void movingAnimal() {
        List<Animal> animals = map.getAnimals();
        for (Animal animal : animals) {
            animal.move();
        }
    }

    public void eating() {
        List<Plant> plants = map.getPlants();
        for (Plant plant : plants) {
            List<Animal> animals = map.getAnimalsPerField(plant.getPosition());
            Collections.sort(animals);
            if (animals.size() != 0) {
                int i = 1;
                while (i<animals.size() && animals.get(i).getEnergy() == animals.get(0).getEnergy()) {
                    i++;
                }
                int energy = Plant.ENERGY / i;
                for (int j = 0; j < i; j++) {
                    animals.get(j).addEnergy(energy);
                }
            }
        }
    }

    public void multiplication() {
        for (Vector2d vector : map.getOccupatedFields()) {
            Vector2d childVector = map.placeForChild(vector);
            if (childVector == null)
                return;
            List<Animal> animals = map.getAnimalsPerField(vector);
            if (animals.size() < 2)
                return;
            Collections.sort(animals);
            Animal firstParent = animals.get(0);
            Animal secondParent = animals.get(1);
            if(firstParent.isCorrectEnergyToMultiplication() && secondParent.isCorrectEnergyToMultiplication()){
                Animal child = firstParent.multiplication(secondParent, childVector);
                map.addElement(child);
            }
        }
    }

    public void putPlants() {
        List<Vector2d> desert = map.getFreePlaceAtDesert();
        List<Vector2d> jungle = map.getFreePlaceAtJungle();
        if (desert.size() > 0) {
            int index = Generator.GENERATOR.nextInt(desert.size());
            Plant plant = new Plant(desert.get(index));
            map.addElement(plant);
        }
        if (jungle.size() > 0) {
            int index = Generator.GENERATOR.nextInt(jungle.size());
            Plant plant = new Plant(jungle.get(index));
            map.addElement(plant);
        }
    }
}
