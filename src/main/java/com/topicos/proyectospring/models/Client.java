package com.topicos.proyectospring.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "clients", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class Client implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username; // Se usa para autenticación

    @Column(unique = true, nullable = false)
    private String email; // Se mantiene, pero no se usa para autenticación

    private String name;
    private String lastName;

    @Column(nullable = false)
    private String password;

    private Long phone;

    @Column(nullable = false)
    private String role; // "ROLE_USER" o "ROLE_ADMIN"

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PayMethod> payMethods = new ArrayList<>();

    // Constructores
    public Client() {}

    public Client(String username, String email, String name, String lastName, String password, Long phone, String role) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    // Implementación de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() { // Ahora usa username en lugar de email
        return username;
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

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getUsernameField() { // Método extra para obtener el username
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<PayMethod> getPayMethods() {
        return payMethods;
    }

    public void setPayMethods(List<PayMethod> payMethods) {
        this.payMethods = payMethods;
    }
}
