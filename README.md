
<div style="text-align: center">
    <h1>ROTAS</h1>
</div>
<h2>Rotas de administrador master</h2>

| HTTP Method | Endpoint | Parâmetros | Descrição | Retornos Possíveis |
|-------------|----------|------------|----------|--------------------|
| GET | `/AdminsMaster/{nome}/{senha}` | `@PathVariable String nome, @PathVariable String senha` | Permite o login de um adminMaster | `200 OK` com os dados do adminMaster, `401 UNAUTHORIZED`


<h2>Rotas de administrador</h2>

| HTTP Method | Endpoint | Parâmetros | Descrição | Retornos Possíveis |
|-------------|----------|------------|----------|--------------------|
| GET | `/Admins/{nome}/{senha}` | `@PathVariable String nome, @PathVariable String senha` | Esse método permite o login de um admin | `200 OK` com os dados do admin, `401 UNAUTHORIZED`
| GET | `/Admins/getAll/{nomeAdminMaster}/{senhaAdminMaster}` | `@PathVariable String nomeAdminMaster, @PathVariable String senhaAdminMaster` | Esse método permite que um adminMaster obtenha os dados de todos os admins | `200 OK` com os dados dos admins, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro
| GET | `/Admins/getOne/{nomeAdminMaster}/{senhaAdminMaster}/{id}` | `@PathVariable String nomeAdminMaster, @PathVariable String senhaAdminMaster, @PathVariable UUID id` | Esse método permite que um adminMaster obtenha os dados de um determinado admin pelo se id | `200 OK` com os dados do admin, `401 UNAUTHORIZED`, `404 NOT_FOUND`
| GET | `/Admins/getAllByInstituto/{nomeAdminMaster}/{senhaAdminMaster}/{idInstituicao}` | `@PathVariable String nomeAdminMaster, @PathVariable String senhaAdminMaster, @PathVariable UUID idInstituicao` | Permite que um adminMaster obtenha os dados de todos os admins de uma determinada instituição | `200 OK` com os dados dos admins, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro
| POST | `/Admins/{nomeAdminMaster}/{senhaAdminMaster}/{idInstituicao}` | `@RequestBody @Valid AdminsDTO dto, @PathVariable String nomeAdminMaster, @PathVariable String senhaAdminMaster, @PathVariable UUID idInstituicao` | Permite que um adminMaster cadastre um admin em uma instituição | `200 OK` com os dados cadastrados, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro, `404 NOT_FOUND`
| PUT | `/Admins/{nomeAdminMaster}/{senhaAdminMaster}/{id}` | `@RequestBody @Valid AdminsDTO dto, @PathVariable String nomeAdminMaster, @PathVariable String senhaAdminMaster, @PathVariable UUID id`| Permite que um adminMaster altere os dados de um determinado admin | `200 OK` com os dados alterados, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro, `404 NOT_FOUND`
| DELETE | `/Admins/{nomeAdminMaster}/{senhaAdminMaster}/{id}` | `@PathVariable String nomeAdminMaster, @PathVariable String senhaAdminMaster, @PathVariable UUID id` | Permite que um adminMaster exclua um determinado admin | `200 OK`, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro, `404 NOT_FOUND`


<h2>Rotas de instituiçoes</h2>

| HTTP Method | Endpoint | Parâmetros | Descrição | Retornos Possíveis |
|-------------|----------|------------|----------|--------------------|
| GET | `/Instituicoes/admin/{nomeAdmin}/{senhaAdmin}/{id}` | `@PathVariable String nomeAdmin, @PathVariable String senhaAdmin, @PathVariable UUID id` | Esse método possibilita que um admin obtenha os dados de uma instituição | `200 OK` com os dados da instituição, `401 UNAUTHORIZED`, `404 NOT_FOUND`
| GET | `/Instituicoes/adminMaster/{nomeAdmin}/{senhaAdmin}/{id}` | `@PathVariable String nomeAdmin, @PathVariable String senhaAdmin, @PathVariable UUID id` | Esse método possibilita que um adminMaster obtenha os dados de uma instituição | `200 OK` com os dados da instituição, `401 UNAUTHORIZED`, `404 NOT_FOUND`
| GET | `/Instituicoes/getAll/{nomeAdmin}/{senhaAdmin}/{id}` | `@PathVariable String nomeAdmin, @PathVariable String senhaAdmin` | Esse método possibilita que um adminMaster obtenha os dados de todas as instituições | `200 OK` com os dados das instituições, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro
| POST | `/Instituicoes/{nomeAdmin}/{senhaAdmin}` | `@PathVariable String nomeAdmin, @PathVariable String senhaAdmin, @RequestBody @Valid InstituicoesDTO instituicaoDto` | Esse método possibilita que um adminMaster cadastre uma instituição | `200 OK` com os dados da instituição cadastrados, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro
| PUT | `/Instituicoes/{nomeAdmin}/{senhaAdmin}/{id}` | `@PathVariable String nomeAdmin, n@PathVariable String senhaAdmin, @PathVariable UUID id, @RequestBody @Valid InstituicoesDTO instituicaoDto` | Esse método possiblita que um adminMaster altere os dados de uma instituição | `200 OK` com os dados alterados, `401 UNAUTHORIZED`, `404 NOT_FOUND`, `500 INTERNAL_SERVER_ERROR` com o texto do erro, `400 BAD_REQUEST`
| DELETE | `/Instituicoes/{nomeAdmin}/{senhaAdmin}/{id}` | `@PathVariable String nomeAdmin, @PathVariable String senhaAdmin, @PathVariable UUID id` | Esse método possibilita que um adminMaster exclua uma instituição, bem como todas as noticias, questões, usuarios, chats e mensagens relacionadas a ela | `200 OK`, `401 UNAUTHORIZED`, `404 NOT_FOUND`, `500 INTERNAL_SERVER_ERROR` com o texto do erro


<h2>Rotas de usuarios</h2>

| HTTP Method | Endpoint | Parâmetros | Descrição | Retornos Possíveis |
|-------------|----------|------------|----------|--------------------|
| GET | `/Usuarios/{email}/{senha}` | `@PathVariable String email, @PathVariable String senha` | Faz o login de um usuario com base no seu email e senha | `200 OK` com os dados do usuario, `401 UNAUTHORIZED`
| GET | `/Usuarios/getAllUsersIntituto/{emailUsuario}/{senhaUsuario}` | `@PathVariable String emailUsuario, @PathVariable String senhaUsuario` | Retorno todos os usuarios ao qual o usuario pertence | `200 OK` com a lista de usuarios, `401 UNAUTHORIZED`
| GET | `/Usuarios/getAllUsuariosInstitutoPaginado/{emailUsuario}/{senhaUsuario}` | `@PathVariable String emailUsuario, @PathVariable String senhaUsuario, @RequestParam(defaultValue = "0") Integer pagina, @RequestParam(defaultValue = "10") Integer tamanho` | Retorna todos os usuarios de uma instituição de forma paginada | `200 OK` com a lista de usuarios, `401 UNAUTHORIZED`
| GET | `/Usuarios/{id}` | `@PathVariable UUID id` | Retorna os dados de um determinado usuario apartir do seu id | `200 OK` com os dados do usuario, `401 NOT_FOUND`
| POST | `/Usuarios/{nomeInstituicao}/{senhaInstituicao}` | `@RequestBody @Valid UsuariosDTO usuarioDto, @PathVariable String nomeInstituicao, @PathVariable String senhaInstituicao` | Adiciona um usuario a uma determinada instituição | `200 OK` com o cadastro do usuario, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro, `400 BAD_REQUEST`
| PUT | `/Usuarios/admin/{nomeAdmin}/{senhaAdmin}/{id}` | `@RequestBody @Valid UsuariosDTO dto, @PathVariable String nomeAdmin, @PathVariable String senhaAdmin, @PathVariable UUID id` | Esse método possibilita que um admin possa alterar os dados de um usuario da sua instituição | `200 OK` com os dados alterados, `401 UNAUTHORIZED`, `404 NOT_FOUND`, `500 INTERNAL_SERVER_ERROR` com o texto do erro, `400 BAD_REQUEST`
| PUT | `/Usuarios/{emailUsuario}/{senhaUsuario}` | `@RequestBody @Valid UsuariosDTO dto, @PathVariable String emailUsuario, @PathVariable String senhaUsuario` | Esse método possibilita que um usuario altere seus dados menos o tipo do usuario | `200 OK` com os dados alterados, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro, `400 BAD_REQUEST`
| PUT | `/Usuarios/{emailUsuario}/{senhaUsuario}/{online}` | `@PathVariable boolean online, @PathVariable String emailUsuario, @PathVariable String senhaUsuario` | Esse método possibilita que o usuario altere a sua propriedade que informa se ele esta online ou não | `200 OK` com os dados alterados, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro
| DELETE | `/Usuarios/{emailUsuario}/{senhaUsuario}` | `@PathVariable String emailUsuario, @PathVariable String senhaUsuario` | Esse método possibilita que o usuario se exclua do sistema, bem como os cronogramas, chats e mensagens relacionadas a ele no sistema, todas as mensagens armazenadas internamente nos dispositivos não serão apagadas por esse método | `200 OK`, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro
| DELETE | `/Usuarios/admin/{emailUsuario}/{senhaUsuario}` | `@PathVariable String emailUsuario, @PathVariable String senhaUsuario` | Esse método possibilita que um admmin exclua do sistema um usuario, bem como os cronogramas, chats e mensagens relacionadas a ele no sistema, todas as mensagens armazenadas internamente nos dispositivos não serão apagadas por esse método | `200 OK`, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro



<h2>Rotas de questões</h2>

| HTTP Method | Endpoint | Parâmetros | Descrição | Retornos Possíveis |
|-------------|----------|------------|----------|--------------------|
| GET | `/Questoes/{id}` | `@PathVariable UUID id` | Retorna uma determinada questão através do seu id | `200 OK` com a questão, `404 NOT_FOUND`
| GET | `/Questoes/LimitRange/{idInstituicao}/{limit}` | `@PathVariable UUID idInstituicao, @PathVariable int limit` | Retorna questões de uma instituição de forma aleatoria e com um limite no numero de questões | `200 OK` com a lista de questões, `404 NOT_FOUND`
| GET | `/Questoes/getAll/{idInstituicao}` | `@PathVariable UUID idInstituicao` | Retorna todas as questões de uma determinada instituição | `200 OK` com a lista de questões, `204 NO_CONTENT`, `404 NOT_FOUND`
| POST | `/Questoes/{adminNome}/{senhaAdmin}` | `@PathVariable String adminNome, @PathVariable String senhaAdmin, @RequestBody @Valid QuestoesDTO questoesDto` | Adiciona uma questão na instituição ao qual o admin faz parte | `200 OK` com a questão adicionada, `401 UNAUTHORIZED`
| PUT | `/Questoes/{adminNome}/{adminSenha}/{id}` | `@PathVariable UUID id, @PathVariable String adminNome, @PathVariable String adminSenha, @RequestBody @Valid QuestoesDTO questoesDTO` | Altera uma determinada questão | `200 OK` com a questão alterada, `401 UNAUTHORIZED`, `404 NOT_FOUND`, `500 INTERNAL_SERVER_ERROR` com o texto do erro, `400 BAD_REQUEST`
| DELETE | `/Questoes/{adminNome}/{senhaAdmin}/{id}` | `@PathVariable String adminNome, @PathVariable String senhaAdmin, @PathVariable UUID id` | Exclui uma determinada questão | `200 OK`, `401 UNAUTHORIZED`, `404 NOT_FOUND`, `500 INTERNAL_SERVER_ERROR` com o texto do erro



<h2>Rotas de noticias</h2>

| HTTP Method | Endpoint | Parâmetros | Descrição | Retornos Possíveis |
|-------------|----------|------------|----------|--------------------|
| GET         | `/Noticias/{idInstituicao}/{index}/{size}`      | `@PathVariable int index, @PathVariable int size, @PathVariable UUID idInstituicao`   | Retorna uma lista paginada de notícias da instituição com o ID especificado | `200 OK` com a lista de noticias, `404 NOT_FOUND`                                                                                                      |
| GET         | `/Noticias/{id}`                                | `@PathVariable UUID id`                                 | Retorna a notícia com o ID especificado | `200 OK` com a noticia, `404 NOT_FOUND` |
| GET          | `/Noticias/getAll/{idInstituicao}` | `@PathVariable UUID idInstituicao` | Retorna todas as noticias de uma determinada instituição | `200 OK` com a lista das noticias, `404 NOT_FOUND`
| POST          | `/Noticias/{adminNome}/{senhaAdmin}` | `@PathVariable String adminNome, @PathVariable String senhaAdmin, @RequestBody @Valid NoticiasDTO noticiasDto` | Adiciona uma notcia a instituição a qual o admin esta vinculado | `200 OK` com a noticia adicionada, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro, `400 BAD_REQUEST`
| PUT          | `/Noticias/{adminNome}/{adminSenha}/{id}` | `@PathVariable UUID id, @PathVariable String adminNome, @PathVariable String adminSenha, @RequestBody @Valid NoticiasDTO noticiasDTO` | Altera uma determinada noticia através do seu id | `200 OK` com a noticia alterada, `401 UNAUTHORIZED`, `404 NOT_FOUND`, `500 INTERNAL_SERVER_ERROR` com o texto do erro, `400 BAD_REQUEST`
| DELETE           | `/Noticias/{adminNome}/{adminSenha}/{id}` | `@PathVariable UUID id, @PathVariable String adminNome, @PathVariable String adminSenha` | Exclui uma determinada noticia através do seu id | `200 OK`, `404 NOT_FOUND`, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro 

<h2>Rotas de cronograma</h2>

| HTTP Method | Endpoint | Parâmetros | Descrição | Retornos Possíveis |
|-------------|----------|------------|----------|--------------------|
| GET         | `/Cronograma/{emailUsuario}/{senhaUsuario}/{id}` | `@PathVariable UUID id, @PathVariable String emailUsuario, @PathVariable String senhaUsuario` | Encontra um cronograma de um determinado usuario | `200 OK` com o cronograma , `404 NOT_FOUND`, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro
| GET         | `/Cronograma/getAll/{emailUsuario}/{senhaUsuario}` | `@PathVariable String emailUsuario, @PathVariable String senhaUsuario` | Obtém todos os cronogramas de um determinado usuario | `200 OK` com uma lista dos cronogramas, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com a mensagem do erro
| POST         | `/Cronograma/{emailUsuario}/{senhaUsuario}` | `@PathVariable String emailUsuario, @PathVariable String senhaUsuario, @RequestBody @Valid CronogramaDTO dto` | Cadastra um cronogramma relacionado a algum usuario | `200 OK` com o cronograma cadastrado, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro, `400 BAD_REQUEST`
| PUT         | `/Cronograma/{emailUsuario}/{senhaUsuario}/{id}` | `@PathVariable String emailUsuario, @PathVariable String senhaUsuario, @PathVariable UUID id, @RequestBody @Valid CronogramaDTO dto` | Altera um determinado cronograma | `200 OK` com o cronograma atualizado, `401 UNAUTHORIZED`, `404 NOT_FOUND`, `500 INTERNAL_SERVER_ERROR` com a mensagem do erro, `400 BAD_REQUEST`
| DELETE         | `/Cronograma/{emailUsuario}/{senhaUsuario}/{id}` | `(@PathVariable String emailUsuario, @PathVariable String senhaUsuario, @PathVariable UUID id` | Deleta um determinado cronograma | `200 OK`, `401 UNAUTHORIZED`, `404 NOT_FOUND`, `500 INTERNAL_SERVER_ERROR` com o texto do erro
| DELETE         | `/Cronograma/{emailUsuario}/{senhaUsuario}` | `(@PathVariable String emailUsuario, @PathVariable String senhaUsuario` | Deleta todos os cronogramas de um usuario | `200 OK`, `401 UNAUTHORIZED`, `500 INTERNAL_SERVER_ERROR` com o texto do erro


<h2>Rotas de chat</h2>

HTTP Method | Endpoint | Parâmetros | Descrição | Retornos Possíveis
------------|----------|------------|-----------|------------------
GET | `/Chat/{emailUsuario}/{senhaUsuario}/{id}` | `@PathVariable String emailUsuario`, `@PathVariable String senhaUsuario`, `@PathVariable UUID id` | Retorna um chat específico. | `OK`, `UNAUTHORIZED`, `NOT_FOUND`
GET | `/Chat/getAll/{emailUsuario}/{senhaUsuario}` | `@PathVariable String emailUsuario`, `@PathVariable String senhaUsuario` | Retorna uma lista de todos os chats do usuário logado. | `OK`, `UNAUTHORIZED`
POST | `/Chat/{emailUsuario}/{senhaUsuario}/{idUsuario}` | `@PathVariable String emailUsuario`, `@PathVariable String senhaUsuario`, `@PathVariable UUID idUsuario` | Cadastra um novo chat entre o usuário logado e o usuário passado como parâmetro. | `OK`, `UNAUTHORIZED`, `NOT_FOUND`, `CONFLICT`
DELETE | `/Chat/{emailUsuario}/{senhaUsuario}/{id}` | `@PathVariable String emailUsuario`, `@PathVariable String senhaUsuario`, `@PathVariable UUID id` | Deleta um chat específico do usuário logado. | `OK`, `UNAUTHORIZED`, `NOT_FOUND`

<h2>Rotas de mensagens</h2>

| HTTP Method | Endpoint                                                       | Parâmetros                                                                                                                | Descrição                                                                                    | Retornos Possíveis                                                  |
| ----------- | -------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- | -------------------------------------------------------------------- |
| GET         | `/Mensagens/{id}`                                                        | `@PathVariable UUID id`                                                                                                    | Obtém uma única mensagem pelo seu ID                                                        | `200 OK` com a mensagem ou `404 Not Found`                           |
| GET         | `/Mensagens/getAllChat/{emailUsuario}/{senhaUsuario}/{id}`                | `@PathVariable String emailUsuario`, `@PathVariable String senhaUsuario`, `@PathVariable UUID id`                          | Obtém todas as mensagens de um chat específico                                                | `200 OK` com uma lista de mensagens ou `401 Unauthorized` ou `404 Not Found` |
| POST        | `/Mensagens/{emailUsuario}/{senhaUsuario}/{idChat}`                        | `@PathVariable String emailUsuario`, `@PathVariable String senhaUsuario`, `@PathVariable UUID idChat`, `@RequestBody @Valid MensagensDTO dto` | Adiciona uma mensagem a um chat específico                                                   | `200 OK` com a mensagem recém-adicionada ou `401 Unauthorized` ou `404 Not Found` ou  `400 BAD_REQUEST` |
| PUT         | `/Mensagens/confirmarEntrega/{emailUsuario}/{senhaUsuario}/{id}`           | `@PathVariable String emailUsuario`, `@PathVariable String senhaUsuario`, `@PathVariable UUID id`                         | Marca uma mensagem como entregue                                                              | `200 OK` com a mensagem atualizada ou `401 Unauthorized` ou `404 Not Found` |
| PUT         | `/Mensagens/confirmarLida/{emailUsuario}/{senhaUsuario}/{id}`              | `@PathVariable String emailUsuario`, `@PathVariable String senhaUsuario`, `@PathVariable UUID id`                         | Marca uma mensagem como lida                                                                  | `200 OK` com a mensagem atualizada ou `401 Unauthorized` ou `404 Not Found` |
| PUT         | `/Mensagens/confirmarLidaChat/{emailUsuario}/{senhaUsuario}/{id}`          | `@PathVariable String emailUsuario`, `@PathVariable String senhaUsuario`, `@PathVariable UUID id`                         | Marca todas as mensagens de um chat como lidas                                                | `200 OK` com o chat atualizado ou `401 Unauthorized` ou `404 Not Found` |
| POST        | `/Mensagens/DeletarPedido`                                               | `@RequestBody @Valid Mensagens mensagem`                                                                                   | Marca uma mensagem como deletada, não entregue e não lida                                      | `200 OK` com a mensagem recém-adicionada ou `500 Internal Server Error` ou `400 BAD_REQUEST` |
| DELETE      | `/Mensagens/DeletarConfirmar/{id}`                                        | `@PathVariable UUID id`                                                                                                    | Deleta uma mensagem                                                                           | `200 OK` ou `404 Not Found` ou `500 Internal Server Error`            |

