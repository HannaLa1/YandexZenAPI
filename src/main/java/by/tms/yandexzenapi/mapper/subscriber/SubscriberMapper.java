package by.tms.yandexzenapi.mapper.subscriber;

import by.tms.yandexzenapi.dto.subscriber.SubscriberDTO;
import by.tms.yandexzenapi.model.Subscriber;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriberMapper {

    SubscriberDTO toSubscriberDTO(Subscriber subscriber);
    Subscriber toSubscriber(SubscriberDTO subscriberDTO);
    List<SubscriberDTO> toSubscriberDTOList(List<Subscriber> subscribers);
}
