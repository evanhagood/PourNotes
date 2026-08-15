# PourNotes
A full-stack application to introduce people from all walks of life to coffee - whether if you've never heard of a latte or roast your own beans.

# Setting up docker for local db testing
1. Create a .env file under backend
2. Write this to .env file:
```
DATABASE_URL=jdbc:postgresql://localhost:5432/pournotes
DATABASE_USERNAME=pournotes
DATABASE_PASSWORD=pournotes
GOOGLE_CLIENT_ID=placeholder
GOOGLE_CLIENT_SECRET=placeholder
```
3. run 'docker compose up -d' from repo root (PourNotes/)

# A guide for creating comments:
/**
 * One-sentence summary of what this type or method represents.
 *
 * Additional explanation only when the behavior, constraints,
 * ownership rules, or business purpose are not obvious.
 *
 * @param parameterName meaning, constraints, units, and nullability
 * @return meaning of the returned value
 * @throws ExceptionType condition that causes the exception
 */
