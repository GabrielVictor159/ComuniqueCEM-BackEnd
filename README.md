<h2>Rotas de administrador master</h2>

| Método | Rota | Descrição |
| --- | --- | --- |
| GET | /AdminsMaster/{nome}/{senha} | Verifica as credenciais de login do administrador. Retorna as informações do administrador se as credenciais estiverem corretas ou retorna um status HTTP 401 (Unauthorized) se as credenciais estiverem incorretas. |


<h2>Rotas de administrador</h2>

| Método HTTP | Rota | Descrição |
| --- | --- | --- |
| GET | /Admins/{nome}/{senha} | Retorna um único administrador com nome de usuário nome e senha senha. Se o administrador não for encontrado, a resposta é 401 Unauthorized. |
| GET | /Admins/getAll/{nomeAdminMaster}/{senhaAdminMaster} | Retorna uma lista de todos os administradores. A solicitação deve ser feita com credenciais de administrador mestre válidas (com nome de usuário nomeAdminMaster e senha senhaAdminMaster). Se as credenciais não forem válidas, a resposta é 401 Unauthorized. |
| GET | /Admins/getOne/{nomeAdminMaster}/{senhaAdminMaster}/{id} | Retorna um único administrador com ID id. A solicitação deve ser feita com credenciais de administrador mestre válidas. Se as credenciais não forem válidas, a resposta é 401 Unauthorized. Se o administrador não for encontrado, a resposta é 404 Not Found. |
| GET | /Admins/getAllByInstituto/{nomeAdminMaster}/{senhaAdminMaster}/{idInstituicao} | Retorna uma lista de todos os administradores que pertencem à instituição com ID idInstituicao. A solicitação deve ser feita com credenciais de administrador mestre válidas. Se as credenciais não forem válidas, a resposta é 401 Unauthorized. Se a instituição não for encontrada, a resposta é 404 Not Found. |
| POST | /Admins/{nomeAdminMaster}/{senhaAdminMaster}/{idInstituicao} | Cria um novo administrador e o associa à instituição com ID idInstituicao. A solicitação deve ser feita com credenciais de administrador mestre válidas. Se as credenciais não forem válidas, a resposta é 401 Unauthorized. Se a instituição não for encontrada, a resposta é 404 Not Found. Se houver um erro ao criar o administrador, a resposta é 500 Internal Server Error. |
| PUT | /Admins/{nomeAdminMaster}/{senhaAdminMaster}/{id} | Atualiza um administrador existente com ID id. A solicitação deve ser feita com credenciais de administrador mestre válidas. Se as credenciais não forem válidas, a resposta é 401 Unauthorized. Se o administrador não for encontrado, a resposta é 404 Not Found. Se houver um erro ao atualizar o administrador, a resposta é 500 Internal Server Error. |
| DELETE | /Admins/{nomeAdminMaster}/{senhaAdminMaster}/{id} | Exclui um administrador existente com ID id. A solicitação deve ser feita com credenciais de administrador mestre válidas. Se as credenciais não forem válidas, a resposta é 401 Unauthorized. Se o administrador não for encontrado, a resposta é 404 Not Found. Se houver um erro ao excluir o administrador, a resposta é 500 Internal Server Error. |


<h2>Rotas de instituiçoes</h2>

| HTTP Method | Endpoint | Parâmetros | Descrição |
| ----------- | -------- | ---------- | --------- |
| GET | /Instituicoes/admin/{nomeAdmin}/{senhaAdmin}/{id} | nomeAdmin (String), senhaAdmin (String), id (UUID) | Retorna uma instituição específica a partir do ID informado. |
| GET | /Instituicoes/adminMaster/{nomeAdmin}/{senhaAdmin}/{id} | nomeAdmin (String), senhaAdmin (String), id (UUID) | Retorna uma instituição específica a partir do ID informado para administradores mestres. |
| GET | /Instituicoes/getAll/{nomeAdmin}/{senhaAdmin} | nomeAdmin (String), senhaAdmin (String) | Retorna uma lista com todas as instituições. |
| POST | /Instituicoes/{nomeAdmin}/{senhaAdmin} | nomeAdmin (String), senhaAdmin (String), corpo (JSON) | Adiciona uma nova instituição. |
| PUT | /Instituicoes/{nomeAdmin}/{senhaAdmin}/{id} | nomeAdmin (String), senhaAdmin (String), id (UUID), corpo (JSON) | Altera uma instituição específica a partir do ID informado. |
| DELETE | /Instituicoes/{adminNome}/{senhaAdmin}/{id} | nomeAdmin (String), senhaAdmin (String), id (UUID) | Remove uma instituição específica a partir do ID informado. |


<h2>Rotas de usuarios</h2>

| HTTP Method | Endpoint | Parâmetros | Descrição |
| ----------- | -------- | ---------- | --------- |
| GET | /Usuarios/{email}/{senha} | email: String, senha: String | Retorna as informações do usuário que realizou login |
| GET | /Usuarios/getAllUsersIntituto/{idInstituto}/{emailUsuario}/{senhaUsuario} | idInstituto: UUID, emailUsuario: String, senhaUsuario: String | Retorna todos os usuários vinculados a uma instituição |
| GET | /Usuarios/getAllUsuariosInstitutoPaginado/{idInstituto}/{emailUsuario}/{senhaUsuario} | idInstituto: UUID, emailUsuario: String, senhaUsuario: String, pagina: Integer (opcional, default=0), tamanho: Integer (opcional, default=10) | Retorna todos os usuários vinculados a uma instituição, de forma paginada |
| GET | /Usuarios/{id} | id: UUID | Retorna as informações de um usuário específico, a partir do seu ID |
| POST | /Usuarios/{nomeInstituicao}/{senhaInstituicao} | nomeInstituicao: String, senhaInstituicao: String | Cria um novo usuário, vinculando-o a uma instituição |
| PUT | /Usuarios/{emailUsuario}/{senhaUsuario} | emailUsuario: String, senhaUsuario: String | Atualiza as informações do usuário que realizou login |
| PUT | /Usuarios/admin/{nomeAdmin}/{senhaAdmin}/{id} | nomeAdmin: String, senhaAdmin: String, id: UUID, dto: UsuariosDTO (request body) | Atualiza um usuário existente na base de dados. Requer autenticação de um admin da mesma instituição do usuário. |
| PUT | /Usuarios/online/{emailUsuario}/{senhaUsuario}/{online} | emailUsuario: String, senhaUsuario: String, online: Boolean | Altera o status de "online" de um usuário na base de dados. Requer autenticação do próprio usuário. |
| DELETE | /Usuarios/{emailUsuario}/{senhaUsuario} | emailUsuario: String, senhaUsuario: String | Remove um usuário da base de dados. Requer autenticação do próprio usuário. |
| DELETE | /Usuarios/admin/{nomeAdmin}/{senhaAdmin}/{id} | nomeAdmin: String, senhaAdmin: String, id: UUID | Remove um usuário da base de dados. Requer autenticação de um admin da mesma instituição do usuário. |


<h2>Rotas de questões</h2>

| HTTP Method | Endpoint                                      | Parâmetros                                  | Descrição                                                                                                                                              |
| ----------- | --------------------------------------------- | ------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------ |
| GET         | /Questoes/{id}                                | UUID id                                     | Busca uma questão pelo seu ID. Retorna um objeto ResponseEntity com o status HTTP e um possível corpo contendo a questão buscada, além de alguns links que podem ser acessados após a busca. |
| GET         | /Questoes/LimitRange/{idInstituicao}/{limit} | UUID idInstituicao, int limit                | Busca uma lista de questões aleatórias com um tamanho específico para a instituição correspondente ao ID informado. Retorna um objeto ResponseEntity com o status HTTP e um possível corpo contendo as questões buscadas, além de alguns links que podem ser acessados após a busca. |
| GET         | /Questoes/getAll/{idInstituicao}             | UUID idInstituicao                          | Busca uma lista com todas as questões da instituição correspondente ao ID informado. Retorna um objeto ResponseEntity com o status HTTP e um possível corpo contendo as questões buscadas, além de alguns links que podem ser acessados após a busca. |
| POST        | /Questoes/{adminNome}/{senhaAdmin}           | String adminNome, String senhaAdmin, QuestoesDTO questoesDto | Cadastra uma nova questão para a instituição do admin correspondente ao nome e senha informados. Retorna um objeto ResponseEntity com o status HTTP e um possível corpo contendo a questão cadastrada, além de alguns links que podem ser acessados após o cadastro. |
| PUT         | /Questoes/{adminNome}/{adminSenha}/{id}      | UUID id, String adminNome, String adminSenha, QuestoesDTO questoesDTO | Atualiza uma questão pelo seu ID. Retorna um objeto ResponseEntity com o status HTTP e um possível corpo, além de alguns links que podem ser acessados após a atualização. |
| DELETE      | /Questoes/{adminNome}/{senhaAdmin}/{id}      | String adminNome, String senhaAdmin, UUID id | Deleta uma questão pelo seu ID. Retorna um objeto ResponseEntity com o status HTTP e um possível corpo contendo a mensagem de sucesso ou de erro. |


<h2>Rotas de noticias</h2>

| HTTP Method | Endpoint                                      | Parâmetros                                | Descrição                                                                                                                                                                       |
|-------------|-----------------------------------------------|------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET         | /Noticias/{idInstituicao}/{index}/{size}      | int page, int size, UUID idInstituicao   | Retorna uma lista paginada de notícias da instituição com o ID especificado                                                                                                      |
| GET         | /Noticias/{id}                                | UUID id                                  | Retorna a notícia com o ID especificado                                                                                                                                          |
| GET         | /Noticias/getAll/{idInstituicao}              | UUID idInstituicao                       | Retorna todas as notícias da instituição com o ID especificado                                                                                                                  |
| POST        | /Noticias/{adminNome}/{senhaAdmin}            | String adminNome, String senhaAdmin, NoticiasDTO noticiasDto | Cria uma nova notícia para a instituição do administrador com o nome e senha especificados                                                                                      |
| PUT         | /Noticias/{adminNome}/{adminSenha}/{id}       | UUID id, String adminNome, String adminSenha, NoticiasDTO noticiasDTO | Atualiza a notícia com o ID especificado para a instituição do administrador com o nome e senha especificados |
| DELETE      | /Noticias/{adminNome}/{adminSenha}/{id}       | UUID id, String adminNome, String adminSenha | Exclui a notícia com o ID especificado para a instituição do administrador com o nome e senha especificados                                                                     |


<h2>Rotas de cronograma</h2>

| HTTP Method | Endpoint | Parâmetros | Descrição | Retornos Possíveis |
|-------------|----------|------------|----------|--------------------|
| GET         | /{emailUsuario}/{senhaUsuario}/{id} | id: UUID, emailUsuario: String, senhaUsuario: String | Busca um cronograma específico pelo ID. | 200 OK com o Cronograma solicitado, 401 UNAUTHORIZED se o usuário não estiver logado, 404 NOT FOUND se o cronograma não for encontrado, 500 INTERNAL SERVER ERROR em caso de erro interno. |
| GET         | /getAll/{emailUsuario}/{senhaUsuario} | emailUsuario: String, senhaUsuario: String | Busca todos os cronogramas do usuário logado. | 200 OK com uma lista de todos os cronogramas, 401 UNAUTHORIZED se o usuário não estiver logado, 500 INTERNAL SERVER ERROR em caso de erro interno. |
| POST        | /{emailUsuario}/{senhaUsuario} | emailUsuario: String, senhaUsuario: String, body: CronogramaDTO | Cadastra um novo cronograma para o usuário logado. | 200 OK com o cronograma cadastrado, 401 UNAUTHORIZED se o usuário não estiver logado, 500 INTERNAL SERVER ERROR em caso de erro interno. |
| PUT         | /{emailUsuario}/{senhaUsuario}/{id} | id: UUID, emailUsuario: String, senhaUsuario: String, body: CronogramaDTO | Altera um cronograma existente. | 200 OK com o cronograma atualizado, 401 UNAUTHORIZED se o usuário não estiver logado, 404 NOT FOUND se o cronograma não for encontrado, 500 INTERNAL SERVER ERROR em caso de erro interno. |
| DELETE      | /{emailUsuario}/{senhaUsuario}/{id} | id: UUID, emailUsuario: String, senhaUsuario: String | Exclui um cronograma existente. | 200 OK se o cronograma foi excluído com sucesso, 401 UNAUTHORIZED se o usuário não estiver logado, 404 NOT FOUND se o cronograma não for encontrado, 500 INTERNAL SERVER ERROR em caso de erro interno. |
| DELETE      | /{emailUsuario}/{senhaUsuario} | emailUsuario: String, senhaUsuario: String | Exclui todos os cronogramas do usuário logado. | 200 OK se todos os cronogramas foram excluídos com sucesso, 401 UNAUTHORIZED se o usuário não estiver logado, 500 INTERNAL SERVER ERROR em caso de erro interno. |



