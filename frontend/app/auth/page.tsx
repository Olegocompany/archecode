import { ReactNode } from "react";
import Widget from "@/app/auth/_components/Widget";

export default function Auth() {
  return (
    <div className="w-full h-screen bg-linear-50 from-dark-gray to-gray py-[90px] px-[150px] flex items-center justify-center">
      <div className="grid grid-cols-2 w-full h-full bg-linear-30 from-dark-gray to-gray rounded-[50px] p-[10px]">
        <div />
        <Widget />
      </div>
    </div>
  );
}
