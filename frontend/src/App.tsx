import { useQuery, useQueryClient } from "@tanstack/react-query";
import { Navigate, Route, Routes } from "react-router-dom";

import { AccountPage } from "./auth/AccountPage.tsx";
import {
  fetchCurrentUser,
  type CurrentUser,
} from "./auth/authApi";
import { LoginPage } from "./auth/LoginPage";

const CURRENT_USER_QUERY_KEY = ["current-user"] as const;

function App() {
  const queryClient = useQueryClient();

  const {
    data: currentUser,
    isPending,
    isError,
  } = useQuery({
    queryKey: CURRENT_USER_QUERY_KEY,
    queryFn: fetchCurrentUser,
  });

  function handleLoggedOut() {
    queryClient.setQueryData<CurrentUser | null>(
      CURRENT_USER_QUERY_KEY,
      null,
    );
  }

  if (isPending) {
    return (
      <main>
        <h1>PourNotes</h1>
        <p>Loading account...</p>
      </main>
    );
  }

  if (isError) {
    return (
      <main>
        <h1>PourNotes</h1>
        <p>Unable to contact the PourNotes server.</p>
      </main>
    );
  }

  return (
    <Routes>
      <Route
        path="/login"
        element={
          currentUser ? (
            <Navigate to="/account" replace />
          ) : (
            <LoginPage />
          )
        }
      />

      <Route
        path="/account"
        element={
          currentUser ? (
            <AccountPage
              user={currentUser}
              onLoggedOut={handleLoggedOut}
            />
          ) : (
            <Navigate to="/login" replace />
          )
        }
      />

      <Route
        path="*"
        element={
          <Navigate
            to={currentUser ? "/account" : "/login"}
            replace
          />
        }
      />
    </Routes>
  );
}

export default App;