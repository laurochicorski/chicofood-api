package com.chicorski.chicofoodapi.core.security.authorizationserver;

import com.chicorski.chicofoodapi.domain.model.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthUser extends User {

    private static final long serialVersionUID = 1l;

    private Long userId;
    private String fullName;

    public AuthUser(Usuario usuario, Collection<? extends GrantedAuthority> grantedAuthorities) {
        super(usuario.getEmail(), usuario.getSenha(), grantedAuthorities);

        this.userId = usuario.getId();
        this.fullName = usuario.getNome();
    }
}
