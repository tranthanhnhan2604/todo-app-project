package com.nhantran.todoapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",
                joinColumns = @JoinColumn(name = "users_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role = new HashSet<>();

    //Custom user details
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        role.forEach(r -> authorities.add(new SimpleGrantedAuthority(r.getName().name())));
        return List.of(new SimpleGrantedAuthority(authorities.toString()));
    }

    public Integer getUserId() {
        return id;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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

