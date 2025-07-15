package com.data.javarest06.model.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "Product")
public class Product {

    @JacksonXmlProperty(isAttribute = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JacksonXmlProperty(localName = "Name")
    private String name;

    @JacksonXmlProperty(localName = "Price")
    private Double price;

    @JacksonXmlProperty(localName = "Description")
    private String description;

    @JacksonXmlProperty(localName = "Stock")
    private Integer stock;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCart> carts;
}
