import { createFileRoute, redirect } from "@tanstack/react-router";
import { getAccount } from "../features/auth/authApi";

export const Route = createFileRoute("/_authenticated")({
  beforeLoad: async ({ context }) => {
    const account = await context.queryClient.ensureQueryData({
      queryKey: ["account"],
      queryFn: getAccount,
      retry: false,
    });

    if (!account) {
      throw redirect({
        to: "/login",
      });
    }
  },
});