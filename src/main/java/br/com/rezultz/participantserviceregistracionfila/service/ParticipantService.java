package br.com.rezultz.participantserviceregistracionfila.service;

import br.com.rezultz.participantserviceregistracionfila.dto.ParticipantProducerRequest;
import br.com.rezultz.participantserviceregistracionfila.dto.ParticipantProducerResponse;
import br.com.rezultz.participantserviceregistracionfila.entity.Participant;
import br.com.rezultz.participantserviceregistracionfila.entity.ParticipantAddress;
import br.com.rezultz.participantserviceregistracionfila.entity.ParticipantAssociate;
import br.com.rezultz.participantserviceregistracionfila.entity.ParticipantContact;
import br.com.rezultz.participantserviceregistracionfila.repository.ParticipantAddressRepository;
import br.com.rezultz.participantserviceregistracionfila.repository.ParticipantAssociateRepository;
import br.com.rezultz.participantserviceregistracionfila.repository.ParticipantContactRepository;
import br.com.rezultz.participantserviceregistracionfila.repository.ParticipantRepository;
import br.com.rezultz.participantserviceregistracionfila.validator.ParticipantValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final ParticipantAddressRepository participantAddressRepository;
    private final ParticipantAssociateRepository participantAssociateRepository;
    private final ParticipantContactRepository participantContactRepository;
    private final ParticipantValidator participantValidator;

    @Transactional
    public void createParticipant(ParticipantProducerResponse response){
        ParticipantProducerRequest request = response.request();

        participantValidator.validateDocumentDoesNotExist(request.document());
        Participant participant = saveParticipant(request);
        Long participantId = participant.getId();

        log.info("ID gerado para o participante (documento: {}): {}", request.document(), participantId);

        saveContact(request, participantId);
        saveAddress(request, participantId);
        saveAssociate(request, participantId);
        log.info("Participant do documento {} salvo com todas as suas informações no banco", request.document());
    }

    private Participant saveParticipant(ParticipantProducerRequest request) {
        log.info("Salvando participante principal (documento: {})", request.document());
        Participant participant = Participant.builder()
                .uuid(UUID.randomUUID())
                .createDate(LocalDateTime.now())
                .deleted(false)
                .status(1)
                .statusName("ACTIVE")
                .documentType("CNPJ")
                .tradeName("REZULTZ")
                .document(request.document())
                .name(request.name())
                .build();

        return participantRepository.save(participant);
    }

    private void saveContact(ParticipantProducerRequest request, Long participantId) {
        log.info("Salvando contato do participante ID: {}", participantId);
        ParticipantContact participantContact = ParticipantContact.builder()
                .enrlParticipantId(participantId)
                .name(request.contact().name())
                .email(request.contact().email())
                .mobile(request.contact().mobile())
                .phone(request.contact().phone())
                .uuid(UUID.randomUUID())
                .createDate(LocalDateTime.now())
                .deleted(false)
                .status(1)
                .build();

        participantContactRepository.save(participantContact);
    }

    private void saveAddress(ParticipantProducerRequest request, Long participantId) {
        log.info("Salvando endereço do participante ID: {}", participantId);
        ParticipantAddress participantAddress = ParticipantAddress.builder()
                .participantId(participantId)
                .line(request.address().street())
                .number(request.address().number())
                .city(request.address().city())
                .state(request.address().state())
                .uuid(UUID.randomUUID())
                .createDate(LocalDateTime.now())
                .deleted(false)
                .status(1)
                .type("empresa")
                .build();

        participantAddressRepository.save(participantAddress);
    }

    private void saveAssociate(ParticipantProducerRequest request, Long participantId) {
        log.info("Salvando sócio do participante ID: {}", participantId);
        ParticipantAssociate participantAssociate = ParticipantAssociate.builder()
                .enrlParticipantId(participantId)
                .name(request.associate().name())
                .document(request.associate().document())
                .email(request.associate().email())
                .mobile(request.associate().mobile())
                .phone(request.associate().phone())
                .uuid(UUID.randomUUID())
                .createDate(LocalDateTime.now())
                .deleted(false)
                .status(1)
                .build();

        participantAssociateRepository.save(participantAssociate);
    }
}
