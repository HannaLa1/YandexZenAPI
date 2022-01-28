package by.tms.yandexzenapi.rest;

import by.tms.yandexzenapi.dto.subscriber.SubscriberDTO;
import by.tms.yandexzenapi.mapper.subscriber.SubscriberMapper;
import by.tms.yandexzenapi.model.Subscriber;
import by.tms.yandexzenapi.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/subscriber")
public class SubscriberController {

    private final SubscriberService service;
    private final SubscriberMapper subscriberMapper;

    @PostMapping("/{id}")
    public ResponseEntity<SubscriberDTO> subscribe(@PathVariable long id, @RequestBody SubscriberDTO subscriberDTO){
        if (service.existByUserId(id, subscriberDTO.getUsername())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Subscriber subscriber = service.subscribe(id, subscriberMapper.toSubscriber(subscriberDTO));

        return new ResponseEntity<>(subscriberMapper.toSubscriberDTO(subscriber), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<SubscriberDTO>> findAll(@PathVariable long id){
        List<SubscriberDTO> subscribers = subscriberMapper.toSubscriberDTOList(service.findAll(id));

        return new ResponseEntity<>(subscribers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubscriberDTO> unsubscribe(@PathVariable long id){
        final Subscriber subscriber = service.findById(id);

        if (subscriber != null){
            service.unsubscribe(id);
            return new ResponseEntity<>(subscriberMapper.toSubscriberDTO(subscriber), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
