package entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {
    int id;
    String storeName;
    String foodName;
    int price;
    int quntity;
    int deliveryTime;
    Date orderDate;
    String status;
}
