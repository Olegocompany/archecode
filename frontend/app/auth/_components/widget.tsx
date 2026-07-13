"use client";
import { ReactNode, useEffect, useState } from "react";

export default function Widget() {
  const time = 3000;
  const [point, setPoint] = useState([true, false, false]);
  const [positionPoint, setPositionPoint] = useState(0);

  const changePoint = () => {
    const newPoint = [...point];
    const nextIndex = (positionPoint + 1) % point.length;
    newPoint[positionPoint] = false;
    newPoint[nextIndex] = true;

    console.log(point);

    setPoint(newPoint);
    setPositionPoint(nextIndex);
  };

  useEffect(() => {
    setTimeout(changePoint, time);
    return () => {};
  }, [changePoint, positionPoint]);

  return (
    <div className="flex w-full h-fullbg-white relative">
      <div
        className="widget w-full h-full bg-cover bg-center bg-no-repeat rounded-[40px] flex justify-end items-center"
        style={{ backgroundImage: "url('/widget/photo-one.png')" }}
      ></div>
      <div className="absolute h-full flex w-full bottom-0 items-center justify-end py-[40px] flex-col gap-[34px]">
        <div className="text-center flex flex-col gap-1">
          <p className="text-[32px] text-white">
            Оптимизируй работу своей команды
          </p>
          <p className="text-[18px] text-white/70">
            Создай свою команду и следим за движениями
          </p>
        </div>
        <div className="flex gap-[36px]">
          {point.map((i, index) => (
            <div
              className={
                `size-[30px] rounded-full flex justify-center items-center ` +
                `${i ? "bg-accent-base/50" : ""}`
              }
              key={index}
            >
              <div
                className="size-[14px] bg-white rounded-full"
                style={{
                  backgroundColor: i ? "var(--accent-base)" : "var(--white)",
                }}
              />
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
