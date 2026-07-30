import * as React from "react";
import type { SVGProps } from "react";
const SvgCloseEye = (props: SVGProps<SVGSVGElement>) => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    width={22}
    height={12}
    fill="none"
    {...props}
  >
    <path
      stroke="#404040"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={2}
      d="m14 11-.722-3.25M1 1a10.645 10.645 0 0 0 20 0M19 8l-1.726-2.05M3 8l1.726-2.05M8 11l.722-3.25"
    />
  </svg>
);
export default SvgCloseEye;
