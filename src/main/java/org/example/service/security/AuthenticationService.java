package org.example.service.security;

import lombok.RequiredArgsConstructor;
import org.example.controller.security.AuthenticationRequest;
import org.example.controller.security.AuthenticationResponse;
import org.example.dto.UserDto;
import org.example.entity.Credentials;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.entity.enums.RoleName;
import org.example.entity.security.AuthorizationEntity;
import org.example.exception.entityNotFound.UserNotFoundException;
import org.example.repository.impl.UserRepository;
import org.example.utils.CustomMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    private final CustomMapper mapper;

    public AuthenticationResponse register(UserDto userDto) {
        String password = userDto.getCredentials().getPassword();
        OffsetDateTime offsetDateTime = OffsetDateTime.now().withNano(0);
        Role role = new Role();
        role.setName(RoleName.ROLE_USER);
        List<Role> roles = List.of(role);
        Credentials credentials = mapper.toEntity(Credentials.class, userDto.getCredentials());
        credentials.setPassword(hashPassword(offsetDateTime, password));
        var user = User.builder()
                .dateCreating(offsetDateTime)
                .nickname(userDto.getNickname())
                .credentials(credentials)
                .roles(roles)
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(new AuthorizationEntity(user));
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }


    public String hashPassword(OffsetDateTime offsetDateTime, String password) {
        String dateString = offsetDateTime.toString();
        String passwordWithDate = password + dateString;
        byte[] passwordBytes = passwordWithDate.getBytes(StandardCharsets.UTF_8);
        String hashedPassword = Base64.getEncoder().encodeToString(passwordBytes);
        return hashedPassword;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        String email = request.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("email " + email));
        OffsetDateTime dateCreating = user.getDateCreating();
        OffsetDateTime utcDateTime = dateCreating.withOffsetSameInstant(ZoneOffset.UTC);
        String outputDateTime = utcDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        String savedPassword = user.getCredentials().getPassword();
        String password = request.getPassword();
        String hashedPassword = hashPassword(OffsetDateTime.parse(outputDateTime), password);
        if (Objects.equals(savedPassword, hashedPassword)) {
            var token = jwtService.generateToken(new AuthorizationEntity(user));
            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        }
        else throw new BadCredentialsException("login failed");
    }
}
