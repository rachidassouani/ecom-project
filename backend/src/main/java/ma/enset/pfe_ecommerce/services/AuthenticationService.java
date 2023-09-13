
package ma.enset.pfe_ecommerce.services;

import ma.enset.pfe_ecommerce.dtos.AuthenticationRequest;
import ma.enset.pfe_ecommerce.dtos.AuthenticationResponse;
import ma.enset.pfe_ecommerce.dtos.UserDTO;
import ma.enset.pfe_ecommerce.jwt.JWTUtil;
import ma.enset.pfe_ecommerce.mappers.UserMapper;
import ma.enset.pfe_ecommerce.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JWTUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 UserMapper userMapper,
                                 JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        System.out.println("okk");
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        // get the user from the authenticate object
        User user = (User) authenticate.getPrincipal();

        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println("roles" + user.getRoles().size());
        if (user.getRoles() != null) {
            user.getRoles().forEach(role -> System.out.println("rooles " + role.getRoleName()));
        } else  {
            System.out.println("Null");
        }
        // cast the user to customerDTO
        UserDTO userDTO = userMapper.fromUser(user);

        // issue a token
        String token = jwtUtil.issueToken(userDTO.email(), userDTO.roles());

        return new AuthenticationResponse(userDTO, token);
    }
}
