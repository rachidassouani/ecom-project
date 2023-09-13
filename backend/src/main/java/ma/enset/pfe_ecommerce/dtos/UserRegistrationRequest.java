package ma.enset.pfe_ecommerce.dtos;

import ma.enset.pfe_ecommerce.model.Role;

import java.util.Set;

public record UserRegistrationRequest (
        String firstName,
        String lastName,
        String email,
        String password,
        Set<Role> roles) {
}
