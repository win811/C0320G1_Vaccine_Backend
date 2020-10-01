package com.c0320g1.vaccine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Contact {
    //    CREATE BY ANH ĐỨC
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String fullName;
    private String email;
    private String subject;
    private String text;
    private LocalDateTime receivingTime;
    private LocalDateTime endTime;
    private String status;
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    // MapopedBy trỏ tới tên biến contact ở trong replyContact.
    @JsonIgnoreProperties("contact")
    private List<ContactReply> contactReply;

}
