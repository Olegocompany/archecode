"use client";
import { ViewTransition } from "react";
import { usePathname } from "next/navigation";
import { Widget, Register, Login } from "@/app/auth";

export default function Part() {
  const pathname = usePathname();

  return (
    <div className="w-full h-screen bg-linear-50 from-dark-gray to-gray py-[40px] px-[150px] flex items-center justify-center">
      <div
        className={
          "grid grid-cols-2 w-full h-full bg-linear-[236deg] from-gray to-dark-gray rounded-[50px] p-[10px] [box-shadow:0_16px_16px_0_rgba(0, 0, 0, 0.45)] relative"
        }
      >
        <Register />
        <ViewTransition name="widgett">
          <div
            id="widget"
            className={` absolute inset-2.5 flex items-center justify-center w-[50%] transition duration-300 ${pathname === "/auth/register" ? " left-[50%] " : " left-0"}  `}
          >
            <Widget />
          </div>
        </ViewTransition>
        <Login />
      </div>
    </div>
  );
}
