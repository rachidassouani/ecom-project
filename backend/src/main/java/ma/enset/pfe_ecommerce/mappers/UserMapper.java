package ma.enset.pfe_ecommerce.mappers;

import ma.enset.pfe_ecommerce.dtos.UserDTO;
import ma.enset.pfe_ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper {

    public UserDTO fromUser(User user){
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAuthorities()
                        .stream()
                        .map(role -> role.getAuthority())
                        .collect(Collectors.toSet())
        );
    }
}
