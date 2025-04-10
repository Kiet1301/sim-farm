import java.util.Scanner;
import items.Apple;
import items.Food;
import items.Item;
import items.Grain;

public class Farm {

    private final Field field;
    private int bank;

    public Farm(int width, int height, int startingFunds) {
        field = new Field(width, height);
        bank = startingFunds;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println(field);
            System.out.println("Bank balance: $" + bank);
            System.out.println("\nEnter your next action:\n");
            System.out.println("  t x y: till");
            System.out.println("  h x y: harvest");
            System.out.println("  p x y: plant");
            System.out.println("  s: field summary");
            System.out.println("  w: wait");
            System.out.println("  q: quit\n");

            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");

            try {
                switch (parts[0]) {
                    case "t":
                        handleTill(parts);
                        break;
                    case "h":
                        handleHarvest(parts);
                        break;
                    case "p":
                        handlePlant(parts, scanner);
                        break;
                    case "s":
                        System.out.println(field.getSummary());
                        break;
                    case "w":
                        // do nothing
                        break;
                    case "q":
                        running = false;
                        continue;
                    default:
                        System.out.println("Invalid command.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            field.tick();
        }

        scanner.close();
    }

    private void handleTill(String[] parts) {
        int x = Integer.parseInt(parts[1]) - 1;
        int y = Integer.parseInt(parts[2]) - 1;
        field.till(x, y);
    }

    private void handleHarvest(String[] parts) {
        int x = Integer.parseInt(parts[1]) - 1;
        int y = Integer.parseInt(parts[2]) - 1;
        Item item = field.get(x, y);
        if (item instanceof Food) {
            int value = item.getValue();
            if (value > 0) {
                bank += value;
                field.till(x, y);
                System.out.println("Harvested for $" + value);
            } else {
                System.out.println("Not ready for harvest.");
            }
        } else {
            System.out.println("Not a harvestable item.");
        }
    }

    private void handlePlant(String[] parts, Scanner scanner) {
        int x = Integer.parseInt(parts[1]) - 1;
        int y = Integer.parseInt(parts[2]) - 1;

        System.out.println("Enter:\n - 'a' to buy an apple for $2\n - 'g' to buy grain for $1");
        String choice = scanner.nextLine().trim();

        if (choice.equals("a")) {
            if (bank >= 2) {
                field.plant(x, y, new Apple());
                bank -= 2;
            } else {
                System.out.println("Not enough funds.");
            }
        } else if (choice.equals("g")) {
            if (bank >= 1) {
                field.plant(x, y, new Grain());
                bank -= 1;
            } else {
                System.out.println("Not enough funds.");
            }
        } else {
            System.out.println("Invalid plant choice.");
        }
    }
}
