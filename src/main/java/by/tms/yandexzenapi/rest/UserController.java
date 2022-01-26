package by.tms.yandexzenapi.rest;

import by.tms.yandexzenapi.dto.UserAdminDTO;
import by.tms.yandexzenapi.dto.UserDTO;
import by.tms.yandexzenapi.mapper.UserAdminMapper;
import by.tms.yandexzenapi.mapper.UserMapper;
import by.tms.yandexzenapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;
    private final UserAdminMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<UserAdminDTO>> findAll(){
        List<UserAdminDTO> users = mapper.toUserAdminDTOList(service.findAll());

        return !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
