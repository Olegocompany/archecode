"use client";
import { passwordVariants } from "./password.variants";
import { Eye } from "@/app/shared/ui/eye";
import { useState } from "react";

interface InputProps {
  disabled?: boolean;
  error?: boolean | string;
  placeholder?: string;
}

function Password({ disabled, error, placeholder }: InputProps) {
  const [showPassword, setShowPassword] = useState(false);
  const [inputType, setInputType] = useState("password");

  const handleShowPassword = () => {
    setShowPassword(!showPassword);
    setInputType(inputType === "password" ? "text" : "password");
  };
  return (
    <div>
      <label className={passwordVariants({ error: Boolean(error) })}>
        <input
          className="flex w-full items-center justify-center focus:outline-none! focus-visible:outline-none!"
          disabled={disabled}
          placeholder={placeholder}
          type={inputType}
        />
        <Eye state={showPassword} handleClick={handleShowPassword} />
      </label>
      {!!error && <p className="text-sm text-error mt-0.75">{error}</p>}
    </div>
  );
}

export default Password;
