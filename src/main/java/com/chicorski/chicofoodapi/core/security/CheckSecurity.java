package com.chicorski.chicofoodapi.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

    public @interface Cozinhas {
        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditar {
        }

    }

    public @interface Restaurantes {

        @PreAuthorize("@chicoFoodSecurity.podeGerenciarCadastroRestaurantes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeGerenciarCadastro { }

        @PreAuthorize("@chicoFoodSecurity.podeGerenciarFuncionamentoRestaurantes(#restauranteId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeGerenciarFuncionamento { }

        @PreAuthorize("@chicoFoodSecurity.podeConsultarRestaurantes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar { }

    }

    public @interface Pedidos {

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @PostAuthorize("hasAuthority('CONSULTAR_PEDIDOS') or " +
                "@chicoFoodSecurity.usuarioAutenticadoIgual(returnObject.cliente.id) or" +
                "@chicoFoodSecurity.gerenciaRestaurante(returnObject.restaurante.id)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeBuscar { }

        @PreAuthorize("@chicoFoodSecurity.podePesquisarPedidos(#filtro.clienteId, #filtro.restauranteId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodePesquisar { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeCriar { }

        @PreAuthorize("@chicoFoodSecurity.podeGerenciarPedidos(#codigoPedido)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeGerenciarPedidos {
        }
    }

    public @interface FormasPagamento {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_FORMAS_PAGAMENTO')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditar { }

        @PreAuthorize("@chicoFoodSecurity.podeConsultarFormasPagamento()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar { }

    }

    public @interface Cidades {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_CIDADES')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditar { }

        @PreAuthorize("@chicoFoodSecurity.podeConsultarCidades()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar { }

    }

    public @interface Estados {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_ESTADOS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditar { }

        @PreAuthorize("@chicoFoodSecurity.podeConsultarEstados()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar { }

    }

    public @interface UsuariosGruposPermissoes {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and "
                + "@chicoFoodSecurity.usuarioAutenticadoIgual(#usuarioId)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarPropriaSenha { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
                + "@chicoFoodSecurity.usuarioAutenticadoIgual(#usuarioId))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeAlterarUsuario { }

        @PreAuthorize("@chicoFoodSecurity.podeEditarUsuariosGruposPermissoes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeEditar { }


        @PreAuthorize("@chicoFoodSecurity.podeConsultarUsuariosGruposPermissoes()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar { }

    }

    public @interface Estatisticas {

        @PreAuthorize("@chicoFoodSecurity.podeConsultarEstatisticas()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface PodeConsultar { }

    }

}
