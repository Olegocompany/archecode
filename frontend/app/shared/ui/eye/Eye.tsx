import { CloseEye, OpenEye } from "@/app/shared/svg";

interface EyeProps {
  state?: boolean;
  handleClick?: () => void;
}

function Eye({ handleClick, state = true }: EyeProps) {
  return (
    <>
      {state ? (
        <OpenEye
          onClick={handleClick}
          className=" min-w-6 max-h-4 cursor-pointer text-primary-dark  hover:text-lighter-gray active:text-input-placeholder transition duration-300"
        />
      ) : (
        <CloseEye
          onClick={handleClick}
          className=" min-w-6 max-h-3 cursor-pointer text-primary-dark  hover:text-lighter-gray active:text-input-placeholder transition duration-300"
        />
      )}
    </>
  );
}

export default Eye;
