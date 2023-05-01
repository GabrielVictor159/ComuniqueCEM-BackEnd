package com.comunique.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comunique.model.Usuarios;
import com.comunique.model.UsuariosSolicitacoes;
import com.comunique.service.UsuariosService;
import com.comunique.service.UsuariosSolicitacoesService;

@Controller
@RequestMapping(value = "/Pages")
@CrossOrigin
public class PagesController {

    @Autowired
    UsuariosSolicitacoesService usuariosSolicitacoesService;
    @Autowired
    UsuariosService usuariosService;

    @GetMapping("/confirmarCadastro/{id}")
    public String confirmarCadastro(@PathVariable(value = "id") UUID id) {
        Optional<UsuariosSolicitacoes> solicitacao = usuariosSolicitacoesService.buscarId(id);
        if (solicitacao.isEmpty()) {
            return "CadastroInvalido";
        } else {
            try {
                Usuarios user = new Usuarios();
                BeanUtils.copyProperties(solicitacao.get(), user, "senhaProvisoria");
                Usuarios userCadastro = usuariosService.Cadastrar(user);
                System.out.println(userCadastro);
                usuariosSolicitacoesService.Deletar(id);
                return "CadastroSucesso";
            } catch (Exception e) {
                return "CadastroFalhou";
            }
        }
    }
}
