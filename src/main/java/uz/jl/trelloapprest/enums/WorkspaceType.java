package uz.jl.trelloapprest.enums;

import java.util.Arrays;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:39 PM 8/24/22 on Wednesday in August
 */
public enum WorkspaceType {
    MARKETING, SALES_CRM, ENGINEERING_IT, OPERATIONS, EDUCATION, SMALL_BUSINESS;

    public static WorkspaceType findByName(String type) {
        for (WorkspaceType value : values()) {
            if (value.name().contains(type)) {
                return value;
            }
        }
        return MARKETING;
    }
}
