import * as React from 'react';
import type { SVGProps } from 'react';
const SvgCross = (props: SVGProps<SVGSVGElement>) => (
    <svg xmlns="http://www.w3.org/2000/svg" width={15} height={15} fill="none" {...props}>
        <g fill="currentColor" opacity={0.3}>
            <path d="M13.171.314a1.071 1.071 0 1 1 1.515 1.515L1.83 14.686a1.071 1.071 0 1 1-1.515-1.515z" />
            <path d="M.314.314a1.07 1.07 0 0 1 1.515 0L14.686 13.17a1.071 1.071 0 1 1-1.515 1.515L.314 1.83a1.07 1.07 0 0 1 0-1.515" />
        </g>
    </svg>
);
export default SvgCross;
