package springEx.springEx.domain;

public enum Level {
    BASIC(0), SLIVER(1), GOLD(2);
    private final Integer type;

    Level(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static Level getLevel(Integer type) {
        switch(type) {
            case 0:
                return BASIC;
            case 1:
                return SLIVER;
            case 2:
                return GOLD;
            default:
                throw new IllegalArgumentException("type Exception : " + type);
        }
    }
}
