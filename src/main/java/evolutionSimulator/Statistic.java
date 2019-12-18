package evolutionSimulator;

import evolutionSimulator.objects.Genotype;

public class Statistic {
    public final int counterAnimals;
    public final int counterPlants;
    public final Genotype genotype;
    public final int avgLifeTime;
    public final int avgCurrentEnergy;
    public final int avgChildren;

    public Statistic(int counterAnimals, int counterPlants, Genotype genotype, int avgLifeTime, int avgCurrentEnergy, int avgChildren) {
        this.counterAnimals = counterAnimals;
        this.counterPlants = counterPlants;
        this.genotype = genotype;
        this.avgLifeTime = avgLifeTime;
        this.avgCurrentEnergy = avgCurrentEnergy;
        this.avgChildren = avgChildren;
    }

}
