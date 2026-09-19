'use client';
import { Button, Link } from '@shared-ui';

interface errorData {
    error: Error & { digest?: string };
    retry: () => void;
}

export default function Error({ error, retry }: errorData) {
    return (
        <div
            className={
                'flex flex-col gap-40 items-center justify-center w-full h-screen bg-dark-gray text-white text-Montserrat'
            }
        >
            <div className={'flex flex-col items-center justify-center '}>
                <p className={'text-[256px] font-bold'}>404</p>
                <div>
                    <p className={'text-Jost text-6xl'}>Произошла ошибка...</p>
                    <p className={'text-3xl'}>Что-то пошло не так при загрузке страницы</p>
                </div>
            </div>
            <div className={'flex gap-5 min-w-[60%]'}>
                <Button variantButton={'accent'} onClick={() => retry()}>
                    Попробовать ещё раз
                </Button>
                <div className={'w-full'}>
                    <Link to={'/oleg'}>
                        <Button variantButton={'solid'}>На главную</Button>
                    </Link>
                </div>
            </div>
        </div>
    );
}
