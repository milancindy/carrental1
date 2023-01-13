package top.naccl.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CarList {
    private String id;
    private String name;
    private String brand;
    private String feature;
    private String priceYear;
    private String priceHalfyear;
    private String priceSeason;
    private String priceMonth;
    private String remark;
    private String image;
}
