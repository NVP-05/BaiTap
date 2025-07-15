package com.data.javarest06.model.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "User")
public class User {

    @JacksonXmlProperty(isAttribute = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JacksonXmlProperty(localName = "Username")
    private String username;

    @JacksonXmlProperty(localName = "Email")
    private String email;

    @JacksonXmlProperty(localName = "Password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCart> carts;
}
