# 📌 RESILIENCE-MICROSERVICES

Projeto de pesquisa experimental para o TCC do **MBA em Engenharia de Software**.

## 📌 Objetivo

O objetivo deste trabalho é identificar as abordagens mais eficientes para tornar os sistemas de **microserviços mais resilientes**, fornecendo uma base teórica e prática para a adoção dessas técnicas em diferentes cenários. O estudo busca mensurar como cada uma dessas técnicas contribui para **minimizar falhas e melhorar a estabilidade** das aplicações, por meio de experimentos práticos e análise de métricas de desempenho. O resultado esperado é uma melhor compreensão das **melhores práticas para a construção de sistemas resilientes**.

---

## 📌 Arquitetura do Sistema

O ambiente experimental é composto por **dois microserviços**:

### 🛒 **1. Serviço de Ordens**
- Responsável por receber requisições HTTP de ordens de compra e retornar dados simples.
- Atua como o **ponto de entrada do sistema**.

### 💳 **2. Serviço de Pagamentos**
- Dependência do Serviço de Ordens, processa os pagamentos das requisições recebidas.
- Inclui **10% de falha controlada por padrão** para simulação de cenários reais.
- Implementa os padrões de **Circuit Breaker, Retry e Exponential Backoff** usando a biblioteca **Resilience4j**.
- É submetido a **falhas controladas** durante os experimentos.

---

## 📌 Tecnologias Utilizadas

### 🔹 **Camada de Aplicação**
- **Linguagem de Programação:** Java 21.
- **Framework:** Spring Boot 3.4.0.
- **Ambiente:** Docker (para isolamento e gerenciamento dos serviços).
- **Bibliotecas:** Resilience4j (para implementação dos padrões de resiliência).

### 🔹 **Camada de Dados**
- **Banco de Dados:** MySQL.
- **Gerenciamento:** Docker.

### 🔹 **Ferramentas de Teste e Monitoramento**
- **Teste de Carga:** [K6](https://k6.io/) (simulação de tráfego e falhas controladas).
- **Monitoramento de Métricas:**
  - **Prometheus** (coleta de métricas de desempenho).
  - **Grafana** (visualização de métricas em tempo real).

---

## 📌 Como Executar o Projeto

### 🔹 **1. Clonar o Repositório**
git clone https://github.com/seu-usuario/resilience-microservices.git
cd resilience-microservices

### 🔹 **2. Executar as aplicações**
docker compose up -d

### 🔹 **3.Testes de carga**
k6 run test.js


### 🔹 **4.Monitoramento**
Grafana: http://localhost:3000
Prometheus: http://localhost:9090
Mais informações no arquivo docker-compose.yml

