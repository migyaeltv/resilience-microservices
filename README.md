RESILIENCE-MICROSERVICES

Projeto de pesquisa experimenta para tcc do curso mba de engenharia de software
O objetivo deste trabalho é identificar as abordagens mais eficientes para tornar os sistemas de microserviços mais resilientes, fornecendo uma base teórica e prática para a adoção dessas técnicas em diferentes cenários. O estudo busca mensurar como cada uma dessas técnicas contribui para minimizar falhas e melhorar a estabilidade das aplicações, por meio de experimentos práticos e análise de métricas de desempenho, permitindo uma compreensão das melhores práticas para a construção de sistemas resilientes.

O ambiente experimental está composto por dois microserviços:

1- Serviço de Ordens: Responsável por receber requisições HTTP de ordens de compra e retornar dados simples. Atuará como ponto de entrada do sistema.

2- Serviço de Pagamentos: Dependência do serviço de ordens, receberá as requisições e processará os pagamentos. O serviço por padrão tem uma porcentagem de falha de 10% das requisições. Também inclui a lógica de resiliência (Circuit Breaker, Retry e Exponential Backoff) e é submetido a falhas controladas durante os experimentos.

Camada de Aplicação
Linguagem de Programação: Java 21.
Framework: Spring Boot 3.4.0, utilizado para a construção dos microserviços.
Ambiente: Docker será empregado para isolar e gerenciar os serviços e suas dependências durante a execução dos experimentos.
Bibliotecas: Resilience4j, que oferece suporte à implementação dos padrões Circuit Breaker, Retry e Exponential Backoff.

Camada de Dados
Banco de Dados: MySQL para armazenamento dos dados.
Ambiente: Docker para gerenciamento do banco de dados.

Ferramentas de Teste e Monitoramento
Ferramenta de Teste de Carga: K6 será utilizado para gerar tráfego e simular cenários de falhas controladas nos microserviços.

Monitoramento de Métricas:
Prometheus para coleta de métricas de desempenho.
Grafana para visualização dessas métricas em tempo real.


Para executar as aplicações pode fazer um git clone na sua máquinae rodar na raiz do projeto o comando: docker compose up -d.

Para executar testes de carga, uma vez as aplicações estejam rodando, executar o comando: k6 run test.js

Para visualizar metricas no grafana: localhost:3000
Para visualizar metricas no prometheus: localhost: 9090
Mais informações no arquivo docker compose

