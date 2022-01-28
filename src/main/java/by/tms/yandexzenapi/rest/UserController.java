package by.tms.yandexzenapi.rest;

import by.tms.yandexzenapi.dto.user.UserAdminDTO;
import by.tms.yandexzenapi.dto.user.UserDTO;
import by.tms.yandexzenapi.mapper.user.UserAdminMapper;
import by.tms.yandexzenapi.mapper.user.UserMapper;
import by.tms.yandexzenapi.model.User;
import by.tms.yandexzenapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;
    private final UserMapper userMapper;
    private final UserAdminMapper userAdminMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserAdminDTO> findById(@PathVariable long id) {
        final User user = service.findById(id);

        return user != null
                ? new ResponseEntity<>(userAdminMapper.toUserAdminDTO(user), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserAdminDTO>> findAll(){
        final List<UserAdminDTO> users = userAdminMapper.toUserAdminDTOList(service.findAll());

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserAdminDTO> delete(@PathVariable long id){
        final User user = service.findById(id);

        if (user != null){
            service.delete(id);
            return new ResponseEntity<>(userAdminMapper.toUserAdminDTO(user), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDTO> update(@PathVariable String username, @RequestBody UserDTO userDTO){
        User updatedUser = service.update(username, userMapper.toUser(userDTO));

        return new ResponseEntity<>(userMapper.toUserDTO(updatedUser), HttpStatus.OK);
    }
}
