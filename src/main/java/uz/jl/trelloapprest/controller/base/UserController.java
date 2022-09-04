package uz.jl.trelloapprest.controller.base;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jl.trelloapprest.domains.auth.AuthUser;
import uz.jl.trelloapprest.response.ApiResponse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/11:03 (Friday)
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ApiResponse<List<AuthUser>> getAll() {
        return new ApiResponse<>(List.of(AuthUser.builder()
                .username("John")
                .password("123")
                .lastLoginTime(LocalDateTime.now())
                .email("john.lgd65@gmail.com")
                .build()), 2);
    }
}
