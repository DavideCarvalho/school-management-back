package com.growth.schoolmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCredentials {

    @Id
    @Column(name = "client_id")
    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "authority_client_credentials",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities;

}
