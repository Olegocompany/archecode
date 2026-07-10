import { inputVariants } from "./input.variants";

interface InputProps {
    disabled?: boolean;
    error?: boolean | string;
    placeholder?: string;
    type?: string;
}


function Input({ disabled, error, placeholder, type = "text"  }: InputProps) {

    return (
        <label
        >
            <input className={inputVariants({ error: Boolean(error) })}
                   disabled={disabled}
                   placeholder={placeholder}
                   type={type}
            />
            {
                typeof error === "string" && (<p className="text-sm text-error">{ error }</p>)
            }

        </label>
    );
}

export default Input;