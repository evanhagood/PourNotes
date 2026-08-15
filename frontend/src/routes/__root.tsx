import {
  createRootRouteWithContext,
  Outlet,
} from "@tanstack/react-router";

import type { QueryClient } from "@tanstack/react-query";

type RouterContext = {
  queryClient: QueryClient;
};

export const Route = createRootRouteWithContext<RouterContext>()({
  component: RootLayout,
});

function RootLayout() {
  return (
    <>
      <header>
        {/* Navbar eventually */}
      </header>

      <main>
        <Outlet />
      </main>

      <footer>
        {/* Footer eventually */}
      </footer>
    </>
  );
}