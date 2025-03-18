package io.company.outsera.dto;

import io.company.outsera.utils.constantes.Parameters;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {

    private Number id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    @Builder.Default
    private String password = Parameters.USER_SENHA;
    private String phone;
    private int userStatus;

}
