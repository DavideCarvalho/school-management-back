package com.growth.schoolmanagement.service;

import com.growth.schoolmanagement.domain.ClientCredentials;
import com.growth.schoolmanagement.dto.ClientCredentialsDTO;
import com.growth.schoolmanagement.repository.ClientCredentialsRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor(onConstructor = @__({@Lazy}))
public class ClientCredentialsService implements ClientDetailsService {

    private final ClientCredentialsRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;

    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

        final Optional<ClientCredentials> optional = this.repository.findByClientId(clientId);

        if (optional.isEmpty()) {
            this.repository.save(
                    ClientCredentials.builder()
                            .clientId("school-management@client")
                            .clientSecret("secret")
                            .accessTokenValiditySeconds(30000)
                            .refreshTokenValiditySeconds(30000)
                            .authorizedGrantTypes("authorization_code,refresh_token,implicit,password,client_credentials")
                            .resourceIds("restservice")
                            .scope("read,write")
                            .build()

            );
        }

        final Optional<ClientCredentials> optionalClientCredentials = this.repository.findByClientId(clientId);

        optionalClientCredentials.orElseThrow(() -> {
            throw new ClientRegistrationException("Not Found");
        });

        final ClientCredentials clientCredentials = optionalClientCredentials.get();

        Collection<GrantedAuthority> authorityCollection = new ArrayList<>();

        clientCredentials.getAuthorities().forEach((authority) -> authorityCollection.add(
                new SimpleGrantedAuthority(authority.getName())
        ));

        Set<String> grantTypes = new HashSet<>(Arrays.asList(clientCredentials.getAuthorizedGrantTypes().split(",")));

        Set<String> resources = new HashSet<>(Arrays.asList(clientCredentials.getResourceIds().split(",")));

        Set<String> scopes = new HashSet<>(Arrays.asList(clientCredentials.getScope().split(",")));

        return ClientCredentialsDTO
                .builder()
                .accessTokenValiditySeconds(clientCredentials.getAccessTokenValiditySeconds())
                .authorities(authorityCollection)
                .clientId(clientCredentials.getClientId())
                .secretRequired(true)
                .clientSecret(this.passwordEncoder.encode(clientCredentials.getClientSecret()))
                .refreshTokenValiditySeconds(clientCredentials.getRefreshTokenValiditySeconds())
                .authorizedGrantTypes(grantTypes)
                .resourceIds(resources)
                .scope(scopes)
                .build();
    }
}
