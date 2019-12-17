import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.floor;


public class Animal implements IMapElement, Comparable<Animal>{
    private MapDirection direction;
    private Vector2d position;
    private WorldMap map;
    private List<IPositionChangeObserver> observers;
    private int energy;
    private int energyToMultiplication;
    private int energyToMove;
    private Genotype genotype;
    private static Random GENERATOR = new Random();
    private static OptionsParser PARSER = new OptionsParser();



    public Animal(WorldMap map, Vector2d position, int energyToMultiplication){
        direction = new OptionsParser().parseToMapDirection(GENERATOR.nextInt(8));
        this.position = position;
        this.map = map;
        this.observers = new LinkedList<>();
        this.energy = 0;
        this.energyToMultiplication = energyToMultiplication;
        this.genotype=Genotype.generateNewGenotype();
    }

    public Animal (WorldMap map, Vector2d position, int energyToMultiplication, int energy, Genotype firstParent, Genotype secondParent){
        this(map, position, energyToMultiplication);
        this.energy = energy;
        this.genotype = firstParent.generateNewGenotype(secondParent);
    }


    public void move(){
        int direction = this.genotype.findRotation();
        int currentDirection = PARSER.parseToInt(this.direction);
        currentDirection += direction;
        currentDirection %= 8;
        this.direction = PARSER.parseToMapDirection(currentDirection);
        map.removeElement(this);
        this.position = map.placeAtMap(this.position.add(this.direction.toUnitVector()));
        map.addElement(this);
        this.energy -= energyToMove;
    }


    public int quaterEnergy (int energy){
        double energyDouble = (double) energy;
        energy = (int)floor(energyDouble/4);
        return energy;
    }

    public Animal multiplication (Animal otherAnimal, Vector2d childVector) {
        int childEnergy = quaterEnergy(this.energy) + quaterEnergy(otherAnimal.energy);
        this.energy -= quaterEnergy(this.energy);
        otherAnimal.energy -= quaterEnergy(otherAnimal.energy);
        return new Animal (this.map, childVector, this.energyToMultiplication, childEnergy, this.genotype, otherAnimal.genotype);

    }

    public boolean isCorrectEnergyToMultiplication () {
        return this.energy >= this.energyToMultiplication;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public void addObserver (IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver (IPositionChangeObserver observer){
        observers.remove(observer);
    }

    public void notify (Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer: observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    public void addEnergy (int energy){
        this.energy += energy;
    }

    public void subtractEnergy (int energy){
        this.energy -= energy;
    }

    public boolean isAlive () {
        return this.energy > 0;
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    public int compareTo(Animal otherAnimal) {
        return (-1) * Integer.compare(this.energy, otherAnimal.energy);
    }
}

