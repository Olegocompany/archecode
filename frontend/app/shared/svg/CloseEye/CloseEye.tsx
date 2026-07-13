import * as React from "react";
import { SVGProps } from "react";
const CloseEye = (props: SVGProps<SVGSVGElement>) => (
  <svg {...props} xmlns="http://www.w3.org/2000/svg" fill="none">
    <path
      stroke="currentColor"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={2}
      d="m14 11-.722-3.25M1 1a10.645 10.645 0 0 0 20 0M19 8l-1.726-2.05M3 8l1.726-2.05M8 11l.722-3.25"
    />
  </svg>
);
export default CloseEye;
