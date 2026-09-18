import styles from './GradientLayout.module.css';

export function GradientLayout() {
    return (
        <div className="relative min-h-screen w-full overflow-hidden">
            <div className="absolute inset-0 bg-gradient-to-b from-gray-950 via-gray-900 to-gray-950" />
            <span
                className={` absolute opacity-80 w-[20%] h-[30%] left-89 -top-35 rounded-full blur-[320px]  ${styles.gradient}`}
            />
            <span
                className={` absolute opacity-80 w-[40%] h-[65%] left-60 top-170 rounded-full blur-[140px] ${styles.circleOne} ${styles.gradient}`}
            />
            <span
                className={`absolute opacity-80 w-[35%] h-[65%] -right-50 -top-40 rounded-full blur-[300px] ${styles.circle} ${styles.gradient}`}
            />
        </div>
    );
}

export default GradientLayout;
