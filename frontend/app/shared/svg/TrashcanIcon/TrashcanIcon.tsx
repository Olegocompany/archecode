import * as React from "react";
import { SVGProps } from "react";
const TrashcanIcon = (props: SVGProps<SVGSVGElement>) => (
  <svg {...props} xmlns="http://www.w3.org/2000/svg" fill="none">
    <g
      stroke="currentColor"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={2}
      opacity={0.5}
    >
      <path d="M14.333 4.4v11.9c0 .45-.176.883-.489 1.202a1.65 1.65 0 0 1-1.178.498H4.333a1.65 1.65 0 0 1-1.179-.498 1.717 1.717 0 0 1-.488-1.202V4.4M1 4.4h15M5.167 4.4V2.7c0-.45.176-.883.488-1.202A1.65 1.65 0 0 1 6.834 1h3.333c.442 0 .866.18 1.178.498.313.319.489.751.489 1.202v1.7" />
    </g>
  </svg>
);
export default TrashcanIcon;
