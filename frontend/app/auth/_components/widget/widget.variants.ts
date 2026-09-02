import { tv } from "tailwind-variants";

export const widgetVariants = tv({
  slots: {
    widgetBlock:
      "flex w-full h-full bg-none relative overflow-hidden rounded-[40px] ",
    widgetBlockPicture: "w-full flex relative ",
    widgetPicture:
      "shrink-0 widget w-full h-full bg-cover bg-center bg-no-repeat rounded-[40px] flex justify-end items-center overflow-hidden ",
    widgetBlockContent:
      "absolute h-full flex w-full bottom-0 items-center justify-end py-[40px] flex-col gap-[34px] ",
    widgetBlockTitle: "text-center flex flex-col gap-1 ",
    widgetTitle: "text-[32px] text-white transition duration-300 font-jost ",
    widgetSubtitle:
      "text-[18px] text-white/70 transition duration-300 font-montserrat ",
    widgetBlockPoints: "relative justify-center items-center ",
  },
});
