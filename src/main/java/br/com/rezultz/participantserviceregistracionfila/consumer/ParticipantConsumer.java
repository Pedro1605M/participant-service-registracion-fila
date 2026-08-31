package br.com.rezultz.participantserviceregistracionfila.consumer;

import br.com.rezultz.participantserviceregistracionfila.config.RabbitMQConfig;
import br.com.rezultz.participantserviceregistracionfila.dto.ParticipantProducerResponse;
import br.com.rezultz.participantserviceregistracionfila.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ParticipantConsumer {

    private final  ParticipantService participantService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void createParticipant(ParticipantProducerResponse participantProducerResponse){
        log.info("Participant Recebido da fila do documento: {}", participantProducerResponse.request().document());
        participantService.createParticipant(participantProducerResponse);
    }

}
