curl -X POST http://localhost:8080/users/login -s -H "Content-Type: application/json" -d '{"user": {
  "email": "super@super.cz",
  "password": "pass"
}}' | jq .user.token