package scoreboard;

public enum Sport {
    HOCKEY(1), SOCCER(2);

    private final int value;

    Sport(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
