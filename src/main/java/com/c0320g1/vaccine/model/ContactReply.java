package com.c0320g1.vaccine.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class ContactReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String replyText;
    private LocalDateTime replyTime;
    @ManyToOne
    @JoinColumn(name = "contact_id") // thông qua khóa ngoại contact_id
    @EqualsAndHashCode.Exclude
    private Contact contact;
}
