package items;

public class Apple extends Food {
    private static int generationCount = 0;

    public Apple() {
        super(3, 5, 3);
        generationCount++;
    }

    public static int getGenerationCount() {
        return generationCount;
    }

    @Override
    public String toString() {
        return age < maturationAge ? "a" : "A";
    }
}
