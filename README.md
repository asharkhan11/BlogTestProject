# Test Blog Project

This is the Test Blog Project, a simple authentication API with role-based access control.

## Table of Contents

## Table of Contents
1. [API Endpoints](#api-endpoints)
    - [User Registration](#user-registration)
    - [User Login](#user-login)
    - [Protected Route](#protected-route-token-required)
    - [Search Blogs](#search-blogs)
2. [Notes](#notes)

## API Endpoints

### User Registration

**URL:** `http://127.0.0.1:4545/auth/register`

**Method:** `POST`

**Request Body:**

```json
{
  "name": "Example User",
  "email": "test1",
  "password": "test1",
  "role": "USER"
}
```

### User Login

**URL:** `http://127.0.0.1:4545/auth/login`

**Method:** `POST`

**Request Body:**

```json
{
  "email": "test",
  "password": "test"
}
```

**Response:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6InRlc3QiLCJleHAiOjE3NDExNTc5NTl9.28ygI6y3MKFrq9Ft7LLrzS8iA8feWqZxuoBAHXViDDA"
}
```

### Protected Route (Token Required)

**URL:** `http://127.0.0.1:4545/add/sum`

**Method:** `GET`

**Authorization:** Bearer Token

Use the token received from the login response and include it in the `Authorization` header as follows:

```
Authorization: Bearer <your_token_here>
```

---

### Search Blogs
**URL:** `http://127.0.0.1:4545/api/blogs/search?query=?`

**Method:** `GET`

**Authorization:** Bearer Token

**Example Request:**
```
http://127.0.0.1:4545/api/blogs/search?query=java
```

**Example Response:**
```json
[
  {
    "id": 1,
    "title": "Spring Boot Guide",
    "content": "A complete guide to Spring Boot framework.",
    "keywords": "Spring Boot, Java, Backend",
    "category": "Technology"
  },
  {
    "id": 2,
    "title": "Introduction to Microservices",
    "content": "Microservices architecture explained with examples.",
    "keywords": "Microservices, Spring Cloud, Java",
    "category": "Technology"
  }
]
```

---

## Notes

- Ensure that the backend service is running at `http://127.0.0.1:4545`.
- Replace `<your_token_here>` with the actual token received during login.
- More API endpoints will be added in the future.

---
## 📌 Blogging Application To-Do List

### 🚀 User Account Management
- [ ] **User Preferences:**
   - Ask users for their preferred blog categories during account creation.
   - Provide blog suggestions based on these preferences.
- [ ] **Authentication & Security:**
   - Implement a **Logout API** using Redis for JWT blacklisting.
   - Develop a **Reset Password** and **Forgot Password** feature.
   - Integrate **OTP Validation**:
      - During **User Registration**.
      - During **Forgot Password** recovery.

## 📚 Blog Features
- [ ] **Popular Blogs API:**
   - Fetch blogs **based on likes**.
   - Fetch blogs **based on category**.
   - Fetch blogs **based on user preferences**.
- [ ] **Best Blogs of the Week:**
   - Show the top 5 blogs **from related categories**.
- [ ] **Blog Monitoring & Statistics:**
   - Track **view count** for each blog.
   - If feasible, implement **blog statistics** such as:
      - Total popularity score of blogs.

## 🛠️ User Features
- [ ] **User Profile Page:**
   - Add an **option to upload a profile picture**.
   - Include **blog monitoring** on the profile page.
- [ ] **User Settings:**
   - Allow users to manage their settings and preferences.

## 💬 Engagement & Interaction
- [ ] **Comments & Ratings System:**
   - Allow users to **comment** on blogs.
   - Implement a **sub-comment** system (threaded replies).
   - Add a **rating system** for blogs.

---  
✅ *This list can be updated as needed.*





---
Happy Coding! 🚀

