package com.growth.schoolmanagement.service;

import com.growth.schoolmanagement.domain.User;
import com.growth.schoolmanagement.dto.CustomGrantedAuthorityDTO;
import com.growth.schoolmanagement.dto.CustomUserDetailsDTO;
import com.growth.schoolmanagement.dto.UserDTO;
import com.growth.schoolmanagement.mapper.UserMapper;
import com.growth.schoolmanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__({@Lazy}))
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserMapper mapper = UserMapper.INSTANCE;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Optional<User> optional = this.repository.findByUsername(username);

        if (optional.isEmpty()) {
            this.repository.save(
                    User.builder()
                            .name(username)
                            .password("secret")
                            .enabled(true)
                            .username(username)
                            .build()

            );
        }

        Optional<User> optionalUser = this.repository.findByUsername(username);

        optionalUser.orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });

        User user = optionalUser.get();

        Collection<CustomGrantedAuthorityDTO> authorityCollection = new ArrayList<>();

        user.getAuthorities().forEach((authority) -> {
            authorityCollection.add(
                    CustomGrantedAuthorityDTO
                            .builder()
                            .authority(authority.getName())
                            .build()
            );
        });

        return CustomUserDetailsDTO
                .builder()
                .password(this.passwordEncoder.encode(user.getPassword()))
                .username(user.getUsername())
                .enabled(user.isEnabled())
                .authorities(authorityCollection)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
    }

    public List<UserDTO> findAll() {
        return mapper.toDTOList(this.repository.findAll());
    }
}
