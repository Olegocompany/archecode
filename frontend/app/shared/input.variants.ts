import { tv } from 'tailwind-variants'

export const inputVariants = tv({
    base: 'flex w-full focus:outline-none placeholder:text-input-placeholder px-5 py-5.5 bg-transparent rounded-2xl border-2 border-input-outline transition duration-300' +
        ' hover:placeholder:text-placeholder-hover  hover:bg-green hover:shadow-[0_4px_12px_1px_rgba(2,255,11,0.1)]' +
        ' active:placeholder:text-placeholder-active active:bg-white/10' +
        ' disabled:cursor-not-allowed disabled:opacity-50' +
        ' focus-visible:bg-white/3 focus-visible:outline-5 focus-visible:outline-solid focus-visible:outline-accent-light' +
        ' focus-visible:outline-offset-3',
    variants: {
        error: {
            true: 'active:placeholder:text-error hover:placeholder:text-initial border-error placeholder:text-error',
            false: '',
        }
    }
})
