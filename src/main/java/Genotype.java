import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Genotype {
    private ArrayList<Integer> genotypeArray;
    private Random generator = new Random ();


    private Genotype () {
        this.genotypeArray = generateNewAnimal();
    }

    private Genotype (Genotype firstParent, Genotype secondParent) {
        this.genotypeArray =  generateNewChild(firstParent, secondParent);
    }

    public ArrayList generateNewChild (Genotype firstParent, Genotype secondParent){
        ArrayList<Integer> childGenotype = new ArrayList<>();
        int firstIndex = 0;
        while (firstIndex == 0){
            firstIndex = generator.nextInt(30);
        }
        int secondIndex = firstIndex;
        while (secondIndex == firstIndex && secondIndex == 0){
            secondIndex = generator.nextInt(30);
        }
        for (int i=0; i<firstIndex; i++) {
            childGenotype.add(firstParent.genotypeArray.get(i));
        }
        for (int i=firstIndex; i<secondIndex; i++) {
            childGenotype.add(secondParent.genotypeArray.get(i));
        }
        for (int i=secondIndex; i<32; i++) {
            childGenotype.add(firstParent.genotypeArray.get(i));
        }

        if (areAllDirections(childGenotype)){
            Collections.sort(childGenotype);
        }
        else{
            childGenotype = allDirection(childGenotype);
        }

        return childGenotype;
    }

    public ArrayList<Integer> generateNewAnimal () {
        ArrayList<Integer> newGenotype = new ArrayList<>();
        for (int i=0; i<8; i++) {
            newGenotype.add(i);
        }
        for (int i=0; i<24; i++){
            newGenotype.add(generator.nextInt(7));
        }
        Collections.sort(newGenotype);
        return newGenotype;
    }

    public int findRotation () {
        return genotypeArray.get(generator.nextInt(31));
    }

    private boolean areAllDirections (ArrayList<Integer> list){
        for (int i=0; i<7; i++){
            if (!list.contains(i))
                return false;
        }
        return true;
    }

    private ArrayList allDirection (ArrayList<Integer> list){
        int [] direction = new int[8];
        for (int i=0; i<32; i++){
            direction[list.get(i)]++;
        }

        for (int i=0; i<8; i++){
            if (direction[i] == 0) {
                int position = generator.nextInt(7);
                while (direction[position]==0 || direction[position]==1) {
                    position = generator.nextInt(7);
                }
                direction[position]--;
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




}
