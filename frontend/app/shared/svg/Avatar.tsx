import * as React from "react";
import type { SVGProps } from "react";
const SvgAvatar = (props: SVGProps<SVGSVGElement>) => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    width={26}
    height={32}
    fill="none"
    {...props}
  >
    <path
      stroke="currentColor"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={2}
      d="M25 31v-3.333a6.57 6.57 0 0 0-2.008-4.714A6.96 6.96 0 0 0 18.142 21H7.858a6.96 6.96 0 0 0-4.849 1.953A6.57 6.57 0 0 0 1 27.667V31M13 14.333c3.788 0 6.858-2.984 6.858-6.666S16.788 1 13 1 6.143 3.985 6.143 7.667s3.07 6.666 6.858 6.666"
    />
  </svg>
);
export default SvgAvatar;
