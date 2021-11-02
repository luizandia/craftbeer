package com.beerhouse.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Beer {
    
    @Id
    @NotNull(message = "Id is mandatory")
    @Positive(message = "Id must be a positive Integer")
	private Integer id;

    @NotNull(message = "Name is mandatory")
    @NotEmpty(message = "Name can't be empty")
    private String name;

    @NotNull(message = "Ingredients is mandatory")
    @NotEmpty(message = "Ingredients can't be empty")
    private String ingredients;

    @NotNull(message = "AlcoholContent is mandatory")
    @NotEmpty(message = "AlcoholContent can't be empty")
    private String alcoholContent;

    @NotNull(message = "Price is mandatory")
    @PositiveOrZero(message = "Price must be postive or zero")
    private Float price;

    @NotNull(message = "Category is mandatory")
    @NotEmpty(message = "Category can't be empty")
    private String category;

    public Beer(){}
    public Beer(Integer id, String name, String ingredients, String alcoholContent, Float price, String category) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.alcoholContent = alcoholContent;
        this.price = price;
        this.category = category;
    }
    
	public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setAlcoholContent(String alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getIngredients() {
        return ingredients;
    }
    public String getAlcoholContent() {
        return alcoholContent;
    }
    public Float getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }
    
}
