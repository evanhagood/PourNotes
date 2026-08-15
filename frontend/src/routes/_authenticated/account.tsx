// routes/account.tsx

import { createFileRoute } from "@tanstack/react-router";
import { AccountPage } from "../../features/account/AccountPage";

export const Route = createFileRoute("/_authenticated/account")({
  component: AccountPage,
});