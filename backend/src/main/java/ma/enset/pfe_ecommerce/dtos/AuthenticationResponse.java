package ma.enset.pfe_ecommerce.dtos;

public record AuthenticationResponse (
        UserDTO userDTO,
        String token
) {}

