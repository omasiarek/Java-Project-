package evolutionSimulator.objects;

import evolutionSimulator.Generator;

import java.util.*;
import java.util.stream.Collectors;

public class Genotype {
    private ArrayList<Integer> genotypeArray;


    private Genotype (ArrayList<Integer> list) {
        this.genotypeArray = list;
    }

    public Genotype generateNewGenotype (Genotype otherParent){
        ArrayList<Integer> childGenotype = new ArrayList<>();
        int firstIndex = 1 + Generator.GENERATOR.nextInt(30);
        int secondIndex = firstIndex + 1 + Generator.GENERATOR.nextInt(31-firstIndex);

        for (int i=0; i<firstIndex; i++) {
            childGenotype.add(this.genotypeArray.get(i));
        }
        for (int i=firstIndex; i<secondIndex; i++) {
            childGenotype.add(otherParent.genotypeArray.get(i));
        }
        for (int i=secondIndex; i<32; i++) {
            childGenotype.add(this.genotypeArray.get(i));
        }

        if (areAllDirections(childGenotype)){
            Collections.sort(childGenotype);
        }
        else{
            childGenotype = allDirections(childGenotype);
        }

        return new Genotype(childGenotype);
    }

    public static Genotype generateNewGenotype () {
        ArrayList<Integer> newGenotype = new ArrayList<>();
        for (int i=0; i<8; i++) {
            newGenotype.add(i);
        }
        for (int i=0; i<24; i++){
            newGenotype.add(Generator.GENERATOR.nextInt(8));
        }
        Collections.sort(newGenotype);

        return new Genotype(newGenotype);

    }

    public int findRotation () {
        return genotypeArray.get(Generator.GENERATOR.nextInt(32));
    }

    private boolean areAllDirections (ArrayList<Integer> list){
        for (int i=0; i<=7; i++){
            if (!list.contains(i))
                return false;
        }
        return true;
    }

    private ArrayList allDirections(ArrayList<Integer> list){
        int [] direction = new int[8];
        for (int i=0; i<32; i++){
            direction[list.get(i)]++;
        }

        for (int i=0; i<8; i++){
            if (direction[i] == 0) {
                List<Integer> currentDirections = new LinkedList<>();
                for (int j=0; j<8; j++){
                    if (direction[j]>1)
                        currentDirections.add(j);
                }
                int position = Generator.GENERATOR.nextInt(currentDirections.size());
                direction[currentDirections.get(position)]--;
                direction[i]++;
            }
        }
        ArrayList<Integer> newDirections = new ArrayList<>();
        for (int i=0; i<8; i++){
            for (int j=0; j<direction[i]; j++)
                newDirections.add(i);
        }
        return newDirections;
    }

    @Override
    public String toString() {
        return genotypeArray.stream()
                .map(gen -> gen.toString())
                .collect(Collectors.joining(""));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genotype genotype = (Genotype) o;
        return Objects.equals(genotypeArray, genotype.genotypeArray);
    }
}
