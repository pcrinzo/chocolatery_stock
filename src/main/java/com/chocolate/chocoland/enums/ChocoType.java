package com.chocolate.chocoland.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChocoType {

    RICHMILK("Rich Milk"),
    MILK("Milk"),
    BITTER("Bitter"),
    RICHBITTER("Rich Bitter"),
    FRUITCHOCO("Fruit Choco"),
    WHITE("White Choco"),
    RUBY("Runy Choco");

    private final String description;
}
