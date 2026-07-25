"use client";
import { Widget } from "@/app/auth";
import { Button, Input, Link } from "@shared-ui";

export default function Auth() {
  // будущий layout первый div
  return (
    <div className="w-full h-full bg-linear-50 from-dark-gray to-gray py-[90px] px-[150px] flex items-center justify-center">
      <div className="grid grid-cols-2 w-full h-full bg-linear-[236deg] from-gray to-dark-gray rounded-[50px] p-[10px] [box-shadow:0_16px_16px_0_rgba(0, 0, 0, 0.45)]">
        <div className="flex flex-col gap-[40px] pr-[122px] pl-[100px] pt-[140px] pb-[40px]">
          <h2 className="text-[60px] text-white">Создание аккаунта</h2>
          <form className="flex flex-col gap-[40px]">
            <div className="flex flex-col gap-[20px]">
              <Input type={"text"} placeholder={"Логин"} />
              <div className="grid grid-rows-1 grid-cols-2 gap-[20px]">
                <Input type={"text"} placeholder={"Имя"} />
                <Input type={"text"} placeholder={"Фамилия"} />
              </div>
              <Input type={"email"} placeholder={"Почта"} />
              <Input type={"password"} placeholder={"Пароль"} />
              <Input type={"password"} placeholder={"Повторите пароль"} />
            </div>
            <div className={"flex flex-col items-center gap-[15px]"}>
              <Button variantButton="accent" type={"submit"}>
                Создать аккаунт
              </Button>
              <div className={"flex items-center gap-[5px]"}>
                <p className={"text-white"}>У вас есть аккаунт?</p>
                <Link to="/">Войти</Link>
              </div>
            </div>
          </form>
        </div>
        <Widget />
      </div>
    </div>
  );
}
