package com.example.demoproductcrudexample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_TBL")
public class Product {
    @Id
    @GeneratedValue
    private int id;
    @NotEmpty(message = "the product name must not be null or empty")
    @Size(min = 3, max = 8, message = "the product name must be between {min} and {max}")
    private String name;
    //@Min(value = 10, message = "quantity should not be less than 10")
    //@Max(value = 100, message = "quantity should not be less than 10")
    @NotNull(message = "value must not be null or empty")
    @Range(min = 10, max = 100, message = "quantity must be greater than or equal to {min} or less than or equal to {max}")
    private Integer quantity;
    @NotNull(message = "value must not be null or empty")
    private Double price;

}
