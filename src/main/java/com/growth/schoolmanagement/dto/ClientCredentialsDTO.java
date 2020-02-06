package com.growth.schoolmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.Id;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientCredentialsDTO implements ClientDetails {

    private String clientId;

    private Set<String> resourceIds;

    @Builder.Default
    private boolean secretRequired = true;

    private String clientSecret;

    @Builder.Default
    private boolean scoped = true;

    private Set<String> scope;

    private Set<String> authorizedGrantTypes;

    private Set<String> registeredRedirectUri;

    private Collection<GrantedAuthority> authorities;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private Map<String, Object> additionalInformation;

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }
}
