package by.tms.yandexzenapi.rest;

import by.tms.yandexzenapi.dto.UserAdminDTO;
import by.tms.yandexzenapi.mapper.UserAdminMapper;
import by.tms.yandexzenapi.model.User;
import by.tms.yandexzenapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService service;
    private final UserAdminMapper mapper;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserAdminDTO> getUserById(@PathVariable long id) {
        User user = service.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(mapper.toUserAdminDTO(user), HttpStatus.OK);
    }
}
