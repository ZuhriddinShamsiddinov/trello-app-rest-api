package uz.jl.trelloapprest.projection;

import java.util.List;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:05 AM 8/26/22 on Friday in August
 */
public interface BoardViewProjection {
    Long getId();

    String getName();

    String getBackground();


    String getVisibility();


    List<AuthUserViewProjection> getUsers();

    interface AuthUserViewProjection {
        String getUsername();


        String getEmail();
    }

}
