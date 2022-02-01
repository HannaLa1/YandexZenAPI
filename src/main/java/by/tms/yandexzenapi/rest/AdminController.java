package by.tms.yandexzenapi.rest;

import by.tms.yandexzenapi.dto.post.PostUserDTO;
import by.tms.yandexzenapi.dto.tag.TagPostDTO;
import by.tms.yandexzenapi.mapper.post.PostUserMapper;
import by.tms.yandexzenapi.mapper.tag.TagPostMapper;
import by.tms.yandexzenapi.service.PostService;
import by.tms.yandexzenapi.service.TagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
@RequestMapping("/api/admin")
public class AdminController {

    private final PostService postService;
    private final TagService tagService;
    private final PostUserMapper postUserMapper;
    private final TagPostMapper tagUserMapper;

    @GetMapping("/posts")
    @ApiOperation(value = "", authorizations = { @Authorization(value="SECURITY_REFERENCE") })
    public ResponseEntity<List<PostUserDTO>> findAllPostsOfUsers(){
        List<PostUserDTO> posts = postUserMapper.toPostUserDTOList(postService.findAllPostsOfUsers());

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/tags")
    @ApiOperation(value = "", authorizations = { @Authorization(value="SECURITY_REFERENCE") })
    public ResponseEntity<List<TagPostDTO>> findAllTagsOfPosts(){
        List<TagPostDTO> tags = tagUserMapper.toTagUserDTOList(tagService.findAllTagsOfPosts());

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }
}
