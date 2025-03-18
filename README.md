# PROVA TECNICA AUTOMAÇÃO DE TESTES API


## Automação de testes

Este projeto foi elaborado pensando em exemplificar uma cobertura de testes passando por diversas camadas do teste [HealtCheck, Sanity, Contrato, Funcional].<br> 
O projeto oferece a possibilidade de rodas os testes localmente evitando assim a correncia de utilizar a API publica, basta starta uma imagem da API atraves do docker-compose.yml e alterar o environment para 'env=local',<br>
ou podemos usar a API publica por default o projeto ja vem configurado para usa-lá 'env=hom'.<br>


### Swagger
https://petstore.swagger.io/

### Repositorio
https://gitlab.com/rodrigo.qa/outsera-automation-api-test


-HealtCheck<br>
-Sanity<br>
-Contrato<br>
-Funcional<br>


### Pré-condições

Para execução do projeto, é necessário possuir as seguintes instalações na máquina:
- Java 17 ou superior;
- Maven.
- Docker (Opcional)

### Executando o projeto
Após atender as pré-condições,<br>
executar o comando `mvn install` para baixar as dependências;

Caso opter por roda localmente:<br>
Sera necessario subir a imagem da API, `docker-compose up -d`.<br> 
Obs:(requerido docker)<br>

Para executar os testes, executar o comando: `mvn test clean -Dgroups=Regressivo -Denv=local -e`.<br>
-Dgroups: Tag referente ao teste(Opcional)<br> 
-Denv: Ambiente(Default=hom)<br>

### Tags
- Healthcheck
- Sanity
- Contrato
- Funcional

### Estrutura dos testes

#### Main
- **client:** contém as classes que irão realizar as requisições para o back-end;
- **dto:** inclui o mapeamento dos atributos e contrução dos objetos específicos para cada API;
- **factory:** classe ou objetos utilizados para retornar dados fictícios ou similares aos dados de produção.
- **utils:** contém classes auxiliares na construção dos testes, enumerações e configurações de ambiente.


#### Test
- **Sanity:** verifcar os cenarios mais criticos da aplicação;
- **Contrato:** agrupam os testes que validam o contrato das requisições;
- **functional:** agrupam os testes funcionais do projeto;
- **healthCheck:** verificam se o serviço está disponível;

### CI/CD

#### gitlab.ci
Foi implementado uma soluçâo para rodar os testes utilizado o CI do gitlab.ci.<br>
Os testes seram executados em estágios onde apenas vamos avançar para os proximos em caso de sucesso.

![img_1.png](img_1.png)

#### Relatórios
Após a execução dos testes é possível gerar o relatório com ajuda da biblioteca do Allure, desta forma é possivel visualizar todos resultados dos testes assim como os ‘logs’ das requests/response de forma mais simplificada.<br>
Para visualizar o relatório, basta executar o comando: `mvn allure:serve`<br>
![img.png](img.png)