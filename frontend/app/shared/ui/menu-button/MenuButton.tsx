"use client";
import React, { useState } from "react";

interface MenuButtonProps {
  variant?: "grow" | "icon";
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
    if (color === "green")
      return "bg-accent-base/10 text-accent-base hover:bg-accent-base/25 active:bg-accent-base/5";
    if (color === "red")
      return "bg-error/10 text-error hover:bg-error/25 active:bg-error/5";
    if (color === "gray")
      return "bg-white/10 text-white hover:bg-white/25 active:bg-white/5";
  };

  return (
    <div className="cursor-pointer font-montserrat" onClick={handleClick}>
      {variant === "icon" ? (
        <div className={`rounded-[10px] p-2 ${setColor(bgColor)}`}>
          {children}
        </div>
      ) : variant === "grow" ? (
        <div
          className={`flex rounded-[20px] px-5 py-4 bg-input-outline transition-all duration-400 overflow-hidden
            ${isHover ? "text-error" : "text-white"}
        `}
          onMouseEnter={() => setIsHover(true)}
          onMouseLeave={() => setIsHover(false)}
        >
          <div
            className={` transition-all duration-200 
                        ${isHover ? " drop-shadow-[0_0_6px_var(--error)]" : "drop-shadow-[0_0_6px_var(--white)]"}`}
          >
            {children}
          </div>
          <p
            className={`
            transition-all duration-400  text-error flex items-center drop-shadow-[0_0_6px_var(--error)] whitespace-nowrap
            ${isHover ? "opacity-100 max-w-40 ml-3  " : "opacity-0 max-w-0 ml-0"}
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
            <div className="text-accent-base drop-shadow-[0_4px_4px_rgba(0,0,0,0.25),0_0_6px_rgba(0,185,6,0.75)]">
              {children}
            </div>
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
