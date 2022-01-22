package by.tms.yandexzenapi.rest;

import by.tms.yandexzenapi.dto.AuthRequestDto;
import by.tms.yandexzenapi.dto.UserDTO;
import by.tms.yandexzenapi.mapper.UserMapper;
import by.tms.yandexzenapi.model.User;
import by.tms.yandexzenapi.security.jwt.JWTTokenProvider;
import by.tms.yandexzenapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;

    @PostMapping("/logIn")
    public ResponseEntity<Map<String, String>> logIn(@RequestBody AuthRequestDto requestDto){

        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = service.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.generateToken(username, user.getRoleList());

            Map<String, String> resp = new HashMap<>();
            resp.put("username", username);
            resp.put("token", token);

            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

//        User byUsername = service.findByUsername(userDTO.getUsername());
//
//        return byUsername != null
//                ? new  ResponseEntity<>(userMapper.toUserDTO(byUsername), HttpStatus.OK)
//                : new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDTO> registration(@RequestBody UserDTO userDTO){
        service.registration(userMapper.toUser(userDTO));

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
}
