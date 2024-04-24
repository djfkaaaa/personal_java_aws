package com.james.api.user;

import com.james.api.common.component.MessengerVo;
import com.james.api.common.security.service.AuthServiceImpl;
import com.james.api.user.model.UserDto;
import com.james.api.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RequestMapping(path = "/api/auth")
@Slf4j
public class AuthController {

    private final UserServiceImpl service;

    @GetMapping("/getusername")
    public ResponseEntity<Boolean> findByUsername(@RequestParam("username") String username){
        log.info("입력받은 정보 : {}",username);
        return ResponseEntity.ok(service.findByUsername(username));
    }

    @PostMapping("/login")
    public ResponseEntity<MessengerVo> login(@RequestBody UserDto dto){
        log.info("입력받은 정보 : {}",dto);
        return ResponseEntity.ok(service.login(dto));
    }
}
