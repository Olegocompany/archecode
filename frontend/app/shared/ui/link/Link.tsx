import { ReactNode } from "react";

interface LinkProps {
  to: string;
  children: ReactNode;
}

function Link({ to, children }: LinkProps) {
  return (
    <a
      className="text-accent-light cursor-pointer [text-shadow:0_0_6px_rgba(0,185,6,0.75)]
            hover:text-accent-lighter
            active:[text-shadow:0_0_6px_rgba(0,185,6,0.5)] transition duration-300"
      href={to}
    >
      {children}
    </a>
  );
}

export default Link;
