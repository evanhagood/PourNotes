import { useState } from "react";

import type { CurrentUser } from "./authApi";
import { logout } from "./authApi";

type AccountPageProps = {
  user: CurrentUser;
  onLoggedOut: () => void;
};

export function AccountPage({
  user,
  onLoggedOut,
}: AccountPageProps) {
  const [logoutError, setLogoutError] = useState<string | null>(null);
  const [loggingOut, setLoggingOut] = useState(false);

  async function handleLogout() {
    setLoggingOut(true);
    setLogoutError(null);

    try {
      await logout();
      onLoggedOut();
    } catch (error) {
      setLogoutError(
        error instanceof Error ? error.message : "Logout failed",
      );
    } finally {
      setLoggingOut(false);
    }
  }

  return (
    <main>
      <h1>Your account</h1>

      {user.pictureUrl && (
        <img
          src={user.pictureUrl}
          alt={`${user.displayName} profile`}
          width={96}
          height={96}
          referrerPolicy="no-referrer"
        />
      )}

      <dl>
        <div>
          <dt>Name</dt>
          <dd>{user.displayName}</dd>
        </div>

        <div>
          <dt>Email</dt>
          <dd>{user.email ?? "Not provided"}</dd>
        </div>

        <div>
          <dt>Email verified</dt>
          <dd>{user.emailVerified ? "Yes" : "No"}</dd>
        </div>

        <div>
          <dt>PourNotes role</dt>
          <dd>{user.role}</dd>
        </div>
      </dl>

      <button
        type="button"
        onClick={handleLogout}
        disabled={loggingOut}
      >
        {loggingOut ? "Signing out..." : "Sign out"}
      </button>

      {logoutError && <p>{logoutError}</p>}
    </main>
  );
}