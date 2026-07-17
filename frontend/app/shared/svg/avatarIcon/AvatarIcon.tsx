import * as React from "react";
import { SVGProps } from "react";
const AvatarIcon = (props: SVGProps<SVGSVGElement>) => (
  <svg
    {...props}
    xmlns="http://www.w3.org/2000/svg"
    fill="none"
    className="max-w-7 max-h-7 drop-shadow-[0_0_6px_var(--white)]"
  >
    <path
      stroke="currentColor"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={2}
      d="M25 31v-3.333a6.574 6.574 0 0 0-2.008-4.714A6.958 6.958 0 0 0 18.142 21H7.858a6.958 6.958 0 0 0-4.849 1.953A6.574 6.574 0 0 0 1 27.667V31M13 14.333c3.788 0 6.858-2.984 6.858-6.666C19.858 3.985 16.788 1 13 1 9.213 1 6.143 3.985 6.143 7.667s3.07 6.666 6.858 6.666Z"
    />
  </svg>
);
export default AvatarIcon;
