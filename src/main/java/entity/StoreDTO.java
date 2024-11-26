package entity;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {
    int id;
    String name;
    int area_id;
    String ownerEmail;
    int category;
}
