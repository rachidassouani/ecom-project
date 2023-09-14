package ma.enset.pfe_ecommerce.dtos;

import java.util.List;
import java.util.Set;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        Set<String> roles) {}

