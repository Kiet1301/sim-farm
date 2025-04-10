package items;

public abstract class Item {

    protected int age;
    protected int maturationAge;
    protected int deathAge;
    protected int value;

    public Item(int maturationAge, int deathAge, int value) {
        this.age = 0;
        this.maturationAge = maturationAge;
        this.deathAge = deathAge;
        this.value = value;
    }

    public void tick() {
        age++;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean died() {
        return age > deathAge;
    }

    public int getValue() {
        return age >= maturationAge ? value : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Item)) return false;
        Item other = (Item) obj;
        return this.age == other.age &&
                this.maturationAge == other.maturationAge &&
                this.deathAge == other.deathAge &&
                this.value == other.value;
    }

    public abstract String toString();
}
