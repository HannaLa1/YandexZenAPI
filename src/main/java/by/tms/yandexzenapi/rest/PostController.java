package by.tms.yandexzenapi.rest;

import by.tms.yandexzenapi.dto.post.PostDTO;
import by.tms.yandexzenapi.mapper.post.PostMapper;
import by.tms.yandexzenapi.model.Post;
import by.tms.yandexzenapi.service.PostService;
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
@RequestMapping("/api/post")
public class PostController {

    private final PostService service;
    private final PostMapper postMapper;

    @PostMapping("/{username}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="SECURITY_REFERENCE") })
    public ResponseEntity<PostDTO> save(@PathVariable String username, @RequestBody PostDTO postDTO){
        Post post = service.save(username, postMapper.toPost(postDTO));

        return new ResponseEntity<>(postMapper.toPostDTO(post), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="SECURITY_REFERENCE") })
    public ResponseEntity<List<PostDTO>> findAll(@PathVariable long id){
        List<PostDTO> posts = postMapper.toPostDTOList(service.findAll(id));

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="SECURITY_REFERENCE") })
    public ResponseEntity<PostDTO> update(@PathVariable long id, @RequestBody PostDTO postDTO){
        Post updatedPost = service.update(id, postMapper.toPost(postDTO));

        return new ResponseEntity<>(postMapper.toPostDTO(updatedPost), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="SECURITY_REFERENCE") })
    public ResponseEntity<PostDTO> delete(@PathVariable long id){
        final Post post = service.findById(id);

        if (post != null){
            service.delete(id);
            return new ResponseEntity<>(postMapper.toPostDTO(post), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
