package com.data.javarest5.model.entity;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JacksonXmlProperty(localName = "id")
    private Long id;

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "price")
    private Double price;

    @JacksonXmlProperty(localName = "stock")
    private Integer stock;

    @JacksonXmlProperty(localName = "status")
    private Boolean status;

    @JacksonXmlProperty(localName = "created_at")
    private LocalDate createdAt;
}
