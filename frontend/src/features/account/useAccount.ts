import {useQuery} from "@tanstack/react-query";
import { getAccount } from "../auth/authApi";

export function useAccount() {
    return useQuery({
        queryKey: ["account"],
        queryFn: getAccount,
        retry: false,
        staleTime: 5 * 60 * 1000 // 5 minutes
    });
}