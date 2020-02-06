package com.growth.schoolmanagement.dto;

import com.growth.schoolmanagement.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private String username;

    private String password;

    private boolean enabled;

    private Set<Authority> authorities;

}
