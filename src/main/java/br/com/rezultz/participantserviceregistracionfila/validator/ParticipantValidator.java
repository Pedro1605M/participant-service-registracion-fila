package br.com.rezultz.participantserviceregistracionfila.validator;


import br.com.rezultz.participantserviceregistracionfila.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipantValidator {
    private final ParticipantRepository participantRepository;

    public void validateDocumentDoesNotExist(String document) {
        if (participantRepository.existsByDocument(document)){
            throw new AmqpRejectAndDontRequeueException("Já existe um participante cadastrado com o documento: " + document);
        }
    }
}
