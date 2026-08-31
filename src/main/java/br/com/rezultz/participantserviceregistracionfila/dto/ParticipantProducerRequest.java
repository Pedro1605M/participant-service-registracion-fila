package br.com.rezultz.participantserviceregistracionfila.dto;

public record ParticipantProducerRequest(
        String document,
        String name,
        ContactRequest contact,
        AddressRequest address,
        AssociateRequest associate
) {}
