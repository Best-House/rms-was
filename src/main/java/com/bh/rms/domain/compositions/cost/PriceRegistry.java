package com.bh.rms.domain.compositions.cost;

import java.util.List;

public interface PriceRegistry {
    List<String> getUnknownPriceOf(List<String> materials);

    boolean containsKey(String materialId);

    double getUnitPrice(String materialId);
}
