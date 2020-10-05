package com.c0320g1.vaccine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Account {
    //    CREATE BY ANH ĐỨC
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String userName;
    private String password;
    private String fullName;
    private String idCard;
    private String address;
    private String email;
    private LocalDate birthday;
    private String role;
    private String phoneNumber;
    private Boolean confirmStatus;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    // Quan hệ 1-n với đối tượng ở dưới (Contact) (1 account có nhiều reply )
    // MapopedBy trỏ tới tên biến account ở trong replyContact.
    @JsonIgnoreProperties("account")
    private List<ContactReply> contactReply;

}
