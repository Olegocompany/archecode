import { ReactNode } from "react";

interface LinkProps {
  to: string;
  children: ReactNode;
}

function Link({ to, children }: LinkProps) {
  return (
    <a
      className="text-accent-light cursor-pointer [text-shadow:0_0_6px_rgba(0,185,6,0.75)] transition duration-300
            hover:text-accent-lighter
            active:text-accent-dark active:[text-shadow:0_0_6px_rgba(0,185,6,0.5)]"
      href={to}
    >
      {children}
    </a>
  );
}

export default Link;
