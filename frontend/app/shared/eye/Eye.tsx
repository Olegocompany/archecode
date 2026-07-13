import { CloseEye, OpenEye } from "@/app/shared/svg";

interface EyeProps {
  state: boolean;
}

function Eye({ state = true }: EyeProps) {
  return (
    <>
      {state ? (
        <OpenEye className="w-full h-full cursor-pointer text-primary-dark  hover:text-lighter-gray active:text-input-placeholder transition duration-300" />
      ) : (
        <CloseEye className="w-full h-full cursor-pointer text-primary-dark  hover:text-lighter-gray active:text-input-placeholder transition duration-300" />
      )}
    </>
  );
}

export default Eye;
