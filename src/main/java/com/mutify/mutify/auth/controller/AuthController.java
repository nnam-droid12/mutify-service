package com.mutify.mutify.auth.controller;


import com.mutify.mutify.auth.entities.RefreshToken;
import com.mutify.mutify.auth.entities.User;
import com.mutify.mutify.auth.repository.UserRepository;
import com.mutify.mutify.auth.service.AuthService;
import com.mutify.mutify.auth.service.JwtService;
import com.mutify.mutify.auth.service.RefreshTokenService;
import com.mutify.mutify.auth.utils.AuthResponse;
import com.mutify.mutify.auth.utils.LoginRequest;
import com.mutify.mutify.auth.utils.RefreshTokenRequest;
import com.mutify.mutify.auth.utils.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, UserRepository userRepository, RefreshTokenService refreshTokenService, JwtService jwtService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }



    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerHandler(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }






    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshTokenHandler(@RequestBody RefreshTokenRequest refreshTokenRequest){
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken());
        User user = refreshToken.getUser();
        String accessToken = jwtService.generateToken(user);

        return ResponseEntity.ok(AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build());

    }

}





