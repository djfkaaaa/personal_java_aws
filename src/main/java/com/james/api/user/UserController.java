package com.james.api.user;

import com.james.api.common.component.MessengerVo;
import com.james.api.common.component.PageRequestVo;
import com.james.api.user.model.UserDto;
import com.james.api.user.repository.UserRepository;
import com.james.api.user.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RequestMapping(path = "/api/users")
@Slf4j
public class UserController {

    private final UserServiceImpl service;
    private final UserRepository repo;

    @SuppressWarnings("static-access")
    @PostMapping("/save")
    public ResponseEntity<MessengerVo> save(@RequestBody UserDto dto){
        log.info("입력받은 정보 : {}",dto);
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> findAll(){
//        log.info("입력받은 정보 : {}");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<Optional<UserDto>> findById(@RequestParam("id") Long id){
        log.info("입력받은 정보 : {}",id);
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/modify")
    public ResponseEntity<MessengerVo> modify(@RequestBody UserDto dto){
        log.info("입력받은 정보 : {}",dto);
        return ResponseEntity.ok(service.modify(dto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessengerVo> deleteById(@RequestParam Long id){
        log.info("입력받은 정보 : {}",id);
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(){
        return ResponseEntity.ok(service.count());
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserDto>> findUserByName(@RequestBody UserDto dto){
        return ResponseEntity.ok(service.findUserByName(dto.getName()));
    }

    @PostMapping("/login")
    public ResponseEntity<MessengerVo> login(@RequestBody UserDto dto){
        log.info("입력받은 정보 : {}",dto);
        return ResponseEntity.ok(service.login(dto));
    }
}
