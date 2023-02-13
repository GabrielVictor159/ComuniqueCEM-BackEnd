package com.comunique.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.comunique.dto.AdminsDTO;
import com.comunique.dto.AdminsMasterDTO;
import com.comunique.dto.ChatDTO;
import com.comunique.dto.CronogramaDTO;
import com.comunique.dto.InstituicoesDTO;
import com.comunique.dto.MensagensDTO;
import com.comunique.dto.NoticiasDTO;
import com.comunique.dto.QuestoesDTO;
import com.comunique.dto.UsuariosDTO;
import com.comunique.model.Admins;
import com.comunique.model.AdminsMaster;
import com.comunique.model.Chat;
import com.comunique.model.Cronograma;
import com.comunique.model.Instituicoes;
import com.comunique.model.Mensagens;
import com.comunique.model.Noticias;
import com.comunique.model.Questoes;
import com.comunique.model.Usuarios;
import com.comunique.service.AdminsMasterService;
import com.comunique.service.AdminsService;
import com.comunique.service.ChatService;
import com.comunique.service.CronogramaService;
import com.comunique.service.InstituicoesService;
import com.comunique.service.MensagensService;
import com.comunique.service.NoticiasService;
import com.comunique.service.QuestoesService;
import com.comunique.service.UsuariosService;

public class ModelCadastrosTests {

    public static Usuarios CadastrarUsuario(Instituicoes instituicao, UsuariosService usuariosService) {

        UsuariosDTO userDto = new UsuariosDTO(AleatoryString.getAlphaNumericString(7),
                AleatoryString.getAlphaNumericString(7), AleatoryString.getAlphaNumericString(7),
                AleatoryString.getAlphaNumericString(7),
                AleatoryString.getAlphaNumericString(7), AleatoryString.getAlphaNumericString(7), true);

        Usuarios user = new Usuarios();
        BeanUtils.copyProperties(userDto, user);
        user.setInstituicao(instituicao);
        return usuariosService.Cadastrar(user);

    }

    public static Questoes cadastrarQuestao(Instituicoes instituicao, QuestoesService questoesService) {
        String RespostaCorreta = AleatoryString.getAlphaNumericString(7);
        QuestoesDTO questao = new QuestoesDTO(AleatoryString.getAlphaNumericString(7),
                RespostaCorreta, AleatoryString.getAlphaNumericString(7),
                AleatoryString.getAlphaNumericString(7), AleatoryString.getAlphaNumericString(7), RespostaCorreta);
        Questoes a = new Questoes();
        BeanUtils.copyProperties(questao, a);
        a.setInstituicao(instituicao);
        return questoesService.Cadastrar(a);
    }

    public static Chat CadastrarChat(Usuarios user1, Usuarios user2, ChatService chatService) {
        ChatDTO dto = new ChatDTO(user1, user2);
        Chat chat = new Chat();
        BeanUtils.copyProperties(dto, chat);
        return chatService.Cadastrar(chat);

    }

    public static Noticias CadastrarNoticia(Instituicoes instituicao, NoticiasService noticiasService) {
        NoticiasDTO a = new NoticiasDTO(AleatoryString.getAlphaNumericString(7),
                AleatoryString.getAlphaNumericString(7), AleatoryString.getAlphaNumericString(7));
        Noticias b = new Noticias();
        BeanUtils.copyProperties(a, b);
        b.setInstituicao(instituicao);
        return noticiasService.Cadastrar(b);
    }

    public static Cronograma CadastrarCronograma(Usuarios user, CronogramaService cronogramaService)
            throws ParseException {
        String dateString = "2023-02-09";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);
        CronogramaDTO cronogramaDto = new CronogramaDTO(date, "blue", 30, "dasdas", user);
        Cronograma cronograma = new Cronograma();
        BeanUtils.copyProperties(cronogramaDto, cronograma);
        return cronogramaService.Cadastrar(cronograma);
    }

    public static Mensagens CadastrarMensagem(Chat chat, UUID idUsuario, MensagensService mensagensService) {
        MensagensDTO mensagemDto = new MensagensDTO(idUsuario, AleatoryString.getAlphaNumericString(7), chat);
        Mensagens mensagem = new Mensagens();
        BeanUtils.copyProperties(mensagemDto, mensagem);
        return mensagensService.Cadastrar(mensagem);

    }

    public static Instituicoes CadastarInstituicoes(InstituicoesService instituicoesService) {
        InstituicoesDTO dto = new InstituicoesDTO(AleatoryString.getAlphaNumericString(7),
                AleatoryString.getAlphaNumericString(7), AleatoryString.getAlphaNumericString(7));
        Instituicoes instituicao = new Instituicoes();
        BeanUtils.copyProperties(dto, instituicao);
        return instituicoesService.Cadastrar(instituicao);
    }

    public static Admins CadastrarAdmin(Instituicoes instituicao, AdminsService adminsService) {
        AdminsDTO dto = new AdminsDTO(AleatoryString.getAlphaNumericString(7), AleatoryString.getAlphaNumericString(7));
        Admins admin = new Admins();
        BeanUtils.copyProperties(dto, admin);
        admin.setInstituicao(instituicao);
        return adminsService.Cadastrar(admin);
    }

    public static AdminsMaster CadastrarAdminMaster(AdminsMasterService adminsMasterService) {
        AdminsMasterDTO dto = new AdminsMasterDTO(AleatoryString.getAlphaNumericString(7),
                AleatoryString.getAlphaNumericString(7));
        AdminsMaster adminMaster = new AdminsMaster();
        BeanUtils.copyProperties(dto, adminMaster);
        return adminsMasterService.Cadastrar(adminMaster);
    }

}
