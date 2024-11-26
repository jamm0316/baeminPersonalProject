package entity;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
    int foodId;
    String name;
    int price;
    int maxCookingTime;
    int storeId;
}
