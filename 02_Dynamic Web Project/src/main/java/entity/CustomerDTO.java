package entity;

import lombok.*;

@Getter @Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private String password;
    private String email;
    private String nickName;
    private int location;
}
