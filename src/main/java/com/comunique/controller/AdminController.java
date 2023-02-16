package com.comunique.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comunique.dto.AdminsDTO;
import com.comunique.model.Admins;
import com.comunique.model.AdminsMaster;
import com.comunique.model.Instituicoes;
import com.comunique.service.AdminsMasterService;
import com.comunique.service.AdminsService;
import com.comunique.service.InstituicoesService;

@RestController
@RequestMapping(value = "/Admins", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin
public class AdminController {
    @Autowired
    AdminsService adminsService;
    @Autowired
    AdminsMasterService adminsMasterService;
    @Autowired
    InstituicoesService instituicoesService;

    @GetMapping("/{nome}/{senha}")
    public ResponseEntity<Object> getAdmin(@PathVariable String nome, @PathVariable String senha) {
        Optional<Admins> admin = adminsService.Login(nome, senha);
        if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(admin.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/getAll/{nomeAdminMaster}/{senhaAdminMaster}")
    public ResponseEntity<Object> getAll(@PathVariable String nomeAdminMaster,
            @PathVariable String senhaAdminMaster) {
        Optional<AdminsMaster> admin = adminsMasterService.Login(nomeAdminMaster, senhaAdminMaster);
        if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            try {
                List<Admins> listAdmins = adminsService.getAll();
                return new ResponseEntity<>(listAdmins, HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/getOne/{nomeAdminMaster}/{senhaAdminMaster}/{id}")
    public ResponseEntity<Object> getOne(@PathVariable String nomeAdminMaster,
            @PathVariable String senhaAdminMaster, @PathVariable UUID id) {
        Optional<AdminsMaster> adminMaster = adminsMasterService.Login(nomeAdminMaster, senhaAdminMaster);
        Optional<Admins> admin = adminsService.getOne(id);
        if (adminMaster.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(admin.get(), HttpStatus.OK);
        }

    }

    @GetMapping("/getAllByInstituto/{nomeAdminMaster}/{senhaAdminMaster}/{idInstituicao}")
    public ResponseEntity<Object> getAllByInstituto(@PathVariable String nomeAdminMaster,
            @PathVariable String senhaAdminMaster, @PathVariable UUID idInstituicao) {
        Optional<AdminsMaster> admin = adminsMasterService.Login(nomeAdminMaster, senhaAdminMaster);
        Optional<Instituicoes> instituicao = instituicoesService.getInstituicao(idInstituicao);
        if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (instituicao.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                List<Admins> listAdmins = adminsService.getAllByInstituicao(instituicao.get());
                return new ResponseEntity<>(listAdmins, HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/{nomeAdminMaster}/{senhaAdminMaster}/{idInstituicao}")
    public ResponseEntity<Object> adicionar(@PathVariable String nomeAdminMaster,
            @PathVariable String senhaAdminMaster, @PathVariable UUID idInstituicao,
            @RequestBody @Valid AdminsDTO dto) {
        Optional<AdminsMaster> adminMaster = adminsMasterService.Login(nomeAdminMaster, senhaAdminMaster);
        Optional<Instituicoes> instituicao = instituicoesService.getInstituicao(idInstituicao);
        if (adminMaster.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (instituicao.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                Admins admin = new Admins();
                BeanUtils.copyProperties(dto, admin);
                admin.setInstituicao(instituicao.get());
                Admins cadastro = adminsService.Cadastrar(admin);
                return new ResponseEntity<>(cadastro, HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/{nomeAdminMaster}/{senhaAdminMaster}/{id}")
    public ResponseEntity<Object> alterar(@PathVariable String nomeAdminMaster,
            @PathVariable String senhaAdminMaster, @PathVariable UUID id,
            @RequestBody @Valid AdminsDTO dto) {
        Optional<AdminsMaster> adminMaster = adminsMasterService.Login(nomeAdminMaster, senhaAdminMaster);
        Optional<Admins> admin = adminsService.getOne(id);
        if (adminMaster.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                BeanUtils.copyProperties(dto, admin.get());
                Admins cadastro = adminsService.Cadastrar(admin.get());
                return new ResponseEntity<>(cadastro, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @DeleteMapping("/{adminNome}/{senhaAdmin}/{id}")
    public ResponseEntity<Object> deletar(@PathVariable String nomeAdminMaster,
            @PathVariable String senhaAdminMaster, @PathVariable UUID id,
            @RequestBody @Valid AdminsDTO dto) {
        Optional<AdminsMaster> adminMaster = adminsMasterService.Login(nomeAdminMaster, senhaAdminMaster);
        Optional<Admins> admin = adminsService.getOne(id);
        if (adminMaster.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else if (admin.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                adminsService.Deletar(admin.get().getIdAdmin());
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }
}
