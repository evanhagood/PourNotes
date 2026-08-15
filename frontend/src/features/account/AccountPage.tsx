import { useNavigate } from "@tanstack/react-router";
import { useAccount } from "./useAccount";
import { useLogout } from "../auth/useLogout";

export function AccountPage() {
  const {data: account, isPending, isError} = useAccount();
  const navigate = useNavigate();
  const logout = useLogout();

  if(isPending) {
    return <p>Loading (make this better later)</p>
  }

  if(isError) {
    return <p> Failed to load account. </p>
  }

  if(!account) {
    navigate({to: "/login"});
    return <p>Not authenticated</p>;
  }

  return (
    <main>
      <h1>Your account</h1>

      {account.pictureUrl && (
        <img
          src={account.pictureUrl}
          alt={`${account.displayName}'s profile`}
          width={96}
          height={96}
          referrerPolicy="no-referrer"
        />
      )}

      <dl>
        <div>
          <dt>Name</dt>
          <dd>{account.displayName}</dd>
        </div>

        <div>
          <dt>Email</dt>
          <dd>{account.email ?? "Not provided"}</dd>
        </div>

        <div>
          <dt>Email verified</dt>
          <dd>{account.emailVerified ? "Yes" : "No"}</dd>
        </div>

        <div>
          <dt>PourNotes role</dt>
          <dd>{account.role}</dd>
        </div>
      </dl>

      <button onClick={() => logout.mutate()}>Log Out</button>
    </main>
  );
}