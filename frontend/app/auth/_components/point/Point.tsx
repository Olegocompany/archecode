"use client";
import { ReactNode } from "react";

interface PointProps {
  point: boolean;
  progress: number;
}

export default function Point({ point, progress }: PointProps) {

  return (
    <svg
      width="40"
      height="40"
      xmlns="http://www.w3.org/2000/svg"
      style={{ transform: "rotate(-90deg)" }}
    >
      <g className="relative flex justify-center items-center">
        <circle
          cx="20"
          cy="20"
          r="7"
          style={{
            fill: point ? " var(--accent-base) " : "var(--white) ",
          }}
        />

        {point && (
          <circle
            cx="20"
            cy="20"
            r="11"
            stroke="black"
            strokeWidth="8"
            fill="none"
            id="circle"
            className="absolute progress stroke-accent-base opacity-80 transition duration-300"
            style={{strokeDasharray: `${progress}, ${2 * 3.14 * 11}`, strokeDashoffset: 0  }}
          />
        )}
      </g>
    </svg>
  );
}
