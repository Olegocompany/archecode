"use client";
import {
  useCallback,
  useEffect,
  useEffectEvent,
  useRef,
  useState,
} from "react";
import { Point } from "@/app/auth";
import styles from "./widget.module.css";

export default function Widget() {
  const [point, setPoint] = useState([true, false, false]);
  const [positionPoint, setPositionPoint] = useState(0);
  const elementPicture = useRef<HTMLDivElement>(null);
  const elementBlockPicture = useRef<HTMLDivElement>(null);
  const [progress, setProgress] = useState(1);
  const [onAnimation, setOnAnimation] = useState(true);
  const listPicture = [
    "/images/widget/photo-one.png",
    "/images/widget/photo-two.png",
    "/images/widget/photo-three.png",
    "/images/widget/photo-one.png",
  ];
  const listText = [
    [
      "Оптимизируй работу своей команды",
      "Создай свою команду и следим за движениями",
    ],
    [
      "Создай свою команду и следим за движениями",
      "Оптимизируй работу своей команды",
    ],
    ["ОпОПОПОПОПОПОП своееее zzzzz", "Оптимизируй работу своей команды"],
  ];
  const [currentPicture, setCurrentPicture] = useState(0);
  const [cycleStart, setCycleStart] = useState(new Date().getTime());

  const changePicture = () => {
    setOnAnimation(true);
    setCurrentPicture((currentPicture + 1) % 4);
  };

  const changePoint = () => {
    const newPoint = [...point];
    const nextIndex = (positionPoint + 1) % point.length;

    newPoint[positionPoint] = false;
    newPoint[nextIndex] = true;

    setProgress(1);
    setPoint(newPoint);
    setPositionPoint(nextIndex);

    setCycleStart(new Date().getTime());
    changePicture();
  };

  const changePointAdapter = useEffectEvent(changePoint);

  const getSecondsDiff = () => {
    return ((new Date().getTime() - cycleStart) / 1000) % 60;
  };

  const changeProgress = () => {
    setProgress(1 + (75 - 1) * (getSecondsDiff() / 3));
  };

  const changeProgressAdapter = useEffectEvent(changeProgress);

  const time = useCallback(() => {
    if (currentPicture === 3) {
      setOnAnimation(false);
      setCurrentPicture(0);
    }
  }, [currentPicture]);

  useEffect(() => {
    const pictureInterval = setInterval(time, 500);
    return () => {
      clearInterval(pictureInterval);
    };
  }, [time]);

  useEffect(() => {
    const progressInterval = setInterval(changeProgressAdapter, 50);
    const interval = setInterval(changePointAdapter, 3000);
    return () => {
      clearInterval(interval);
      clearInterval(progressInterval);
    };
  }, []);

  return (
    <div className="flex w-full h-full bg-white relative overflow-hidden rounded-[40px]">
      <div
        className="w-full flex "
        ref={elementBlockPicture}
        style={{
          transform: `translateX(-${100 * currentPicture}%) `,
          transition: onAnimation ? "all 0.3s linear" : "none",
        }}
      >
        {listPicture.map((item, i) => (
          <div
            className={
              "shrink-0 widget w-full h-full bg-cover bg-center bg-no-repeat rounded-[40px] flex justify-end items-center overflow-hidden " +
              styles.widget
            }
            style={{ backgroundImage: `url(${item})` }}
            ref={elementPicture}
            key={i}
          />
        ))}
      </div>
      <div className="absolute h-full flex w-full bottom-0 items-center justify-end py-[40px] flex-col gap-[34px]">
        <div className="text-center flex flex-col gap-1">
          <p
            className={
              "text-[32px] text-white transition duration-300 " +
              styles.widgetText
            }
          >
            {listText[positionPoint][0]}
          </p>
          <p
            className={
              "text-[18px] text-white/70 transition duration-300 " +
              styles.widgetText
            }
          >
            {listText[positionPoint][1]}
          </p>
        </div>
        <div className="flex gap-[36px]">
          {point.map((i, index) => (
            <div className="relative justify-center items-center" key={index}>
              <Point point={i} progress={progress} />
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
