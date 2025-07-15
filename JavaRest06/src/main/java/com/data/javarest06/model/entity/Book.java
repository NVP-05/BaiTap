package com.data.javarest06.model.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "Book")
public class Book {
    @JacksonXmlProperty(isAttribute = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JacksonXmlProperty(localName = "Title")
    private String title;
    @JacksonXmlProperty(localName = "Author")
    private String author;
    @JacksonXmlProperty(localName = "Price")
    private Double price;
}
