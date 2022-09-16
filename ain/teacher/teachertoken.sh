curl -X POST http://localhost:8080/users/login -s -H "Content-Type: application/json" -d '{"user": {
  "email": "teacher@teacher.cz",
  "password": "pass"
}}' | jq -M .user.token