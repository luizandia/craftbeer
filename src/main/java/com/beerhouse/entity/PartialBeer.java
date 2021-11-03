package com.beerhouse.entity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PartialBeer {
    
    @Pattern(regexp = "^(?!\\s*$).+", message = "name must not be blank")
    private String name;

    @Pattern(regexp = "^(?!\\s*$).+", message = "ingredients must not be blank")
    private String ingredients;

    @Pattern(regexp = "^(?!\\s*$).+", message = "alcoholContent must not be blank")
    private String alcoholContent;
    
    @PositiveOrZero(message = "Price must be postive or zero")
    private Float price;

    @Pattern(regexp = "^(?!\\s*$).+", message = "category must not be blank")
    private String category;

}