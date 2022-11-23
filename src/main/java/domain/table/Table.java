package domain.order;

public class Table {
    private final int number;

    public Table(final int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    public void hasNumber(final int number){
        return number
    }
}
