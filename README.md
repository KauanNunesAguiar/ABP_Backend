# ABP-Backend - Display de leitura de sensores de dados ambientais

Este projeto tem como objetivo fazer o controle dos seus dadoss e informações de um sistema de aplicações de sensor de manhã a noite com acompanhamento em tempo real.

## Funcionalidades Principais

- **Registro de sensores**: Permite o registro de novos sensores especificando nome, localização e tipo de sensor.
- **Envio de dados de sensores:**: API para recebimento e armazenamento de dados enviados pelos sensores.
- **Consulta de dados por sensor**: Permite consultar os dados de um sensor específico em um intervalo de tempo determinado.

## Endpoints Disponíveis
### Dados ambientais
- **/api/dados-ambientais**: GET para listar todos os dados ambientais armazenados no banco de dados
- **/api/dados-ambientais**: PUT para criar dados ambientais
- **/api/dados-ambientais/{id}**: GET para buscar dados ambientais por ID.
- **/api/dados-ambientais/{id}**: PUT para atualizar dados ambientais por ID.
- **/api/dados-ambientais/{id}**: DELETE para deletar dados ambientais por ID.
- **/api/dados-ambientais/reset**: DELETE para resetar todos os dados ambientais do banco de dados e retorna o id para 1.

### Sensores
- **/api/sensores**: GET para listar todos os sensores armazenados no banco de dados
- **/api/sensores**: PUT para criar sensores, necessário cadastrar ao usuário
- **/api/sensores/{id}**: GET para buscar sensores por ID.
- **/api/sensores/{id}**: PUT para atualizar sensores por ID.
- **/api/sensores/{id}**: DELETE para deletar sensores por ID.
- **/api/sensores/{id}/colect-data**: POST para coletar e salvar dados ambientais do sensor (tipoDado = TipoSensor tipoDado, valor = double valor)
- **/api/sensores/reset**: DELETE para resetar todos os sensores do banco de dados e retorna o id para 1.

### Sensores
- **/api/usuarios**: GET para listar todos os usuarios armazenados no banco de dados
- **/api/usuarios**: PUT para criar sensores, necessário cadastrar ao usuário
- **/api/usuarios/{id}**: GET para buscar usuarios por ID.
- **/api/usuarios/{id}**: PUT para atualizar usuarios por ID.
- **/api/usuarios/{id}**: DELETE para deletar usuarios por ID.
- **/api/usuarios/{usuarioId}/register-sensor/{sensorId}**: POST para cadastrar um sensor a um usuário.
- **/api/usuarios/{usuarioId}/sensors**: GET para listar todos os sensores cadastrados no usuário.
- **/api/usuarios/{usuarioId}/search-by-sensor/{sensorId}**: POST listar todos os dados ambientais de um sensor de um usuário, sendo possível especificar intervalo de tempo das leituras (inicio = LocalDate inicioDoIntervalo (opcional), fim = LocalDate fimDoIntervalo (opcional)).
- **/api/usuarios/{usuarioId}/search-by-localizacao/{sensorId}**: POST listar todos os dados ambientais de um usuário em uma localizacao definida, sendo possível especificar intervalo de tempo das leituras (inicio = LocalDate inicioDoIntervalo (opcional), fim = LocalDate fimDoIntervalo (opcional)).
- **/api/usuarios/reset**: DELETE para resetar todos os usuarios do banco de dados e retorna o id para 1.
- **/api/usuarios/ajuda**: GET para adiquir nome dos programadors e do projeto.

## Dependências
Para executar o projeto, é necessário ter o Maven e o Java instalados.

## Autores
Este projeto foi desenvolvido por Kauan Nunes Aguiar e Silvio Virtuoso Júnior.
