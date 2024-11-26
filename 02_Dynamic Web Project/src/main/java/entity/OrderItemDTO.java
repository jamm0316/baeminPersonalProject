package entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    int id;
    int quantity;
    int foodId;
    String email;
    Date orderDate;
    String status;
}
