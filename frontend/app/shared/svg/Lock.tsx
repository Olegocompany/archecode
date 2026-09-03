import * as React from 'react';
import type { SVGProps } from 'react';
const SvgLock = (props: SVGProps<SVGSVGElement>) => (
    <svg xmlns="http://www.w3.org/2000/svg" width={17} height={17} fill="none" {...props}>
        <g
            stroke="currentColor"
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth={2}
            opacity={0.3}
        >
            <path d="M14.333 7.75H2.667C1.747 7.75 1 8.422 1 9.25v5.25c0 .828.746 1.5 1.667 1.5h11.666c.92 0 1.667-.672 1.667-1.5V9.25c0-.828-.746-1.5-1.667-1.5M4.333 7.75v-3c0-.995.44-1.948 1.22-2.652C6.335 1.395 7.395 1 8.5 1s2.165.395 2.946 1.098 1.22 1.657 1.22 2.652v3" />
        </g>
    </svg>
);
export default SvgLock;
