# 🔗 Link Shortener

Este é um encurtador de URLs desenvolvido com Java Spring Boot no backend e Vue.js no frontend, totalmente containerizado com Docker e Docker Compose.

---

## 🚀 Tecnologias

- Backend: Java + Maven + Spring Boot
- Frontend: Vue.js + TypeScript, Sass
- Banco de dados: MySQL
- Docker + Docker Compose

---

## 📦 Pré-requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

---

## 🛠️ Como usar

### 1. Clone o repositório

```bash
git clone https://github.com/YanRamoss/link-shortener.git
cd link-shortener
```

### 2. Configure os arquivos `.env`

Renomeie os arquivos `.env.example` para `.env` nas seguintes localizações:

```bash
cp .env.example .env
cp client/.env.example client/.env
cp api/.env.example api/.env
```

Depois, edite os arquivos `.env` conforme necessário com suas configurações locais (por exemplo: porta da api, variáveis do banco de dados, etc.).

---

## 🐳 Subindo o ambiente com Docker

Para subir todo o ambiente:

```bash
docker-compose up --build
```

Este comando irá:

- Construir as imagens do frontend (`client`) e backend (`api`)
- Criar e inicializar todos os containers
- Conectar os serviços entre si

---

## 🌐 Acessos locais

- Frontend: [http://localhost:2000](http://localhost:2000)
- Backend (API): [http://localhost:2100](http://localhost:2100)

---

## 🧹 Comandos úteis

### Parar os containers

```bash
docker-compose down
```

### Remover todos os containers, imagens e volumes

```bash
docker system prune -a --volumes
```

⚠️ **Cuidado:** esse comando remove todos os volumes, containers e imagens do Docker.