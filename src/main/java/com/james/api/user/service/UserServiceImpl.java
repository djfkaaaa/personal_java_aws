package com.james.api.user.service;



import com.james.api.common.component.JwtProvider;
import com.james.api.common.component.MessengerVo;
import com.james.api.user.model.User;
import com.james.api.user.model.UserDto;
import com.james.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    private final JwtProvider jwtProvider;
    @Override
    public MessengerVo save(UserDto dto) {
        entityToDto(repo.save(dtoToEntity(dto)));
        return MessengerVo.builder()
                .message("Success")
                .build();

    }

    @Override
    public MessengerVo deleteById(Long id) {
        repo.deleteById(id);
        return MessengerVo.builder()
                .message(repo.findById(id).isPresent() ? "Success" : "Fail")
                .build();
    }

    @Override
    public List<UserDto> findAll() {
        return repo.findAll().stream().map(i->entityToDto(i)).toList();
    }
    //stream안에 있는 map method

    @Override
    public Optional<UserDto> findById(Long id) {return repo.findById(id).map(i->entityToDto(i));}

    @Override
    public MessengerVo modify(UserDto dto) {
//        throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
        repo.save(dtoToEntity(dto));
        return MessengerVo.builder()
                .message("success")
                .build();
    }

    @Override
    public Long count() {
        return repo.count();
    }

    @Override
    public List<UserDto> findUserByName(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'findUserByName'");
    }

    @Override
    public List<UserDto> findUserByJob(String job) {
        throw new UnsupportedOperationException("Unimplemented method 'findUserByJob'");
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return repo.findUserByUsername(username);
    }


    @Override
    public MessengerVo login(UserDto param){

        boolean flag = repo.findUserByUsername(param.getUsername()).get().getPassword().equals(param.getPassword());

        return MessengerVo.builder()
                .message(flag? "Success" : "failure")
                .token(flag? jwtProvider.createToken(param) : "none")
                .build();}
    }

