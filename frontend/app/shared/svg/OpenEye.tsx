import * as React from 'react';
import type { SVGProps } from 'react';
const SvgOpenEye = (props: SVGProps<SVGSVGElement>) => (
    <svg xmlns="http://www.w3.org/2000/svg" width={22} height={16} fill="none" {...props}>
        <path
            stroke="currentColor"
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth={2}
            d="M1.063 7.651a1 1 0 0 0 0 .696 10.75 10.75 0 0 0 19.876 0 1 1 0 0 0 0-.696 10.75 10.75 0 0 0-19.876 0"
        />
        <path
            stroke="currentColor"
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth={2}
            d="M11 10.999a3 3 0 1 0 0-6 3 3 0 0 0 0 6"
        />
    </svg>
);
export default SvgOpenEye;
