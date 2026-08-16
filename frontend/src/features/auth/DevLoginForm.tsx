import { useState } from "react";
import { devLogin } from "./authApi";

export function DevLoginForm() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  return (
    <form
      onSubmit={async (event) => {
        event.preventDefault();
        await devLogin(username, password);
      }}
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "1rem",
        maxWidth: "320px",
      }}
    >
      <div>
        <label htmlFor="username">Username</label>
        <input
          id="username"
          type="text"
          value={username}
          onChange={(event) => setUsername(event.target.value)}
          style={{ display: "block", width: "100%" }}
        />
      </div>

      <div>
        <label htmlFor="password">Password</label>
        <input
          id="password"
          type="password"
          value={password}
          onChange={(event) => setPassword(event.target.value)}
          style={{ display: "block", width: "100%" }}
        />
      </div>

      <button type="submit">Dev Login</button>
    </form>
  );
}