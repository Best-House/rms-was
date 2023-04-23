package com.hojun.service.domain.aggregate.material_price.infra;

import com.hojun.service.domain.aggregate.material_price.MaterialPrice;

public interface MaterialPriceRepository {
    MaterialPrice getCommonMaterialPrice();
}
