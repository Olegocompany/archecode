"use client";
import React, { useState } from "react";

interface MenuButtonProps {
  variant: "grow" | "icon";
  handleClick?: () => void;
  text?: string;
  children?: React.ReactNode;
  bgColor?: "green" | "red" | "gray";
}

function MenuButton({
  handleClick,
  variant,
  children,
  text,
  bgColor = "gray",
}: MenuButtonProps) {
  const [isHover, setIsHover] = useState(false);

  const setColor = (color: string) => {
    if (color === "green") return "bg-accent-base text-accent-base";
    if (color === "red") return "bg-error text-error";
    if (color === "gray") return "bg-white text-white";
  };

  return (
    <div className="cursor-pointer font-main" onClick={handleClick}>
      {variant === "icon" ? (
        <div className={`text-${setColor(bgColor)}`}>{children}</div>
      ) : variant === "grow" ? (
        <div
          className={`flex rounded-[20px] px-5 py-4 bg-input-outline 
            ${isHover ? "text-error" : "text-white"}
        `}
          onMouseEnter={() => setIsHover(true)}
          onMouseLeave={() => setIsHover(false)}
        >
          <div
            className={`transition-all duration-300 
                        ${isHover ? " drop-shadow-[0_0_6px_var(--error)]" : "drop-shadow-[0_0_6px_var(--white)]"}`}
          >
            {children}
          </div>
          <p
            className={`
            transition-all duration-300 text-error drop-shadow-[0_0_6px_var(--error)] whitespace-nowrap
            ${isHover ? "opacity-100 max-w-40 ml-3  " : "opacity-0 max-w-0 overflow-hidden ml-0"}
        `}
          >
            {text}
          </p>
        </div>
      ) : (
        <div>
          <div
            className="flex items-center justify-center gap-4 px-5 py-6 bg-gray rounded-[20px]
        hover:bg-input-outline hover:drop-shadow-[0_0_60px_rgba(84,202,88,0.25)]
        active:opacity-50"
          >
            {children}
            <p className="text-accent-light text-shadow-[0_0_6px_rgba(0,185,6,0.75)]">
              {text}
            </p>
          </div>
        </div>
      )}
    </div>
  );
}

export default MenuButton;
