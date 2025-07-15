package com.data.javarest5.model.response;

import com.data.javarest5.model.entity.Product;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "products")
public class ProductList {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Product> products;

    public ProductList() {}

    public ProductList(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
