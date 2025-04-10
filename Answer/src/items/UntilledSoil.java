package items;

public class UntilledSoil extends Item {
    public UntilledSoil() {
        super(Integer.MAX_VALUE, Integer.MAX_VALUE, -1);
    }

    @Override
    public boolean died() {
        return false;
    }

    @Override
    public String toString() {
        return "/";
    }
}
