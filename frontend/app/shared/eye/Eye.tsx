import { CloseEye, OpenEye } from "@/app/shared/svg";

interface EyeProps {
  state: "open" | "close";
}

function Eye({ state }: EyeProps) {
  return (
    <>
      {state === "open" ? (
        <OpenEye className="w-full h-full text-primary-dark cursor-pointer hover:text-primary active:text-input-placeholder transition duration-300" />
      ) : (
        <CloseEye className="w-full h-full text-primary-dark cursor-pointer hover:text-primary active:text-input-placeholder transition duration-300" />
      )}
    </>
  );
}

export default Eye;
