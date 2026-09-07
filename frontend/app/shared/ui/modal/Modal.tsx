import { ReactNode, memo } from 'react';
import { MenuButton } from '../menu-button';
import { Cross } from '../../svg';

interface ModalProps {
    title?: string;
    children?: ReactNode;
    handleClose: () => void;
}

const Modal = memo(function Modal({ title, children, handleClose }: ModalProps) {
    return (
        <div className="absolute inset-0 flex items-center justify-center">
            <div className="absolute inset-0 bg-black/70"></div>
            <div className="py-5 px-7.5 flex flex-col gap-3.75 min-w-xs bg-gray rounded-[30px] relative">
                <div className="w-full flex justify-between">
                    <h3 className="font-jost text-[32px] text-white">{title}</h3>
                    <MenuButton
                        className="absolute top-5 right-5"
                        variant="icon"
                        bgColor="gray"
                        handleClick={handleClose}
                    >
                        <Cross />
                    </MenuButton>
                </div>
                {children}
            </div>
        </div>
    );
});

export default Modal;
