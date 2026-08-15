// features/auth/useLogout.ts

import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useNavigate } from "@tanstack/react-router";
import { logout } from "./authApi";

export function useLogout() {
  const queryClient = useQueryClient();
  const navigate = useNavigate();

  return useMutation({
    mutationFn: logout,

    onSuccess: async () => {
      queryClient.setQueryData(["account"], null);
      await navigate({ to: "/" });
    },

    onError: (error) => {
        console.error("Logout failed:", error);
    }
  });
}