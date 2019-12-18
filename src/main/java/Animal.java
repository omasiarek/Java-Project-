import java.util.Random;


public class Animal implements IMapElement, Comparable<Animal> {
    private MapDirection direction;
    private Vector2d position;
    private WorldMap map;
    private int energy;
    private static int ENERGYTOMULTIPLICATION = 10;
    private static int ENERGYTOMOVE = 10;
    private Genotype genotype;
    private static OptionsParser PARSER = new OptionsParser();


    public Animal(WorldMap map, Vector2d position) {
        direction = new OptionsParser().parseToMapDirection(Generator.GENERATOR.nextInt(8));
        this.position = position;
        this.map = map;
        this.energy = 0;
        this.genotype = Genotype.generateNewGenotype();
    }

    public Animal(WorldMap map, Vector2d position, int energy, Genotype firstParent, Genotype secondParent) {
        this(map, position);
        this.energy = energy;
        this.genotype = firstParent.generateNewGenotype(secondParent);
    }


    public void move() {
        int direction = this.genotype.findRotation();
        int currentDirection = PARSER.parseToInt(this.direction);
        currentDirection += direction;
        currentDirection %= 8;
        this.direction = PARSER.parseToMapDirection(currentDirection);
        map.removeElement(this);
        this.position = map.placeAtMap(this.position.add(this.direction.toUnitVector()));
        map.addElement(this);
        this.energy -= Animal.ENERGYTOMOVE;
    }


    public int quaterEnergy(int energy) {
        return energy / 4;
    }

    public Animal multiplication(Animal otherAnimal, Vector2d childVector) {
        int childEnergy = quaterEnergy(this.energy) + quaterEnergy(otherAnimal.energy);
        this.energy -= quaterEnergy(this.energy);
        otherAnimal.energy -= quaterEnergy(otherAnimal.energy);
        return new Animal(this.map, childVector, childEnergy, this.genotype, otherAnimal.genotype);

    }

    public boolean isCorrectEnergyToMultiplication() {
        return this.energy >= Animal.ENERGYTOMULTIPLICATION;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }


    public void addEnergy(int energy) {
        this.energy += energy;
    }


    public boolean isAlive() {
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

