# Desafio Técnico
## Sobre

API feita a partir do framework Springboot, devido a sua pré configuração de componentes para aplicações WEB, ORM, Rest
e HTTP, também por eu possuir um conhecimento prévio em implementações de API's construídas em Springboot.

Para o banco de dados foi escolhido o Postgress pelo fato de ser um banco de dados gratuito e de rápida implementação
assim como seu suporte pela comunidade e por eu possuir um conhecimento prévio em API's que possuiam postgres como 
fonte de dados.

A fim de criar uma aplicação que execute na nuvem e em diversos ambientes foi criado uma estrutura Docker para executar
a aplicação, o banco de dados e o console gerenciador do banco de dados PgAdmin 4.

A documentação foi feita a partir do framework Swagger e está disponível em: http://localhost/swagger-ui.htm. Estão 
disponíveis as chamadas do POSTMAN na pasta run.

## Execução

Executando o primeiro comando: "mvn clean install" para que dependências sejam baixadas. Após as libs estarem de acordo
acesse a pasta run na raiz do projeto e execute a estrutura docker com o comando "docker-compose up". Para isso
é necessário que o Docker machine esteja instalado em sua máquina. A aplicação ficará disponível no http://localhost:8080 e o 
console do PgAdmin 4 no endereço http://localhost:5050/


#### Tarefa Bônus 1 - Integração com sistemas externos
Implementado.

Descrição: Implementado usando RestTemplate do Java.

#### Tarefa Bônus 2 - Mensageria e filas
Não implementada.

#### Tarefa Bônus 3 - Performance
Implementado.

Descrição: Na raiz do projeto existe a pasta jmeter, nela consta dois relatórios de Teste de Perfomace executado nos métodos de
cadastrar um novo Associado e no método de Realizar Voto.

CadastrarNovoAssociado: Executado sem problemas, consta 99% de sucesso, devido a primeira linha da massa de dados que 
estava incorreta.

RealizarVoto: Possuiu 53.33% de sucesso, devido ao fato do método verificar o CPF do candidato antes de executar o voto,
portanto a taxa de erros gerada é ocasionada pelos resultados de UNABLE_TO_VOTE do componente de verificação do CPF.

Também foram implementados testes unítarios para alguns dos serviços assim como teste de integração utilizando o banco 
em memória H2. 

#### Tarefa Bônus 4 - Versionamento da API

Usando versionamento semântico. Devido a essa API possuir uma grande escabilidade, podendo possuir mudanças estruturais
grandes, adição de novas funcionalidades e correção de bugs o versionamento semântico parece apropriado em lidar com cada
situação de release. Dado o número de versão MAJOR.MINOR.PATCH, que representam:

Versão MAJOR quando você faz alterações de API incompatíveis.
Versão MINOR quando você adiciona funcionalidade de maneira compatível com versões anteriores.
Versão PATCH quando você faz correções de bugs compatíveis com versões anteriores..

Portanto essa API se inícia na versão 1.0.0 já que é uma versão estável.
Caso fosse adicionada alguma funcionalidade ela iria para 1.1.0
Ou uma correção de bugs: 1.0.1.
