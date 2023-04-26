package com.hojun.service.domain.aggregate.user_material_price;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.record.MaterialUnitPrice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserMaterialPriceTest {

    @Test
    public void entityEqualityTest() {
        UserMaterialPrice userMaterialPrice1 = new UserMaterialPrice("userMaterialPrice1");
        UserMaterialPrice userMaterialPrice2 = new UserMaterialPrice("userMaterialPrice1");
        assertEquals(userMaterialPrice1, userMaterialPrice2);
    }

    @Test
    void getMaterialUnitPriceTest() {
        Material material1 = new Material("material1", "");

        UserMaterialPrice userMaterialPrice = new UserMaterialPrice("");
        userMaterialPrice.addMaterial(material1, 16000, 1000);

        MaterialUnitPrice materialUnitPrice = userMaterialPrice.getMaterialUnitPrice();
        assertTrue(materialUnitPrice.contains(material1));
        assertEquals(16, materialUnitPrice.getPrice(material1));
    }

    @Test
    void addZeroAmountMaterialTest() {
        Material material1 = new Material("material1", "");
        UserMaterialPrice userMaterialPrice = new UserMaterialPrice("");

        assertThrows(InvalidAmountException.class, ()->{
            userMaterialPrice.addMaterial(material1, 16000, 0);
        });
    }
}