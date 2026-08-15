export type Account = {
  id: string;
  displayName: string;
  email: string | null;
  emailVerified: boolean;
  pictureUrl: string | null;
  role: "USER" | "ADMIN";
};