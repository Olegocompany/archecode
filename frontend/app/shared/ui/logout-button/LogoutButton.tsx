"use client";
import { useState } from "react";
import { ExitIcon } from "@/app/shared/svg";

interface LogoutButtonProps {
  handleClick?: () => void;
}

function LogoutButton({ handleClick }: LogoutButtonProps) {
  const [isHover, setIsHover] = useState(false);
  return (
    <div
      className={`flex rounded-[20px] px-5 py-4 bg-input-outline 
            ${isHover ? "text-error" : "text-white"}
        `}
      onMouseEnter={() => setIsHover(true)}
      onMouseLeave={() => setIsHover(false)}
    >
      <ExitIcon
        className="transition-all duration-300"
        shadowClass={`${isHover ? " drop-shadow-[0_0_6px_var(--error)]" : "drop-shadow-[0_0_6px_var(--white)]"}`}
      />
      <p
        className={`
            transition-all duration-300 text-error drop-shadow-[0_0_6px_var(--error)] whitespace-nowrap
            ${isHover ? "opacity-100 max-w-40 ml-3  " : "opacity-0 max-w-0 overflow-hidden ml-0"}
        `}
      >
        Выйти из аккаунта
      </p>
    </div>
  );
}

export default LogoutButton;
