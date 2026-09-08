function GradientLayout() {
    return (
        <div className={'relative h-screen w-screen bg-dark-gray overflow-hidden'}>
            <div className={'bg-[linear-gradient(180deg,#00ffe1_0%,rgba(37,239,37,0)_100%)] '}>
                <span
                    className={
                        'absolute opacity-80 w-89 h-89 left-1/5 -top-1/6 rounded-full bg-[linear-gradient(180deg,#00ffe1_0%,rgba(37,239,37,0)_100%)] blur-[400px]'
                    }
                />
                <span
                    className={
                        'absolute opacity-80 w-220 h-220 left-1/10 top-4/7 rounded-full bg-[linear-gradient(180deg,#00ffe1_0%,rgba(37,239,37,0)_100%)] blur-[140px]'
                    }
                />
                <span
                    className={
                        'absolute opacity-80 w-181 h-181 left-2/3 -top-1/7 rounded-full bg-[linear-gradient(180deg,#00ffe1_0%,rgba(37,239,37,0)_100%)] blur-[300px]'
                    }
                />
            </div>
        </div>
    );
}

export default GradientLayout;
