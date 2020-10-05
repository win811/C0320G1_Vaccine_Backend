package com.c0320g1.vaccine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "confirmation_tokens")
@Getter
@Setter
public class ConfirmationToken {

    public ConfirmationToken() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createDate;

    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public ConfirmationToken(Account account) {
        this.account = account;
        createDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
