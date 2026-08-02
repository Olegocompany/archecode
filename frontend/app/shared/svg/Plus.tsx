import * as React from "react";
import type { SVGProps } from "react";
const SvgPlus = (props: SVGProps<SVGSVGElement>) => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    width={32}
    height={32}
    fill="none"
    {...props}
  >
    <path
      stroke="currentColor"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={2}
      d="M3.79 10.924A6 6 0 0 1 7.979 3.86a6 6 0 0 1 2.981-.092 6 6 0 0 1 10.108 0 5.997 5.997 0 0 1 7.077 4.184c.282.972.313 2 .091 2.987a6 6 0 0 1 0 10.11 6 6 0 0 1-4.172 7.073 6 6 0 0 1-2.981.098 6 6 0 0 1-10.123 0 5.998 5.998 0 0 1-7.168-7.155 6 6 0 0 1 0-10.141M16.013 9.993v12.001M10.014 15.994h11.998"
    />
  </svg>
);
export default SvgPlus;
