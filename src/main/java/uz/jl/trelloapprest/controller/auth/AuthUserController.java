package uz.jl.trelloapprest.controller.auth;

import org.springframework.web.bind.annotation.*;
import uz.jl.trelloapprest.controller.base.ApiController;
import uz.jl.trelloapprest.domains.auth.AuthUser;
import uz.jl.trelloapprest.dtos.token.JwtResponse;
import uz.jl.trelloapprest.dtos.token.LoginRequest;
import uz.jl.trelloapprest.dtos.token.RefreshTokenRequest;
import uz.jl.trelloapprest.dtos.UserRegisterDTO;
import uz.jl.trelloapprest.response.ApiResponse;
import uz.jl.trelloapprest.services.auth.AuthUserService;

import javax.validation.Valid;


/**
 * @author "Zuhriddin Shamsiddinov"
 * @since 19/08/22/10:50 (Friday)
 */

@RestController
public class AuthUserController extends ApiController<AuthUserService> {
    protected AuthUserController(AuthUserService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/auth/login", produces = "application/json")
    public ApiResponse<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        return new ApiResponse<>(service.login(loginRequest));
    }

    @GetMapping(value = PATH + "/auth/refresh", produces = "application/json")
    public ApiResponse<JwtResponse> login(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return new ApiResponse<>(service.refreshToken(refreshTokenRequest));
    }

    @PostMapping(PATH + "/auth/register")
    public ApiResponse<AuthUser> register(@Valid @RequestBody UserRegisterDTO dto) {
        return new ApiResponse<>(service.register(dto));
    }

    @GetMapping(PATH + "/auth/activate")
    public ApiResponse<Boolean> register(@RequestParam(name = "activation_code") String activationCode) {
        return new ApiResponse<>(service.activateUser(activationCode));
    }

    @GetMapping(PATH + "/auth/me")
    public AuthUser me() {
        return null;
    }
}
