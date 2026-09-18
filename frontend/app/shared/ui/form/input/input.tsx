'use client';
import { inputVariants } from './input.variants';
import { Eye } from '@/app/shared/ui/eye';
import { memo, useState } from 'react';

interface InputProps {
    disabled?: boolean;
    error?: boolean | string;
    placeholder?: string;
    type?: string;
    required?: boolean;
}

const Input = memo(function Input({
    disabled,
    error,
    placeholder,
    type = 'text',
    required,
}: InputProps) {
    const [showPassword, setShowPassword] = useState(false);

    const handleShowPassword = () => {
        setShowPassword(!showPassword);
    };

    const renderInput = () => {
        if (type === 'password') {
            return (
                <label className={inputVariants({ error: Boolean(error) })}>
                    <input
                        className="flex w-full items-center justify-center focus:outline-none! focus-visible:outline-none!"
                        disabled={disabled}
                        placeholder={placeholder}
                        type={showPassword ? 'text' : 'password'}
                        required={required}
                    />
                    <Eye state={showPassword} handleClick={handleShowPassword} />
                </label>
            );
        } else {
            return (
                <label>
                    <input
                        className={inputVariants({ error: Boolean(error) })}
                        disabled={disabled}
                        placeholder={placeholder}
                        type={type}
                        required={required}
                    />
                </label>
            );
        }
    };

    return (
        <div className="transition-all duration-300">
            {renderInput()}
            <div
                className={`transition-all duration-300 overflow-hidden
                  ${error ? 'max-h-20 opacity-100 mt-2' : 'max-h-0 opacity-0 mt-0'}`}
            >
                <p className="text-sm text-error">{error}</p>
            </div>
        </div>
    );
});

export default Input;
