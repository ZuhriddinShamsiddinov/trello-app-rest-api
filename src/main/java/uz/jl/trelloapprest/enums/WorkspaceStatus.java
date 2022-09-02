package uz.jl.trelloapprest.enums;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 4:26 PM 8/24/22 on Wednesday in August
 */
public enum WorkspaceStatus {
    FREE, PREMIUM;

    public static WorkspaceStatus findByName(String status) {
        for (WorkspaceStatus stats : values()) {
            if (stats.name().contains(status) || stats.name().equalsIgnoreCase(status))
                return stats;
        }
        return FREE;
    }
}
