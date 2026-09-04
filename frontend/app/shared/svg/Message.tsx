import * as React from 'react';
import type { SVGProps } from 'react';
const SvgMessage = (props: SVGProps<SVGSVGElement>) => (
    <svg xmlns="http://www.w3.org/2000/svg" width={22} height={20} fill="none" {...props}>
        <g
            stroke="currentColor"
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth={2}
            opacity={0.3}
        >
            <path d="M21 14.263c0 .502-.21.984-.586 1.34a2.06 2.06 0 0 1-1.414.555H5.828c-.53 0-1.039.2-1.414.555l-2.202 2.086a.72.72 0 0 1-.364.184.75.75 0 0 1-.41-.038.7.7 0 0 1-.318-.248.65.65 0 0 1-.12-.374V2.895c0-.503.21-.985.586-1.34A2.06 2.06 0 0 1 3 1h16c.53 0 1.04.2 1.414.555.375.355.586.837.586 1.34zM11 8.579h.01M15 8.579h.01M7 8.579h.01" />
        </g>
    </svg>
);
export default SvgMessage;
