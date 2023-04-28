package com.hojun.service.infra;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.aggregate.user_material_price.UserMaterialPrice;
import com.hojun.service.domain.record.MaterialUnitPrice;
import com.hojun.service.domain.aggregate.user_material_price.infra.UserMaterialPriceRepository;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@EnableConfigurationProperties(YmlUserMaterialPriceRepository.UserMaterialPriceProperties.class)
@Repository
public class YmlUserMaterialPriceRepository implements UserMaterialPriceRepository {

    private final UserMaterialPriceProperties userMaterialPriceProperties;

    public YmlUserMaterialPriceRepository(UserMaterialPriceProperties userMaterialPriceProperties) {
        this.userMaterialPriceProperties = userMaterialPriceProperties;
    }


    @Override
    public UserMaterialPrice getUserMaterialPrice(String userMaterialPriceId) {
        return userMaterialPriceProperties.getUserMaterialPrice(userMaterialPriceId);
    }

    @Data
    @ConfigurationProperties("user-material-price")
    public static class UserMaterialPriceProperties {
        private Map<String, Set<MaterialPriceAmount>> userMaterialPriceMap;

        public UserMaterialPrice getUserMaterialPrice(String userMaterialPriceId) {
            Set<MaterialPriceAmount> materialPriceAmounts = userMaterialPriceMap.get(userMaterialPriceId);
            if(materialPriceAmounts == null) {
                return null;
            }

            UserMaterialPrice userMaterialPrice = new UserMaterialPrice(userMaterialPriceId);
            for(MaterialPriceAmount materialPriceAmount : materialPriceAmounts) {
                userMaterialPrice.addMaterial(
                        new Material(materialPriceAmount.getMaterialId(), null),
                        materialPriceAmount.getPrice(),
                        materialPriceAmount.getAmount()
                );
            }
            return userMaterialPrice;
        }
    }

    @Data
    private static class MaterialPriceAmount {
        private String materialId;
        private double price;
        private double amount;
    }
}
