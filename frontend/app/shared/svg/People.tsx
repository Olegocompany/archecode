import * as React from 'react';
import type { SVGProps } from 'react';
const SvgPeople = (props: SVGProps<SVGSVGElement>) => (
    <svg xmlns="http://www.w3.org/2000/svg" width={22} height={20} fill="none" {...props}>
        <g
            stroke="currentColor"
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth={2}
            opacity={0.3}
        >
            <path d="M15 19v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2M15 1.128a4 4 0 0 1 0 7.744M21 19v-2a4 4 0 0 0-3-3.87M8 9a4 4 0 1 0 0-8 4 4 0 0 0 0 8" />
        </g>
    </svg>
);
export default SvgPeople;
