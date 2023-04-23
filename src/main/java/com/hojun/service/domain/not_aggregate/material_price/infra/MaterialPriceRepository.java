package com.hojun.service.domain.not_aggregate.material_price.infra;

import com.hojun.service.domain.not_aggregate.material_price.MaterialUnitPrice;

public interface MaterialPriceRepository {
    MaterialUnitPrice getCommonMaterialPrice();
}
