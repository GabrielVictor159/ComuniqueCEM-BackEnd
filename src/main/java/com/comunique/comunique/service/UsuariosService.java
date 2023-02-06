package com.comunique.comunique.service;

import com.comunique.comunique.model.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunique.comunique.repository.UsuariosRepository;

@Service
public class UsuariosService {
	@Autowired
	UsuariosRepository usuariosRepository;
	
	public String Cadastrar(Usuarios usuario){
		try {
			usuariosRepository.save(usuario);
			return "Sucesso";
		}
		catch(Exception e) {
			return "Error";
		}
	}



}
