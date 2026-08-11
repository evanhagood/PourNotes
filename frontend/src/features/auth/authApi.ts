export type CurrentUser = {
  id: string;
  displayName: string;
  email: string | null;
  emailVerified: boolean;
  pictureUrl: string | null;
  role: "USER" | "ADMIN";
};

type CsrfTokenResponse = {
  token: string;
  headerName: string;
  parameterName: string;
};

/**
 * Returns the current authenticated user, or null when no valid
 * backend session exists.
 */
export async function fetchCurrentUser(): Promise<CurrentUser | null> {
  const response = await fetch("/api/auth/me", {
    method: "GET",
    credentials: "include",
    headers: {
      Accept: "application/json",
    },
  });

  if (response.status === 401) {
    return null;
  }

  if (!response.ok) {
    throw new Error(
      `Unable to load current user: ${response.status}`,
    );
  }

  return response.json() as Promise<CurrentUser>;
}

/**
 * Begins the Google login flow through Spring Security.
 */
export function beginGoogleLogin(): void {
  window.location.assign("/oauth2/authorization/google");
}

/**
 * Retrieves a CSRF token from Spring Security.
 */
async function fetchCsrfToken(): Promise<CsrfTokenResponse> {
  const response = await fetch("/api/auth/csrf", {
    method: "GET",
    credentials: "include",
    headers: {
      Accept: "application/json",
    },
  });

  if (!response.ok) {
    throw new Error(`Unable to obtain CSRF token: ${response.status}`);
  }

  return response.json() as Promise<CsrfTokenResponse>;
}

/**
 * Invalidates the current backend session.
 */
export async function logout(): Promise<void> {
  const csrf = await fetchCsrfToken();

  const response = await fetch("/api/auth/logout", {
    method: "POST",
    credentials: "include",
    headers: {
      [csrf.headerName]: csrf.token,
    },
  });

  if (!response.ok) {
    throw new Error(`Logout failed: ${response.status}`);
  }
}