import { buttonVariants } from "@/app/shared/ui/button/button.variants";

interface ButtonProps {
  text: string;
  variantButton?: "accent" | "danger" | "solid";
  accessibilityLabel?: string;
  disabled?: boolean;
}

function Button({
  text,
  variantButton = "accent",
  accessibilityLabel = "",
  disabled = false,
}: ButtonProps) {
  return (
    <button
      disabled={disabled}
      aria-label={accessibilityLabel || undefined}
      className={buttonVariants({ type: variantButton })}
    >
      {text}
    </button>
  );
}

export default Button;
