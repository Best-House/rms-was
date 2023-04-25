package com.hojun.service.infra;

import com.hojun.service.domain.aggregate.material.Material;
import com.hojun.service.domain.record.MaterialUnitPrice;
import com.hojun.service.domain.aggregate.user_material_price.infra.MaterialUnitPriceRepository;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.stream.Collectors;

@EnableConfigurationProperties(YmlMaterialUnitPriceRepository.MaterialPriceProperties.class)
@Repository
public class YmlMaterialUnitPriceRepository implements MaterialUnitPriceRepository {

    private final MaterialPriceProperties materialPriceProperties;

    public YmlMaterialUnitPriceRepository(MaterialPriceProperties materialPriceProperties) {
        this.materialPriceProperties = materialPriceProperties;
    }

    @Override
    public MaterialUnitPrice getCommonMaterialUnitPrice() {
        return materialPriceProperties.getMaterialPrice();
    }

    @Data
    @ConfigurationProperties("material-price")
    public static class MaterialPriceProperties {
        private Map<String, Double> priceMap;

        public MaterialUnitPrice getMaterialPrice() {
            return new MaterialUnitPrice(
                    priceMap.entrySet().stream()
                    .collect(
                            Collectors.toMap(e-> new Material(e.getKey(), ""), Map.Entry::getValue)
                    )
            );
        }
    }
}
