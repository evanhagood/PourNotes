// routes/account.tsx

import { createFileRoute, redirect } from "@tanstack/react-router";
import { AccountPage } from "../features/auth/AccountPage";
import { fetchCurrentUser } from "../features/auth/authApi";

export const Route = createFileRoute("/account")({
  beforeLoad: async () => {
    const currentUser = await fetchCurrentUser();

    if (!currentUser) {
      throw redirect({
        to: "/login",
      });
    }

    return {
      currentUser,
    };
  },

  component: AccountRoute,
});

function AccountRoute() {
  const { currentUser } = Route.useRouteContext();

  return <AccountPage user={currentUser} />;
}