# URL Shortener

## 📌 Project Overview

This project is a **URL Shortener Application** built using **React**, **Spring Boot** and **MongoDB**. It allows users to shorten long URLs and be redirected to the original URL using the generated short id.

## 🛠️ Tech Stack

- **Java & Spring Boot**
- **Typescript & React**
- **MongoDB**
- **Nginx & Reverse proxy**
- **AWS EC2**
- **Docker & Docker Compose**

---

## 🔨 How to Build and Run

### Clone the Repository\*\*

```sh
git clone https://github.com/Diogo-d-Souza/shortener.git
cd shortener
```

### Build and Run the Docker Container\*\*

```sh
docker docker compose up -d
```

## Stop and Remove the Containers and Volumes

```sh
docker compose down -v
```

---

## 🌐 Frontend Endpoints

- **Localhost: http://localhost:8081**
- **Deploy: http://18.234.161.155/** or **http://ec2-18-234-161-155.compute-1.amazonaws.com/**

## 📡 API Endpoints

| Method | Endpoint        | Description                   |
| ------ | --------------- | ----------------------------- |
| `POST` | `/shorten`      | Shortens a URL                |
| `GET`  | `/{shorten-id}` | Redirects to the original URL |

---

This **URL Shortener** was a simple project to practice **Docker, Nginx** and **AWS EC2**.
Feel free to leave a feedback or modify it as needed!
