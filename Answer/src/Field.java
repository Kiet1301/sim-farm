
import items.Item;
import items.Soil;
import items.UntilledSoil;
import items.Weed;
import items.Apple;
import items.Grain;

import java.util.Random;

public class Field {

	private Item[][] grid;
	private int width, height;
	private Random rand = new Random();

	public Field(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new Item[height][width];
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				grid[y][x] = new Soil();
	}

	public void tick() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Item item = grid[y][x];
				item.tick();

				if (item instanceof Soil && rand.nextInt(100) < 20) {
					grid[y][x] = new Weed();
				} else if (item.died()) {
					grid[y][x] = new UntilledSoil();
				}
			}
		}
	}

	public void till(int x, int y) {
		if (isValid(x, y)) {
			grid[y][x] = new Soil();
		}
	}

	public Item get(int x, int y) {
		if (!isValid(x, y)) return null;
		Item item = grid[y][x];
		try {
			return item.getClass().getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	public void plant(int x, int y, Item item) {
		if (isValid(x, y) && grid[y][x] instanceof Soil) {
			grid[y][x] = item;
		}
	}

	public int getValue() {
		int total = 0;
		for (Item[] row : grid)
			for (Item item : row)
				total += item.getValue();
		return total;
	}

	public String getSummary() {
		int apples = 0, grain = 0, soil = 0, untilled = 0, weeds = 0, totalValue = 0;

		for (Item[] row : grid) {
			for (Item item : row) {
				if (item instanceof Apple) apples++;
				else if (item instanceof Grain) grain++;
				else if (item instanceof Soil) soil++;
				else if (item instanceof UntilledSoil) untilled++;
				else if (item instanceof Weed) weeds++;

				totalValue += item.getValue();
			}
		}

		return String.format(
				"Apples:        %d\nGrain:         %d\nSoil:          %d\nUntilled:      %d\nWeed:          %d\n\nFor a total of $%d\n\nTotal apples created: %d\nTotal grain created: %d\n",
				apples, grain, soil, untilled, weeds, totalValue,
				Apple.getGenerationCount(), Grain.getGenerationCount()
		);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("  ");
		for (int x = 1; x <= width; x++) sb.append(x).append(" ");
		sb.append("\n");

		for (int y = 0; y < height; y++) {
			sb.append(y + 1).append(" ");
			for (int x = 0; x < width; x++) {
				sb.append(grid[y][x].toString()).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private boolean isValid(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}
}
