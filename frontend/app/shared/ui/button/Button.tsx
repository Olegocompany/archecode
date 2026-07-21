import { buttonVariants } from "@/app/shared/ui/button/button.variants";

interface ButtonProps {
  handleClick: () => void;
  text: string;
  variantButton?: "accent" | "danger" | "solid";
  accessibilityLabel?: string;
  disabled?: boolean;
}

export default function Button({
  handleClick,
  text,
  variantButton = "accent",
  accessibilityLabel = "",
  disabled = false,
}: ButtonProps) {
  return (
    <button
      onClick={handleClick}
      disabled={disabled}
      aria-label={accessibilityLabel || undefined}
      className={buttonVariants({ type: variantButton })}
    >
      {text}
    </button>
  );
}

// export default Button;
