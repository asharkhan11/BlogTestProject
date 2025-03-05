# Test Blog Project

This is the Test Blog Project.

## API Endpoints

### User Registration
**URL:** `http://192.168.3.44:4545/auth/register`

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
**URL:** `http://192.168.3.44:4545/auth/login`

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
**URL:** `http://192.168.3.44:4545/add/sum`

**Method:** `GET`

**Authorization:** Bearer Token

Use the token received from the login response and include it in the `Authorization` header as follows:
```
Authorization: Bearer <your_token_here>
```

---

### Notes:
- Ensure that the backend service is running at `http://192.168.3.44:4545`.
- Replace `<your_token_here>` with the actual token received during login.
- This project is a simple authentication API with role-based access control.

Happy Coding! 🚀

