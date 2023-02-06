package com.comunique.comunique.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunique.comunique.repository.UsuariosRepository;

@Service
public class UsuariosService {
	@Autowired
	UsuariosRepository usuariosRepository;
	
	
	
}
