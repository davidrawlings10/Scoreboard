package scoreboard;

public enum League {
    AVES("Aves"), NHL("NHL"), TEST("Test"), NFL("NFL"), NBA("NBA"), NCAA("NCAA");

    public final String label; // This label isn't being used. I'm handling the translation (ex: AVES -> Aves) on the front-end

    private League(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
