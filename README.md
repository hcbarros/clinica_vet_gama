
Foi utilizado no projeto o servidor do Heroku que está hospedando a aplicação, e uma instancia EC2 da AWS que salva os dados no banco. 

Para instalar o projeto digite na linha de comando: mvn clean install

Para executar o projeto usando a linha de comando, deve-se digitar: mvn spring-boot:run

Para executar os testes: mvn test

Obs: caso esteja usando em seu diretório .m2 o settings.xml de um repositório privado, então baixe o apache maven (bin) no link https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.8.6/
...em seguida, digite os comandos citados acima, acompanhados da flag que aponta para o arquivo settings.xml do apache maven. 
Por exemplo: mvn clean install --settings caminho_do_settings.xml


### URL remota com o swagger: https://clinica-vet-gama.herokuapp.com/swagger-ui/index.html
### URL remota sem o swagger: https://clinica-vet-gama.herokuapp.com
### Acesso local com o swagger: http://localhost:8080/swagger-ui/index.html
### Acesso local sem o swagger: http://localhost:8080


GET /api/v1/agendamento/{id}\
GET /api/v1/agendamento\
GET /api/v1/pet\
GET /api/v1/pet/{id}\
GET /api/v1/tutor\
GET /api/v1/tutor/{id}\
POST /api/v1/pet\
POST /api/v1/tutor\
POST /api/v1/agendamento\
PUT /api/v1/pet/{id}\
PUT /api/v1/tutor/{id}\
PUT /api/v1/agendamento/{id}\
DELETE /api/v1/pet/{id}\
DELETE /api/v1/tutor/{id}\
DELETE /api/v1/agendamento/{id}\
