import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import { tanstackRouter } from "@tanstack/router-plugin/vite";

export default defineConfig({
  plugins: [
    tanstackRouter({
      target: "react",
    }),
    react(),
  ],

  server: {
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },

      "/oauth2": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },

      "/login": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
    },
  },
});