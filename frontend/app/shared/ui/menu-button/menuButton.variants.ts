import { tv } from "tailwind-variants";

export const menuButtonVariants = tv({
  base: "flex items-center justify-center disabled:cursor-not-allowed disabled:opacity-50",
  variants: {
    variant: {
      default:
        " gap-4 px-5 py-6 bg-gray rounded-[20px]" +
        " hover:bg-input-outline hover:drop-shadow-[0_0_60px_rgba(84,202,88,0.25)]" +
        " active:opacity-50",
      grow: "flex rounded-[20px] px-5 py-4 bg-input-outline ",
      icon: "p-2 rounded-[10px]",
    },
  },
});
