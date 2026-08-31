package br.com.rezultz.participantserviceregistracionfila.repository;

import br.com.rezultz.participantserviceregistracionfila.entity.ParticipantContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantContactRepository  extends JpaRepository<ParticipantContact,Long> {
}
