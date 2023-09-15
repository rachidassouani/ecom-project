package ma.enset.pfe_ecommerce.web;


import ma.enset.pfe_ecommerce.dtos.UserRegistrationRequest;
import ma.enset.pfe_ecommerce.jwt.JWTUtil;
import ma.enset.pfe_ecommerce.model.User;
import ma.enset.pfe_ecommerce.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin("*")
public class RegistrationController {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    public RegistrationController(UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("users")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest registrationRequest) {
        userService.saveUSer(registrationRequest);
        String jwtToken = jwtUtil.issueToken(registrationRequest.email(), "ROLE_USER");
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
    }

    @PostMapping("admins")
    public ResponseEntity<?> registerAdmin(@RequestBody UserRegistrationRequest registrationRequest) {
        userService.saveAdmin(registrationRequest);
        String jwtToken = jwtUtil.issueToken(registrationRequest.email(), "ROLE_ADMIN");
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
    }

    @GetMapping("admins")
    public ResponseEntity<?> findAllAdmins() {
        List<User> adminsList = userService.findAllAdmins();
        return ResponseEntity.ok().body(adminsList);
    }
}
