import { CloseEye, OpenEye } from "@/app/shared/svg";

interface EyeProps {
  state?: "open" | "close";
}

function Eye({ state = "open" }: EyeProps) {
  return (
    <>
      {state === "open" ? (
        <OpenEye className="w-full h-full cursor-pointer text-primary-dark  hover:text-lighter-gray active:text-input-placeholder transition duration-300" />
      ) : (
        <CloseEye className="w-full h-full cursor-pointer text-primary-dark  hover:text-lighter-gray active:text-input-placeholder transition duration-300" />
      )}
    </>
  );
}

export default Eye;
