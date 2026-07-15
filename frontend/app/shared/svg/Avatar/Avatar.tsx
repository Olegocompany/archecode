import * as React from "react";
import { SVGProps } from "react";
const Avatar = (props: SVGProps<SVGSVGElement>) => (
  <svg {...props} xmlns="http://www.w3.org/2000/svg" fill="none">
    <g
      stroke="currentColor"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={2}
      filter="url(#a)"
    >
      <path d="M31 37v-3.333a6.574 6.574 0 0 0-2.008-4.714A6.958 6.958 0 0 0 24.142 27H13.858a6.958 6.958 0 0 0-4.849 1.953A6.574 6.574 0 0 0 7 33.667V37M19 20.333c3.788 0 6.858-2.984 6.858-6.666C25.858 9.985 22.788 7 19 7c-3.787 0-6.857 2.985-6.857 6.667s3.07 6.666 6.857 6.666Z" />
    </g>
    <defs>
      <filter
        id="a"
        width={38}
        height={44}
        x={0}
        y={0}
        colorInterpolationFilters="sRGB"
        filterUnits="userSpaceOnUse"
      >
        <feFlood floodOpacity={0} result="BackgroundImageFix" />
        <feColorMatrix
          in="SourceAlpha"
          result="hardAlpha"
          values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"
        />
        <feOffset />
        <feGaussianBlur stdDeviation={3} />
        <feColorMatrix values="0 0 0 0 0.980392 0 0 0 0 0.980392 0 0 0 0 0.980392 0 0 0 1 0" />
        <feBlend in2="BackgroundImageFix" result="effect1_dropShadow_284_359" />
        <feBlend
          in="SourceGraphic"
          in2="effect1_dropShadow_284_359"
          result="shape"
        />
      </filter>
    </defs>
  </svg>
);
export default Avatar;
