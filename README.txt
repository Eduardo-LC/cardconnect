
# Card Connect API

Bem-vindo ao projeto Card Connect API! Este projeto cria uma API para gerenciamento de jogadores e cartas de baralho, integrando com uma API de baralhos externa.

## Requisitos

1. **Docker e Docker Compose**: Certifique-se de que o Docker e o Docker Compose estão instalados no seu computador.
2. **Portas**: Libere as portas `8081` (para a aplicação), `7891` (para o banco de dados PostgreSQL), e `8080` (para o Adminer).

## Passo a Passo para Configurar o Projeto

1. **Clonar o Repositório**:
   Baixe o projeto para o seu computador usando o comando:
   ```
   git clone git@github.com:Eduardo-LC/cardconnect.git
   ```
   
2. **Configurar o Arquivo Docker Compose**:
   Certifique-se de que o projeto contém o arquivo `banco.yaml`, que configura os serviços do Docker para a aplicação e o banco de dados.

3. **Arquivo de Configurações (`application.properties`)**:
   O projeto já inclui o arquivo `application.properties` pré-configurado para conectar-se ao banco de dados e à API de baralhos. Não é necessária nenhuma alteração adicional para que a aplicação funcione.

4. **Construir e Subir os Containers**:
   Execute o seguinte comando na raiz do projeto para iniciar os serviços:
   ```
   docker-compose -f banco.yaml up --build
   ```
   
   Esse comando irá:
   - **Construir a imagem do app** a partir do `Dockerfile`.
   - **Criar e iniciar um container do PostgreSQL** com as credenciais definidas.
   - **Iniciar o app** e conectá-lo ao banco de dados PostgreSQL na porta `7891`.
   - **Iniciar o Adminer** na porta `8080` para gerenciar o banco de dados via interface gráfica.

5. **Acessar a API**:
   Quando os serviços estiverem rodando, você poderá acessar a API no navegador ou Postman usando o link:
   ```
   http://localhost:8081
   ```

   Exemplos de endpoints:
   - Iniciar um novo jogo: `GET http://localhost:8081/api/game/start`
   - Verificar o vencedor do jogo: `GET http://localhost:8081/api/game/winner`

6. **Acessar o Banco de Dados**:
   Para gerenciar o banco de dados PostgreSQL, você pode utilizar uma ferramenta de sua preferência, como **Adminer**, **DBeaver** ou qualquer outro gerenciador de banco de dados.

   - Para acessar pelo **Adminer**, vá para [http://localhost:8080](http://localhost:8080).  
   - As credenciais de acesso ao banco de dados são:
     - **Servidor**: `db`
     - **Usuário**: `dudu`
     - **Senha**: `dudu123`
     - **Banco de dados**: `postgres`

## Encerrando os Containers

Quando quiser parar a aplicação e o banco de dados, use o comando:
```
docker-compose -f banco.yaml down
```

---

**Pronto!** Agora você pode usar a Card Connect API e gerenciar o banco de dados PostgreSQL com a ferramenta de sua escolha.
