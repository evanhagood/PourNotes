import { beginGoogleLogin } from "./authApi";

export function LoginPage() {
  return (
    <main>
      <h1>PourNotes</h1>
      <p>
        Sign in to record coffees and build your taste profile.
      </p>

      <button type="button" onClick={beginGoogleLogin}>
        Continue with Google
      </button>
    </main>
  );
}