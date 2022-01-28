package by.tms.yandexzenapi.rest;

import by.tms.yandexzenapi.dto.tag.TagDTO;
import by.tms.yandexzenapi.dto.tag.TagUserDTO;
import by.tms.yandexzenapi.mapper.tag.TagMapper;
import by.tms.yandexzenapi.mapper.tag.TagUserMapper;
import by.tms.yandexzenapi.model.Tag;
import by.tms.yandexzenapi.service.TagService;
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
    private final TagUserMapper tagUserMapper;

    @PostMapping("/{username}")
    public ResponseEntity<TagDTO> save(@PathVariable String username, @RequestBody TagDTO tagDTO){
        if (service.existByUserUsername(username, tagDTO.getName())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Tag tag = service.save(username, tagMapper.toTag(tagDTO));

        return new ResponseEntity<>(tagMapper.toTagDTO(tag), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<TagDTO>> findAll(@PathVariable long id){
        List<TagDTO> tags = tagMapper.toTagDTOList(service.findAll(id));

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TagDTO> delete(@PathVariable long id){
        final Tag tag = service.findById(id);

        if (tag != null){
            service.delete(id);
            return new ResponseEntity<>(tagMapper.toTagDTO(tag), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<TagUserDTO>> findUsersByTag(@PathVariable String name){
        List<TagUserDTO> tags = tagUserMapper.toTagUserDTOList(service.findUsersByTag(name));

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }
}
