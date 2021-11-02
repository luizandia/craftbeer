package com.beerhouse.model;

public class Beer {
    
	private Integer id;
    private String name;
    private String ingredients;
    private String alcoholContent;
    private Float price;
    private String category;

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
