"use client";
import { Input, Button, Link } from "@shared-ui";

export default function Register() {
  return (
    <div className="flex flex-col gap-[40px] pr-[122px] pl-[100px] pt-[140px] pb-[40px]">
      <h2 className="text-[60px] text-white font-jost font-semibold text-center">
        Создание аккаунта
      </h2>
      <form className="flex flex-col gap-[40px]">
        <div className="flex flex-col gap-[20px]">
          <Input type="text" placeholder="Логин" />
          <div className="grid grid-rows-1 grid-cols-2 gap-[20px]">
            <Input type="text" placeholder="Имя" />
            <Input type="text" placeholder="Фамилия" />
          </div>
          <Input type="email" placeholder="Почта" />
          <Input type="password" placeholder="Пароль" />
          <Input type="password" placeholder="Повторите пароль" />
        </div>
        <div className="flex flex-col items-center gap-[15px]">
          <Button variantButton="accent" type="submit">
            Создать аккаунт
          </Button>
          <div className="flex items-center gap-[5px]">
            <p className="text-white font-montserrat font-normal text-[18px]">
              У вас есть аккаунт?
            </p>
            <div className="text-[18px]">
              <Link to="/auth/login">Войти</Link>
            </div>
          </div>
        </div>
      </form>
    </div>
  );
}
