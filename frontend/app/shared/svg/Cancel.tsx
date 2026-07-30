import * as React from "react";
import type { SVGProps } from "react";
const SvgCancel = (props: SVGProps<SVGSVGElement>) => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    width={22}
    height={22}
    fill="none"
    {...props}
  >
    <g fill="currentColor" opacity={0.5}>
      <path d="M20 11a9 9 0 1 0-18 0 9 9 0 0 0 18 0m2 0c0 6.075-4.925 11-11 11S0 17.075 0 11 4.925 0 11 0s11 4.925 11 11" />
      <path d="M3.222 3.222a1 1 0 0 1 1.414 0l14.14 14.142a1 1 0 0 1-1.413 1.414L3.222 4.636a1 1 0 0 1 0-1.414" />
    </g>
  </svg>
);
export default SvgCancel;
