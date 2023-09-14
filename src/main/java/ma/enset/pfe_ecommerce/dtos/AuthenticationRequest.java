package ma.enset.pfe_ecommerce.dtos;

public record AuthenticationRequest(
        String email,
        String password) {}