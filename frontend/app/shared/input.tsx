import { inputVariants } from "./input.variants";

interface InputProps {
    disabled?: boolean;
    error?: boolean;
    placeholder?: string;
    type?: string;
}


function Input({ disabled, error, placeholder, type = "text"  }: InputProps) {


    return (
        <label
        >
            <input className={inputVariants({ error })}
                   disabled={disabled}
                   placeholder={placeholder}
                   type={type}
            />
        </label>
    );
}

export default Input;