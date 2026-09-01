package br.com.rezultz.participantserviceregistracionfila.repository;

import br.com.rezultz.participantserviceregistracionfila.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository  extends JpaRepository<Participant,Long> {
    boolean existsByDocument(String document);
}
