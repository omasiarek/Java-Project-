

public class Animal implements IMapElement, Comparable<Animal> {
    private MapDirection direction;
    private Vector2d position;
    private WorldMap map;
    private int energy;
    private static int ENERGYTOMULTIPLICATION = 10;
    private static int ENERGYTOMOVE = 10;
    private Genotype genotype;
    private static OptionsParser PARSER = new OptionsParser();


    public Animal(WorldMap map, Vector2d position, int energy) {
        direction = new OptionsParser().parseToMapDirection(Generator.GENERATOR.nextInt(8));
        this.position = position;
        this.map = map;
        this.energy = energy;
        this.genotype = Genotype.generateNewGenotype();
    }

    public Animal(WorldMap map, Vector2d position, int energy, Genotype genotype) {
        this(map, position, energy);
        this.genotype = genotype;
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


    public int quaterEnergy() {
        return this.energy / 4;
    }

    public Animal multiplication(Animal otherAnimal, Vector2d childVector) {
        int childEnergy = quaterEnergy() + otherAnimal.quaterEnergy();
        this.energy -= quaterEnergy();
        otherAnimal.energy -= otherAnimal.quaterEnergy();
        Genotype genotype = this.genotype.generateNewGenotype(otherAnimal.genotype);
        return new Animal(this.map, childVector, childEnergy, genotype);

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

