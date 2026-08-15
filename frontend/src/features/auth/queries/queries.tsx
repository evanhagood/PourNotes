import {
  useQuery,
  useQueryClient,
} from "@tanstack/react-query";

import {
  fetchCurrentUser,
  type CurrentUser,
} from "../authApi";

export const CURRENT_USER_QUERY_KEY = [
  "current-user",
] as const;

export function useCurrentUser() {
  return useQuery({
    queryKey: CURRENT_USER_QUERY_KEY,
    queryFn: fetchCurrentUser,
  });
}

export function useClearCurrentUser() {
  const queryClient = useQueryClient();

  return () => {
    queryClient.setQueryData<CurrentUser | null>(
      CURRENT_USER_QUERY_KEY,
      null,
    );
  };
}