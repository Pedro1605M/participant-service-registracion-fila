package br.com.rezultz.participantserviceregistracionfila.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "enrl_participant_address")
public class ParticipantAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enrl_participant_id", nullable = false)
    private Long participantId;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private Boolean deleted;

    @Column(nullable = false)
    private Integer status;

    @Column(name = "status_message")
    private String statusMessage;

    @Column(name = "status_name", length = 50)
    private String statusName;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    private String city;

    private String district;

    @Column(nullable = false)
    private String line;

    @Column(name = "line_optional")
    private String lineOptional;

    @Column(length = 50)
    private String number;

    @Column(length = 2)
    private String state;

    @Column(nullable = false)
    private String type;

    @Column(name = "zip_code", length = 9)
    private String zipCode;
}
