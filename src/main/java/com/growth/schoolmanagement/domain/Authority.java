package com.growth.schoolmanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @Column(name = "authority_id")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private Collection<ClientCredentials> clientCredentials;
}
