import { ReactNode } from 'react';
import { default as Hyperlink } from 'next/link';

interface LinkProps {
    to: string;
    size: number;
    children: ReactNode;
}

function Link({ to, children, size }: LinkProps) {
    return (
        <Hyperlink
            className={
                'text-accent-light cursor-pointer [text-shadow:0_0_6px_rgba(0,185,6,0.75)] transition duration-300' +
                'hover:text-accent-lighter' +
                'active:text-accent-dark active:[text-shadow:0_0_6px_rgba(0,185,6,0.5)] '
            }
            href={to}
            style={{ fontSize: size ?? 16 }}
        >
            {children}
        </Hyperlink>
    );
}

export default Link;
