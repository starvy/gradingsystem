curl -X POST http://localhost:8080/users/login -s -H "Content-Type: application/json" -d '{"user": {
  "email": "student@student.cz",
  "password": "pass"
}}' | jq .user.token