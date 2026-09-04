import * as React from 'react';
import type { SVGProps } from 'react';
const SvgCheckMark = (props: SVGProps<SVGSVGElement>) => (
    <svg xmlns="http://www.w3.org/2000/svg" width={9} height={7} fill="none" {...props}>
        <path
            stroke="currentColor"
            strokeLinecap="round"
            strokeLinejoin="round"
            d="M8.5.5 3 6.5.5 3.773"
        />
    </svg>
);
export default SvgCheckMark;
