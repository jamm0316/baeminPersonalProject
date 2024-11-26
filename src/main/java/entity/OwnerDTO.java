package entity;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {
    String email;
    String name;
    String password;
}
