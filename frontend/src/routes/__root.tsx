import {
  createRootRoute,
  Outlet,
} from "@tanstack/react-router";

export const Route = createRootRoute({
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