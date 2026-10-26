# Sample Snyk Vulnerability Demo - CRUD Service with Mock DAO

This project is intentionally designed to show common insecure patterns that Snyk can flag.
It is a simple Java CRUD example using an in-memory mock DAO rather than a real database.

## Project structure

- `com.example.crud.CrudSampleApplication` - demo entry point
- `com.example.crud.model.User` - domain model
- `com.example.crud.dao.MockUserDao` - in-memory DAO
- `com.example.crud.service.UserService` - business logic with vulnerable code paths
- `com.example.crud.controller.UserController` - simple CRUD wrapper

## Why this is intentionally vulnerable

The sample includes examples such as:

- SQL injection style query construction through string concatenation
- Weak MD5 password hashing
- Untrusted command execution via `Runtime.exec()`
- Unsafe handling of raw user input in business logic

These patterns are useful for security scanning demonstrations.

## Compile

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out com.example.crud.CrudSampleApplication
```

On Windows PowerShell:

```powershell
Get-ChildItem src/main/java -Recurse -Filter *.java | ForEach-Object { $_.FullName }
# then run javac manually on the file list
```
