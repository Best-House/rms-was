package com.hojun.service.infra;

import com.hojun.service.domain.material.Material;
import com.hojun.service.domain.material_price.MaterialPrice;
import com.hojun.service.domain.material_price.infra.MaterialPriceRepository;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.stream.Collectors;

@EnableConfigurationProperties(YmlMaterialPriceRepository.MaterialPriceProperties.class)
@Repository
public class YmlMaterialPriceRepository implements MaterialPriceRepository {

    private final MaterialPriceProperties materialPriceProperties;

    public YmlMaterialPriceRepository(MaterialPriceProperties materialPriceProperties) {
        this.materialPriceProperties = materialPriceProperties;
    }

    @Override
    public MaterialPrice getCommonMaterialPrice() {
        return materialPriceProperties.getMaterialPrice();
    }

    @Data
    @ConfigurationProperties("material-price")
    public static class MaterialPriceProperties {
        private Map<String, Double> priceMap;

        public MaterialPrice getMaterialPrice() {
            return new MaterialPrice(
                    priceMap.entrySet().stream()
                    .collect(
                            Collectors.toMap(e-> new Material(e.getKey(), ""), Map.Entry::getValue)
                    )
            );
        }
    }
}
