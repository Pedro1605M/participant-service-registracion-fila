package br.com.rezultz.participantserviceregistracionfila.repository;

import br.com.rezultz.participantserviceregistracionfila.entity.ParticipantAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantAddressRepository  extends JpaRepository<ParticipantAddress,Long> {
}
