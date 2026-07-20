"use client";
import React, { useState } from "react";
import { menuButtonVariants } from "@/app/shared/ui/menu-button/menuButton.variants";

interface MenuButtonProps {
  variant: "default" | "grow" | "icon";
  handleClick?: () => void;
  text?: string;
  children?: React.ReactNode;
  bgColor: string;
}

function MenuButton({
  handleClick,
  variant = "default",
  children,
  text,
}: MenuButtonProps) {
  const [isHover, setIsHover] = useState(false);

  return (
    <div
      onClick={handleClick}
      onMouseEnter={() => setIsHover(true)}
      onMouseLeave={() => setIsHover(false)}
      className={`${menuButtonVariants({ variant })}`}
    >
      {variant === "grow" && (
        <p
          className="transition-all duration-300"
          shadowClass={`${isHover ? " drop-shadow-[0_0_6px_var(--error)]" : "drop-shadow-[0_0_6px_var(--white)]"}`}
        >
          {children}
        </p>
      )}
      {variant !== "grow" && <div>{children}</div>}

      {!!text && (
        <>
          {variant === "default" && (
            <p className="text-accent-light drop-shadow-[0_0_6px_rgba(0,185,6,0.75)]">
              {text}
            </p>
          )}
          {variant === "grow" && (
            <p
              className={`
                transition-all duration-300 text-error drop-shadow-[0_0_6px_var(--error)] whitespace-nowrap
                ${isHover ? "opacity-100 max-w-40 ml-3" : "opacity-0 max-w-0 overflow-hidden ml-0"}
            `}
            >
              {text}
            </p>
          )}
        </>
      )}
    </div>
  );
}

export default MenuButton;
