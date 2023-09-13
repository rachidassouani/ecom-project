package ma.enset.pfe_ecommerce.services;

import ma.enset.pfe_ecommerce.dtos.UserRegistrationRequest;
import ma.enset.pfe_ecommerce.exceptions.DuplicateResourceException;
import ma.enset.pfe_ecommerce.model.Role;
import ma.enset.pfe_ecommerce.model.User;
import ma.enset.pfe_ecommerce.repositories.RoleRepository;
import ma.enset.pfe_ecommerce.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUSer(UserRegistrationRequest registrationRequest) {
        // check if user's email is already exist
        if (userRepository.existsUserByEmail(registrationRequest.email())) {
            throw new DuplicateResourceException("Email already taken");
        }

        // saving the user
        User userToSave = new User();
        userToSave.setFirstName(registrationRequest.firstName());
        userToSave.setLastName(registrationRequest.lastName());
        userToSave.setEmail(registrationRequest.email());
        userToSave.setPassword(passwordEncoder.encode(registrationRequest.password()));

        Role userRole = roleRepository.findByRoleName("ROLE_USER");

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        userToSave.setRoles(roles);
        userRepository.save(userToSave);
    }

    public void saveAdmin(UserRegistrationRequest registrationRequest) {
        // check if user's email is already exist
        if (userRepository.existsUserByEmail(registrationRequest.email())) {
            throw new DuplicateResourceException("Email already taken");
        }

        // saving the user
        User userToSave = new User();
        userToSave.setFirstName(registrationRequest.firstName());
        userToSave.setLastName(registrationRequest.lastName());
        userToSave.setEmail(registrationRequest.email());
        userToSave.setPassword(passwordEncoder.encode(registrationRequest.password()));

        Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN");
        //Role userRole = roleRepository.findByRoleName("ROLE_USER");

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        //roles.add(userRole);
        userToSave.setRoles(roles);
        userRepository.save(userToSave);
    }

    public List<User> findAllAdmins() {
        List<User> adminsList = userRepository.findByRolesRoleName("ROLE_ADMIN");
        return adminsList;
    }

}
