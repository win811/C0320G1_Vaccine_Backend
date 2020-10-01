package com.c0320g1.vaccine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String replyFile;
    private LocalDateTime replyTime;
    @ManyToOne
    @JoinColumn(name = "contact_id") // thông qua khóa ngoại contact_id
    @JsonIgnoreProperties("contactReply")
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "account_id") // thông qua khóa ngoại account_id

    @JsonIgnoreProperties({"contactReply","password","address"})
    private Account account;
    public Account getAccount() {
        return account;
    }

}
