package by.tms.yandexzenapi.rest;

import by.tms.yandexzenapi.dto.tag.TagDTO;
import by.tms.yandexzenapi.dto.tag.TagPostDTO;
import by.tms.yandexzenapi.mapper.tag.TagMapper;
import by.tms.yandexzenapi.mapper.tag.TagPostMapper;
import by.tms.yandexzenapi.model.Tag;
import by.tms.yandexzenapi.service.TagService;
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
@RequestMapping("/api/tag")
public class TagController {

    private final TagService service;
    private final TagMapper tagMapper;
    private final TagPostMapper tagUserMapper;

    @PostMapping("/{username}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="SECURITY_REFERENCE") })
    public ResponseEntity<TagDTO> save(@PathVariable long id, @RequestBody TagDTO tagDTO){
        if (service.existByPostId(id, tagDTO.getName())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Tag tag = service.save(id, tagMapper.toTag(tagDTO));

        return new ResponseEntity<>(tagMapper.toTagDTO(tag), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="SECURITY_REFERENCE") })
    public ResponseEntity<List<TagDTO>> findAll(@PathVariable long id){
        List<TagDTO> tags = tagMapper.toTagDTOList(service.findAll(id));

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="SECURITY_REFERENCE") })
    public ResponseEntity<TagDTO> delete(@PathVariable long id){
        final Tag tag = service.findById(id);

        if (tag != null){
            service.delete(id);
            return new ResponseEntity<>(tagMapper.toTagDTO(tag), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/search/{name}")
    @ApiOperation(value = "", authorizations = { @Authorization(value="SECURITY_REFERENCE") })
    public ResponseEntity<List<TagPostDTO>> findPostsByTag(@PathVariable String name){
        List<TagPostDTO> tags = tagUserMapper.toTagUserDTOList(service.findPostsByTag(name));

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }
}
