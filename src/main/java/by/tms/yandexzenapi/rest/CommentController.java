package by.tms.yandexzenapi.rest;

import by.tms.yandexzenapi.dto.comment.CommentDTO;
import by.tms.yandexzenapi.mapper.comment.CommentMapper;
import by.tms.yandexzenapi.model.Comment;
import by.tms.yandexzenapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService service;
    private final CommentMapper commentMapper;

    @PostMapping("/{id}")
    public ResponseEntity<CommentDTO> save(@PathVariable long id, @RequestBody CommentDTO commentDTO){
        Comment comment = service.save(id, commentMapper.toComment(commentDTO));

        return new ResponseEntity<>(commentMapper.toCommentDTO(comment), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<CommentDTO>> findAll(@PathVariable long id){
        List<CommentDTO> comments = commentMapper.toCommentDTOList(service.findAll(id));

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDTO> delete(@PathVariable long id){
        final Comment comment = service.findById(id);

        if (comment != null){
            service.delete(id);
            return new ResponseEntity<>(commentMapper.toCommentDTO(comment), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable long id, @RequestBody CommentDTO commentDTO){
        Comment updatedComment = service.update(id, commentMapper.toComment(commentDTO));

        return new ResponseEntity<>(commentMapper.toCommentDTO(updatedComment), HttpStatus.OK);
    }
}
