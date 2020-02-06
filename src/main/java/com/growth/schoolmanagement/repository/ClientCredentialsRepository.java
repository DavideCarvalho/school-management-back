package com.growth.schoolmanagement.repository;

import com.growth.schoolmanagement.domain.ClientCredentials;
import com.growth.schoolmanagement.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientCredentialsRepository extends JpaRepository<ClientCredentials, Long> {

    Optional<ClientCredentials> findByClientId(String clientId);
}
