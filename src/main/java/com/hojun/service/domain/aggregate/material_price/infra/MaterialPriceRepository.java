package com.hojun.service.domain.aggregate.material_price.infra;

import com.hojun.service.domain.aggregate.material_price.MaterialUnitPrice;

public interface MaterialPriceRepository {
    MaterialUnitPrice getCommonMaterialPrice();
}
