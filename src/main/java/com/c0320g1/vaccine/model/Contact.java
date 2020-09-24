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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String email;
    private String subject;
    private String text;
    private LocalDateTime receivingTime;
    private LocalDateTime endTime;
    private String status;
    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến contact ở trong replyContact.
    private List<ContactReply> contactReply;

    @JsonIgnoreProperties("contact")
    public List<ContactReply> getContactReply() {
        return contactReply;
    }

    @JsonProperty
    public void setContactReply(ContactReply contactReply) {
        this.contactReply = (List<ContactReply>) contactReply;
    }
}
