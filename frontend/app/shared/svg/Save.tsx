import * as React from 'react';
import type { SVGProps } from 'react';
const SvgSave = (props: SVGProps<SVGSVGElement>) => (
    <svg xmlns="http://www.w3.org/2000/svg" width={21} height={21} fill="none" {...props}>
        <g fill="currentColor" opacity={0.5}>
            <path d="M0 17.85V3.15A3.15 3.15 0 0 1 3.15 0h10.725a3.15 3.15 0 0 1 2.196.938h.001l3.99 3.99c.589.58.926 1.37.938 2.197V17.85A3.15 3.15 0 0 1 17.85 21H3.15A3.15 3.15 0 0 1 0 17.85m2.1 0a1.05 1.05 0 0 0 1.05 1.05h14.7a1.05 1.05 0 0 0 1.05-1.05V7.155l-.006-.103a1.05 1.05 0 0 0-.309-.632l-.008-.007v-.001L14.58 2.415a1.05 1.05 0 0 0-.632-.309l-.103-.006H3.15A1.05 1.05 0 0 0 2.1 3.15z" />
            <path d="M14.7 19.95V12.6H6.3v7.35a1.05 1.05 0 1 1-2.1 0V12.6a2.1 2.1 0 0 1 2.1-2.1h8.4a2.1 2.1 0 0 1 2.1 2.1v7.35a1.05 1.05 0 1 1-2.1 0M4.2 5.25v-4.2a1.05 1.05 0 1 1 2.1 0v4.2h7.35a1.05 1.05 0 1 1 0 2.1H6.3a2.1 2.1 0 0 1-2.1-2.1" />
        </g>
    </svg>
);
export default SvgSave;
