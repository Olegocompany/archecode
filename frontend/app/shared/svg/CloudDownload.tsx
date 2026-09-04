import * as React from 'react';
import type { SVGProps } from 'react';
const SvgCloudDownload = (props: SVGProps<SVGSVGElement>) => (
    <svg xmlns="http://www.w3.org/2000/svg" width={22} height={20} fill="none" {...props}>
        <g
            stroke="currentColor"
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth={2}
            opacity={0.3}
        >
            <path d="M10.998 11v8l-4-4M10.998 19l4-4" />
            <path d="M3.391 13.269a7 7 0 1 1 11.317-7.27h1.79a4.5 4.5 0 0 1 2.436 8.285" />
        </g>
    </svg>
);
export default SvgCloudDownload;
