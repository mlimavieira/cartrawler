package com.mv.ct.model;

import com.mv.ct.model.type.FuelPolicy;
import com.mv.ct.model.type.SippCode;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CarOffer {

    private Supplier supplier;

    private String description;

    private SippCode sipp;

    @EqualsAndHashCode.Exclude
    private Double cost;

    private FuelPolicy fuelPolicy;

}
