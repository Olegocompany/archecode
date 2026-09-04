import { Bell } from '@/app/shared/svg';

interface NotificationProps {
    quantity: string | number;
    handleClick?: () => void;
}

function Notification({ quantity, handleClick }: NotificationProps) {
    return (
        <div className="flex items-center text-sm justify-center gap-[2px] p-1 rounded-[10px] bg-white text-input-outline font-montserrat drop-shadow-[0_0_12px_var(--white)]">
            <Bell />
            <p>{quantity}</p>
        </div>
    );
}

export default Notification;
