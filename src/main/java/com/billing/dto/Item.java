package com.billing.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Item {

    private String name;
    private double price;
    private boolean grocery;
    @JsonIgnore
    private double totalCost;
    private int quantity;

    public Item(String name, double price, boolean grocery, int quantity) {
        this.name = name;
        this.price = price;
        this.grocery = grocery;
        this.quantity = quantity;
    }

    public Double getTotalCost(){
        return price*quantity;
    }

}
