package com.mv.ct.util;

import com.mv.ct.model.CarOffer;
import com.mv.ct.model.Supplier;
import com.mv.ct.model.type.SippCode;

import java.util.Comparator;

public final class ComparatorUtil {

    private ComparatorUtil() {

    }

    public static Comparator<CarOffer> getPriceComparator() {
        return (o1, o2) -> {
            return Double.valueOf(o1.getCost() - o2.getCost()).intValue();
        };
    }

    public static Comparator<CarOffer> getGroupsComparator() {
        return (o1, o2) -> {
            return getGroupRank(o1.getSipp()) - getGroupRank(o2.getSipp());
        };
    }

    public static Comparator<CarOffer> getEnterpriseComparator() {
        return (o1, o2) -> {
            return getSupplierRank(o1.getSupplier()) - getSupplierRank(o2.getSupplier());
        };
    }

    private static int getGroupRank(SippCode sippCode) {
        switch (sippCode.getCategory()) {
            case "M":
                return 1;
            case "E":
                return 2;
            case "C":
                return 3;
            default:
                return 100;
        }
    }

    private static int getSupplierRank(Supplier supplier) {

        switch (supplier.getName()) {
            case "AVIS":
                return 1;
            case "BUDGET":
                return 2;
            case "ENTERPRISE":
                return 3;
            case "HERTZ":
                return 4;
            case "SIXT":
                return 5;
            case "THRIFTY":
                return 6;
            case "FIREFLY":
                return 7;
            default:
                return 100;
        }

    }
}

