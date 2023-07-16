package com.edtech.qnotemate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private UUID userUuid;

    // Parameterized constructor
    public User(Long id, String firstName, String lastName, String email, String username, String password, UUID userUuid) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = username;
        this.password = password;
        this.userUuid = userUuid;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Assign a generic authority to all users
        // Later we will create a subscription based role-model
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return authorities;
    }

}
