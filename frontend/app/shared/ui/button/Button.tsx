import { buttonVariants } from "@/app/shared/ui/button/button.variants";
import { ReactNode } from "react";

interface ButtonProps {
  handleClick?: () => void;
  variantButton?: "accent" | "danger" | "solid";
  type?: "button" | "submit" | "reset";
  accessibilityLabel?: string;
  disabled?: boolean;
  children: ReactNode;
}

export default function Button({
  handleClick = () => 0,
  variantButton = "accent",
  accessibilityLabel = "",
  type = "button",
  disabled = false,
  children,
}: ButtonProps) {
  const handleClickOnButton = () => {
    if (disabled) return;
    handleClick();
  };

  return (
    <button
      onClick={handleClickOnButton}
      disabled={disabled}
      aria-label={accessibilityLabel || undefined}
      className={buttonVariants({ type: variantButton })}
      type={type}
    >
      {children}
    </button>
  );
}
