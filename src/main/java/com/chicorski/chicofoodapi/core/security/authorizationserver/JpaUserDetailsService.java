package com.chicorski.chicofoodapi.core.security.authorizationserver;

import com.chicorski.chicofoodapi.domain.model.Usuario;
import com.chicorski.chicofoodapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail informado"));

        return new AuthUser(usuario, getAuthorities(usuario));
    }

    private Collection<GrantedAuthority> getAuthorities(Usuario usuario) {
        return usuario.getGrupos()
                .stream()
                .flatMap(grupo -> grupo.getPermissoes().stream())
                .map(permissao -> new SimpleGrantedAuthority(permissao.getNome().toUpperCase(Locale.ROOT)))
                .collect(Collectors.toSet());
    }
}
