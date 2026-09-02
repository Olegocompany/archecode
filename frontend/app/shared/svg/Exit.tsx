import * as React from 'react';
import type { SVGProps } from 'react';
const SvgExit = (props: SVGProps<SVGSVGElement>) => (
    <svg xmlns="http://www.w3.org/2000/svg" width={26} height={26} fill="none" {...props}>
        <path
            stroke="currentColor"
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth={2}
            d="M18.333 19.667 25 13l-6.667-6.666M25 13H9M9 25H3.667A2.667 2.667 0 0 1 1 22.333V3.667A2.667 2.667 0 0 1 3.667 1H9"
        />
    </svg>
);
export default SvgExit;
