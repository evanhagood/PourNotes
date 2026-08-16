import { beginGoogleLogin } from "./authApi";
import { DevLoginForm } from "./DevLoginForm";

export function LoginPage() {
  return (
    <main className="login-page">
      <h1>PourNotes</h1>
      <p>Sign in to record coffees and build your taste profile.</p>

      <button onClick={beginGoogleLogin}>
        Continue with Google
      </button>

      {import.meta.env.DEV && <DevLoginForm />}
    </main>
  );
}