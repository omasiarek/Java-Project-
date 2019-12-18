package evolutionSimulator;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimulationConfig {
    private static Gson gson = new Gson();

    public final int width;
    public final int height;
    public final int startEnergy;
    public final int moveEnergy;
    public final int plantEnergy;
    public final double jungleRatio;
    public final int initialAnimals;

    public SimulationConfig(int width, int height, int startEnergy, int moveEnergy, int plantEnergy, double jungleRatio, int initialAnimals) {
        this.width = width;
        this.height = height;
        this.startEnergy = startEnergy;
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
        this.jungleRatio = jungleRatio;
        this.initialAnimals = initialAnimals;
    }

    public static SimulationConfig readFromFile(String path) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            return SimulationConfig.gson.fromJson(bufferedReader, SimulationConfig.class);
        }
    }
}
