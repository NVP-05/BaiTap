package com.data.javarest06.model.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "ProductCart")
public class ProductCart {

    @JacksonXmlProperty(isAttribute = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JacksonXmlProperty(localName = "Product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JacksonXmlProperty(localName = "User")
    private User user;

    @JacksonXmlProperty(localName = "Quantity")
    private Integer quantity;
}
