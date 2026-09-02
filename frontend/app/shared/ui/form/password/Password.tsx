'use client';
import { passwordVariants } from './password.variants';
import { Eye } from '@/app/shared/ui/eye';
import { useState } from 'react';

interface InputProps {
    disabled?: boolean;
    error?: boolean | string;
    placeholder?: string;
    type?: string;
}

function Password({ disabled, error, placeholder, type = 'password' }: InputProps) {
    const [showPassword, setShowPassword] = useState(false);

    const handleShowPassword = () => {
        setShowPassword(!showPassword);
    };
    return (
        <div>
            <label className={passwordVariants({ error: Boolean(error) })}>
                <input
                    className="flex w-full items-center justify-center focus:outline-none! focus-visible:outline-none!"
                    disabled={disabled}
                    placeholder={placeholder}
                    type={showPassword ? 'text' : 'password'}
                />
                <Eye state={showPassword} handleClick={handleShowPassword} />
            </label>
            {!!error && <p className="text-sm text-error mt-0.75">{error}</p>}
        </div>
    );
}

export default Password;
