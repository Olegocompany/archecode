"use client";
import { ViewTransition } from "react";
import { usePathname } from "next/navigation";
import { Widget, Register, Login } from "@/app/auth";
import styles from "./page.module.css";

export default function Part() {
  const pathname = usePathname();

  return (
    <div className="w-full h-screen py-[40px] px-[150px] flex items-center justify-center ">
      <div
        className={
          ` grid grid-cols-2 w-full h-full rounded-[50px] p-[10px] shadow-[0_16px_16px_0_rgba(0,0,0,0.45)] relative  ` +
          styles.blockForm
        }
      >
        <Register />
        <ViewTransition name="widget">
          <div
            className={` absolute inset-2.5 flex items-center justify-center w-[50%] transition duration-300 ${pathname === "/auth/register" ? " left-[calc(50%-10px)] " : " left-[10px]"}  `}
          >
            <Widget />
          </div>
        </ViewTransition>
        <Login />
      </div>
    </div>
  );
}
