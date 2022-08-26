package uz.jl.trelloapprest.enums;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:58 PM 8/24/22 on Wednesday in August
 */
public enum Visibility {
    WORKSPACE, PRIVATE, PUBLIC;

    public static Visibility findByName(String type) {
        for (Visibility value : values()) {
            if (value.name().contains(type)) {
                return value;
            }
        }
        return WORKSPACE;
    }
}
