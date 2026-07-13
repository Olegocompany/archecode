import { tv } from "tailwind-variants";

export const inputVariants = tv({
  base:
    "flex w-full text-white placeholder:text-input-placeholder px-5 py-5.5 bg-transparent rounded-2xl border-2 border-input-outline transition duration-300" +
    " hover:placeholder:text-placeholder-hover hover:bg-white/5 hover:shadow-[0_0_12px_1px_rgba(2,255,11,0.3)]" +
    " active:placeholder:text-placeholder-active active:bg-white/10" +
    " disabled:cursor-not-allowed disabled:opacity-50" +
    " focus:outline-none! focus-visible:outline-none! focus:shadow-[0_0_20px_1px_rgba(2,255,11,0.3)]",
  variants: {
    error: {
      true:
        "text-error placeholder:text-error/50 border-error" +
        " hover:placeholder:text-initial hover:shadow-[0_0_12px_3px_rgba(218,43,43,0.4)]" +
        " active:placeholder:text-initial" +
        " focus:shadow-[0_0_20px_3px_rgba(218,43,43,0.6)]",
      false: "",
    },
  },
});
