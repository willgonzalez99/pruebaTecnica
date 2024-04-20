package com.eclub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Product product;

    private int quantity;

    public Sales(){}

    public Sales(Long id, Long productId, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
        if (!client.getSales().contains(this)) {
            client.getSales().add(this);
        }
    }

    public Product getProducto() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        if (!product.getSales().contains(this)) {
            product.getSales().add(this);
        }
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", client=" + client +
                ", producto=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
