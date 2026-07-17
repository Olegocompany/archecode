import * as React from "react";
import { SVGProps } from "react";
const ExitIcon = (props: SVGProps<SVGSVGElement>) => (
  <svg
    {...props}
    xmlns="http://www.w3.org/2000/svg"
    fill="none"
    className="drop-shadow-[0_0_6px_var(--white)]"
  >
    <path
      stroke="currentColor"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={2}
      d="M18.333 19.667 25 13l-6.667-6.667M25 13H9M9 25H3.667A2.667 2.667 0 0 1 1 22.333V3.667A2.667 2.667 0 0 1 3.667 1H9"
    />
  </svg>
);
export default ExitIcon;
