package springEx.springEx.domain;

public enum Level {
    GOLD(2, null), SILVER(1, GOLD), BASIC(0, SILVER);
    private final Integer type;

    private final Level next;

    Level(Integer type, Level next) {
        this.type = type;
        this.next = next;
    }

    public Level nextLevel() {
        return next;
    }

    public Integer getType() {
        return type;
    }

    public static Level getLevel(Integer type) {
        switch(type) {
            case 0:
                return BASIC;
            case 1:
                return SILVER;
            case 2:
                return GOLD;
            default:
                throw new IllegalArgumentException("type Exception : " + type);
        }
    }
}
