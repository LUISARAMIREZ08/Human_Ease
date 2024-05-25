package com.example.server.entity;

import com.example.server.entity.enums.RoleUser;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails{
    @Id
    private Long cardId;

    @Column(name="user_name", unique = true, nullable = false, length = 100)
    private String username;

    @Column(name="name", nullable = false, length = 100)
    private String name;

    @Column(name="last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name="birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name="address", nullable = false, length = 100)
    private String address;

    @Column(name="phone", unique = true,length = 20)
    private String phone;

    @Column(name="email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name="password", nullable = false, length = 100)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((roleUser.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

}

