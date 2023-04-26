package com.hojun.service.domain.aggregate.user_material_price.infra;

import com.hojun.service.domain.aggregate.user_material_price.UserMaterialPrice;
import com.hojun.service.domain.record.MaterialUnitPrice;

public interface UserMaterialPriceRepository {
    UserMaterialPrice getUserMaterialPrice(String userMaterialPriceId);
}
