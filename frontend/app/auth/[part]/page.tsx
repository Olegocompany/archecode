import { Widget } from "@/app/auth";
import Register from "@/app/auth/[part]/_component/forms/register";
import Login from "@/app/auth/[part]/_component/forms/login";

export default async function part({
  params,
}: {
  params: Promise<{ part: string }>;
}) {
  const { part } = await params;

  // будущий layout -- первый div
  return (
    <div className="w-full h-full bg-linear-50 from-dark-gray to-gray py-[40px] px-[150px] flex items-center justify-center">
      <div
        className={
          "grid grid-cols-2 w-full h-full bg-linear-[236deg] from-gray to-dark-gray rounded-[50px] p-[10px] [box-shadow:0_16px_16px_0_rgba(0, 0, 0, 0.45)] relative"
        }
      >
        <div className="absolute flex items-center justify-center w-full h-full z-30">
          <Widget />
        </div>
        <Register />
        <Login />
      </div>
    </div>
  );
}
