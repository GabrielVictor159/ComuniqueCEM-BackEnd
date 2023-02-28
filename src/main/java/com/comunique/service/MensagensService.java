package com.comunique.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comunique.model.Chat;
import com.comunique.model.Mensagens;
import com.comunique.model.Usuarios;
import com.comunique.repository.MensagensRepository;

import jakarta.transaction.Transactional;

@Service
public class MensagensService {
    @Autowired
    MensagensRepository mensagensRepository;
    @Autowired
    ImageService imageService;
    private static String GlobalPath = "src/main/resources/static/images/";

    @Transactional
    public Mensagens Cadastrar(Mensagens mensagem) {
        return mensagensRepository.save(mensagem);
    }

    public Mensagens ultimaMensagemChat(Chat chat) {
        return mensagensRepository.findTopByChatOrderByDataMensagemDesc(chat);
    }

    public Optional<Mensagens> getMensagem(UUID idMensagem) {
        return mensagensRepository.findById(idMensagem);
    }

    public List<Mensagens> getAllMensagensForChat(Chat chat) {
        return mensagensRepository.findAllByChat(chat);
    }

    @Transactional
    public void Deletar(Mensagens mensagem) throws IOException {
        if (mensagem.getIsfile()) {
            try {
                imageService.excluir("", GlobalPath + extrairLink(mensagem.getMensagem()));
            } catch (Exception e) {

            }
        }
        mensagensRepository.deleteById(mensagem.getIdMensagens());
    }

    @Transactional
    public void deleteIn(List<Mensagens> mensagens) {
        List<UUID> listIds = new ArrayList<>();
        for (Mensagens mensagem : mensagens) {
            listIds.add(mensagem.getIdMensagens());
            try {
                if (mensagem.getIsfile()) {
                    imageService.excluir("", GlobalPath + extrairLink(mensagem.getMensagem()));
                }
            } catch (Exception e) {

            }
        }

        mensagensRepository.deleteByidMensagensIn(listIds);

    }

    @Transactional
    public void DeleteForChat(Chat chat) {
        List<Mensagens> listMessage = this.getAllMensagensForChat(chat);
        for (Mensagens mensagem : listMessage) {
            try {
                if (mensagem.getIsfile()) {
                    imageService.excluir("", GlobalPath + extrairLink(mensagem.getMensagem()));
                }
            } catch (Exception e) {

            }
        }
        mensagensRepository.deleteAllByChat(chat);
    }

    @Transactional
    public void confirmarLidaChat(Chat chat, Usuarios usuario) {
        mensagensRepository.usuarioLeuChat(chat, usuario.getIdUsuario());
    }

    public static String extrairLink(String str) {
        int inicio = str.indexOf("§") + 1;
        int fim = str.lastIndexOf("§");
        return str.substring(inicio, fim);
    }
}
