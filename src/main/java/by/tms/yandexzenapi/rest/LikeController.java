package by.tms.yandexzenapi.rest;

import by.tms.yandexzenapi.dto.like.LikeDTO;
import by.tms.yandexzenapi.mapper.like.LikeMapper;
import by.tms.yandexzenapi.model.Like;
import by.tms.yandexzenapi.service.LikeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService service;
    private final LikeMapper likeMapper;

    @PostMapping("/{id}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="SECURITY_REFERENCE") })
    public ResponseEntity<LikeDTO> save(@PathVariable long id, @RequestBody LikeDTO likeDTO){
        if (service.existByPostId(id, likeDTO.getNameOfUser())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Like like = service.save(id, likeMapper.toLike(likeDTO));

        return new ResponseEntity<>(likeMapper.toLikeDTO(like), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="SECURITY_REFERENCE") })
    public ResponseEntity<List<LikeDTO>> findAll(@PathVariable long id){
        List<LikeDTO> likes = likeMapper.toLikeDTOList(service.findAll(id));

        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="SECURITY_REFERENCE") })
    public ResponseEntity<LikeDTO> delete(@PathVariable long id){
        final Like like = service.findById(id);

        if (like != null){
            service.delete(id);
            return new ResponseEntity<>(likeMapper.toLikeDTO(like), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
