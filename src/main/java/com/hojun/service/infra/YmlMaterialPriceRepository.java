package com.hojun.service.infra;

import com.hojun.service.domain.model.MaterialPrice;
import com.hojun.service.domain.repository.MaterialPriceRepository;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Repository;

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

    @Getter
    @ConfigurationProperties("material-price")
    public static class MaterialPriceProperties {
        private MaterialPrice materialPrice;
    }
}
