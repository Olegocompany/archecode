"use client";

import { Input, Button } from "@shared-ui";
import Link from "next/link";

export default function Login() {
  return (
    <div className="flex w-full flex-col gap-[40px] px-[80px] justify-center">
      <h2 className="text-[60px] text-white font-jost font-semibold text-center">
        Вход в аккаунт
      </h2>
      <form className="flex flex-col gap-[40px]">
        <div className="flex flex-col gap-[20px]">
          <Input type="text" placeholder="Логин или почта" />
          <Input type="password" placeholder="Пароль" />
        </div>
        <div className="flex flex-col items-center gap-[15px]">
          <Button variantButton="accent" type="submit">
            Создать аккаунт
          </Button>
          <div className="flex items-center gap-[5px]">
            <p className="text-white font-montserrat font-normal text-[18px]">
              Впервые на платформе?
            </p>
            <div className="text-[18px]">
              <Link href="/auth/register">Зарегистрироваться</Link>
            </div>
          </div>
        </div>
      </form>
    </div>
  );
}
