package com.hojun.service.domain.aggregate.user_material_price;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserMaterialPriceTest {

    @Test
    public void test() {
        UserMaterialPrice userMaterialPrice = new UserMaterialPrice();
        userMaterialPrice.getMaterialUnitPrice();
    }
}