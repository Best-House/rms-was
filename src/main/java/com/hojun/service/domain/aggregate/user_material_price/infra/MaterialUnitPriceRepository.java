package com.hojun.service.domain.aggregate.user_material_price.infra;

import com.hojun.service.domain.record.MaterialUnitPrice;

public interface MaterialUnitPriceRepository {
    MaterialUnitPrice getCommonMaterialUnitPrice();
}
