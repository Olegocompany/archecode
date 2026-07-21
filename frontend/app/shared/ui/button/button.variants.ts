import { tv } from "tailwind-variants";

export const buttonVariants = tv({
  base:
    "flex items-center justify-center w-full text-white box-border min-w-fit" +
    " disabled:cursor-default hover:cursor-pointer" +
    " p-5 " +
    " [box-shadow:0_0_12px_1px_rgba(2,255,11,0.3)]" +
    " transition-colors duration-200 ease-out" +
    " border-2 border-accent-base rounded-2xl text-xl font-semibold" +
    " bg-linear-to-r from-accent-light to-accent-dark" +
    " hover:from-accent-lighter hover:[box-shadow:0_0_12px_1px_rgba(2,255,11,0.45)]" +
    " active:from-accent-base active:text-white/80 active:opacity-80 active:shadow-none" +
    " focus:from-accent-lighter active:text-white/80" +
    " disabled:bg-none disabled:bg-gray disabled:border-light-gray disabled:text-white/50 disabled:[box-shadow:0_0_12px_1px_rgba(48,48,48,1.0)] disabled:active:opacity-100" +
    " leading-none",

  variants: {
    type: {
      accent: "",
      danger:
        "[box-shadow:0_0_12px_1px_rgba(218,43,43,0.5)] hover:[box-shadow:0_0_12px_1px_rgba(218,43,43,0.5)]" +
        " from-error to-error-dark border-error-light" +
        " hover:from-error-light hover:to-error" +
        " active:from-error active: to-error-dark active:opacity-80" +
        " focus:from-error focus:to-error-dark focus:opacity-80",
      solid:
        "[box-shadow:0_0_12px_1px_rgba(48,48,48,0.5)] hover:[box-shadow:0_0_12px_1px_rgba(48,48,48,0.5)]" +
        " bg-none bg-input-outline border-light-gray" +
        " hover:bg-light-gray hover:border-input-placeholder" +
        " active:bg-input-outline active:border-light-gray" +
        " focus:bg-input-outline focus:border-light-gray focus:text-white/80",
    },
  },
});
