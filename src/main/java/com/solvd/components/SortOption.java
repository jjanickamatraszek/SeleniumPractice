package com.solvd.components;

import lombok.Getter;

public enum SortOption {
    PRICE_ASC(0), PRICE_DESC(1), NEWEST(2), DEFAULT(3);

    @Getter
    private final String value;

    SortOption(int value) {
        this.value = String.valueOf(value);
    }
}
