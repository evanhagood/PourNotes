import { createRouter } from "@tanstack/react-router";
import { queryClient } from "./queryClient.tsx";
import { routeTree } from "./routeTree.gen";

export const router = createRouter({
  routeTree,
  context: {
    queryClient,
  },
  defaultErrorComponent: ({error}) => {
    // TODO: Log this and don't show the user a bare error message.
    // build out a dedicated 5XX page eventually
    return (
      <main>
        <h1>We fucked something up</h1>
        <p>{error.message}</p>
      </main>
    )
  }
});

declare module "@tanstack/react-router" {
  interface Register {
    router: typeof router;
  }
}