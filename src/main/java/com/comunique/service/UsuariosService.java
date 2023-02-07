package com.comunique.service;

import com.comunique.model.Usuarios;
import com.comunique.repository.UsuariosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuariosService {
	@Autowired
    UsuariosRepository usuariosRepository;
	@Transactional
	public Usuarios Cadastrar(Usuarios usuario){
			return usuariosRepository.save(usuario);
	}
	public Optional<Usuarios> getUser(UUID idUsuario){
		return usuariosRepository.findById(idUsuario);
	}
	public List<Usuarios> getAllUsers(){
		return usuariosRepository.findAll();
	}

	@Transactional
	public void Deletar(Usuarios usuarios){
		usuariosRepository.delete(usuarios);
	}



}
