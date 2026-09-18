import { CloseEye, OpenEye } from '@/app/shared/svg';
import { memo } from 'react';

interface EyeProps {
    state?: boolean;
    handleClick?: () => void;
}

const Eye = memo(function Eye({ handleClick, state = true }: EyeProps) {
    return (
        <>
            {state ? (
                <OpenEye
                    onClick={handleClick}
                    className=" min-w-6 max-h-4 cursor-pointer text-primary-dark  hover:text-lighter-gray active:text-input-placeholder transition duration-300"
                />
            ) : (
                <CloseEye
                    onClick={handleClick}
                    className=" min-w-6 max-h-3 cursor-pointer text-primary-dark  hover:text-lighter-gray active:text-input-placeholder transition duration-300"
                />
            )}
        </>
    );
});

export default Eye;
