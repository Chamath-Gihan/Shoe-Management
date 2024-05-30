package lk.ijse.shoe_management.auth.request;

import lk.ijse.shoe_management.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignInRequest {

    private String email;

    private String password;

    private Role role;
}
