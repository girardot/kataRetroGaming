package trivia;

public enum Category {

    POP("Pop"), SCIENCE("Science"), ROCK("Rock"), SPORTS("Sports");

    private final String label;

    private Category(String label) {
        this.label = label;
    }

    public String toString() {
        return this.label;
    }

}
