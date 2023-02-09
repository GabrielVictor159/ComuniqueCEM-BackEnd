package com.comunique.service;

import com.comunique.model.Cronograma;
import com.comunique.model.Usuarios;
import com.comunique.repository.CronogramaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CronogramaService {

    @Autowired
    CronogramaRepository cronogramaRepository;

    @Transactional
    public Cronograma Cadastrar(Cronograma cronograma){
       return cronogramaRepository.save(cronograma);
    }

    public Optional<Cronograma> getCronograma(UUID idCronograma){
        return  cronogramaRepository.findById(idCronograma);
    }

    public List<Cronograma> getCronogramaUser(Usuarios usuario){
        return cronogramaRepository.findByUsuario(usuario);
    }

    @Transactional
    public void Deletar(UUID idCronograma){
        cronogramaRepository.deleteById(idCronograma);
    }
}
