import { useEffect, useState } from "react";
import "./App.css";

type ApiStatus = {
  status: string;
  message: string;
};

function App() {
  const [apiStatus, setApiStatus] = useState<ApiStatus | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    async function loadStatus() {
      try {
        const response = await fetch("/api/status");

        if (!response.ok) {
          throw new Error(`Request failed with status ${response.status}`);
        }

        const data: ApiStatus = await response.json();
        setApiStatus(data);
      } catch (requestError) {
        const message =
          requestError instanceof Error
            ? requestError.message
            : "Unknown request error";

        setError(message);
      }
    }

    loadStatus();
  }, []);

  return (
    <main>
      <h1>PourNotes</h1>

      {error && <p>Backend error: {error}</p>}

      {!error && !apiStatus && <p>Connecting to backend...</p>}

      {apiStatus && (
        <p>
          Backend status: <strong>{apiStatus.status}</strong>
          <br />
          {apiStatus.message}
        </p>
      )}
    </main>
  );
}

export default App;