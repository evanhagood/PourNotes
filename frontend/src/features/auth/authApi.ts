import type { CsrfTokenResponse } from "./types/CsrfTokenResponse";
import type { Account } from "../account/Account";

/**
 * Get the current authenticated acount
 * 
 * @returns Promise<Account> with the account information or null if not authenticated.
 */
export async function getAccount(): Promise<Account | null> {
  const response = await fetch("/api/auth/me", {
    credentials: "include",
  });

  if(response.status == 401) { // user is unauthenticated
    return null;
  }

  if(!response.ok) {
    throw new Error("Failed to fetch account");
  }

  return response.json();
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

/**
 * Authenticates a local development user.
 */
export async function devLogin(
  username: string,
  password: string
): Promise<void> {
  const csrf = await fetchCsrfToken();

  const body = new URLSearchParams({
    username,
    password,
  });

  const response = await fetch("/api/auth/dev-login", {
    method: "POST",
    credentials: "include",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
      [csrf.headerName]: csrf.token,
    },
    body,
  });

  if (!response.ok) {
    throw new Error(`Dev login failed: ${response.status}`);
  }
}