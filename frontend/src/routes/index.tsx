import { createFileRoute, Link } from "@tanstack/react-router";
import { LoginPage } from "../features/auth/LoginPage";

export const Route = createFileRoute("/")({
  component: HomePage,
});

function HomePage() {
  return (<LoginPage></LoginPage>);
}