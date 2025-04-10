package items;

public class Grain extends Food {

    private static int generationCount = 0;

    public Grain() {
        super(2, 6, 2);
        generationCount++;
    }

    public static int getGenerationCount() {
        return generationCount;
    }

    @Override
    public String toString() {
        return age < maturationAge ? "g" : "G";
    }
}
