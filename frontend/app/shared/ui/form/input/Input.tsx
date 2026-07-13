import { inputVariants } from "./input.variants";

interface InputProps {
  disabled?: boolean;
  error?: boolean | string;
  placeholder?: string;
  type?: string;
}

function Input({ disabled, error, placeholder, type = "text" }: InputProps) {
  return (
    <label>
      <input
        className={inputVariants({ error: Boolean(error) })}
        disabled={disabled}
        placeholder={placeholder}
        type={type}
      />
      {!!error && <p className="text-sm text-error mt-0.75">{error}</p>}
    </label>
  );
}

export default Input;
