package br.com.rezultz.participantserviceregistracionfila.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enrl_participant_contact")
public class ParticipantContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date", nullable = false, updatable = false)
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

    private String email;

    @Column(length = 45)
    private String mobile;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 45)
    private String phone;

    @Column(name = "enrl_participant_id")
    private Long enrlParticipantId;
}
