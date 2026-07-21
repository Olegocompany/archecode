"use client";

import { buttonVariants } from "@/app/shared/ui/button/button.variants";
import React from "react";
interface ButtonProps {
  onPress: () => void;
  text: string;
  variantButton?: "accent" | "danger" | "solid";
  accessibilityLabel?: string;
  disabled?: boolean;
}

function Button({
  onPress,
  text,
  variantButton = "accent",
  accessibilityLabel = "",
  disabled = false,
}: ButtonProps) {
  const handleClick = () => {
    onPress();
  };

  return (
    <button
      disabled={disabled}
      aria-label={accessibilityLabel || undefined}
      className={buttonVariants({ type: variantButton })}
      onClick={handleClick}
    >
      {text}
    </button>
  );
}

export default Button;
