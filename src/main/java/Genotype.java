import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Genotype {
    private ArrayList<Integer> genotypeArray;
    private static Random generator = new Random ();


    private Genotype (ArrayList<Integer> list) {
        this.genotypeArray = list;
    }

    public Genotype generateNewChild (Genotype otherParent){
        ArrayList<Integer> childGenotype = new ArrayList<>();
        int firstIndex = 1 + generator.nextInt(30);
        int secondIndex = firstIndex + 1 + generator.nextInt(31-firstIndex);

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
            childGenotype = allDirection(childGenotype);
        }

        return new Genotype(childGenotype);
    }

    public static void generateNewGenotype () {
        ArrayList<Integer> newGenotype = new ArrayList<>();
        for (int i=0; i<8; i++) {
            newGenotype.add(i);
        }
        for (int i=0; i<24; i++){
            newGenotype.add(generator.nextInt(8));
        }
        Collections.sort(newGenotype);

        new Genotype(newGenotype);

    }

    public int findRotation () {
        return genotypeArray.get(generator.nextInt(32);
    }

    private boolean areAllDirections (ArrayList<Integer> list){
        for (int i=0; i<=7; i++){
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
                int position = generator.nextInt(8);
                while (direction[position]==0 || direction[position]==1) {
                    position = generator.nextInt(8);
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
