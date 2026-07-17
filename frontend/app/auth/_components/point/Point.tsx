"use client"
import {ReactNode, useEffect, useRef} from "react";

interface PointProps {
    point: boolean;
    cycleStart: number;
    progress: number;
}

export default function Point ({point, progress} : PointProps ) {
    const elementCircle = useRef<SVGCircleElement>(null)

    useEffect(() => {
        const progressInterval = setInterval(() => {
            if (elementCircle.current) elementCircle.current.style.setProperty("--progress", `${progress}%`);
        })
        return () => {
            clearInterval(progressInterval)
        }
    },[progress])


    return(
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
                            className="absolute progresss stroke-accent-base opacity-80"
                            ref={elementCircle}
                        />
                    )}
                </g>
            </svg>
    )
}