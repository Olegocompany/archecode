import { Trashcan, Lock, CheckMark, People, Message, CloudDownload } from '@/app/shared/svg';
import { MenuButton, Notification } from '@shared-ui';
import { memo } from 'react';

interface SwitchProjectsProps {
    projectData?: ProjectData;
    handleClick?: () => void;
}

interface ProjectData {
    name: string;
    description: string;
    update: string | number;
    branch: string;
    developers: string | number;
    commits: string | number;
    size: string | number;
}

const ProjectItem = memo(function ProjectItem({ projectData, handleClick }: SwitchProjectsProps) {
    return (
        <div
            className="flex group justify-between bg-input-outline rounded-[30px] font-montserrat drop-shadow-[0_4_10px_rgba(0_0_0/0.05)] w-full transition-all duration-300
                        hover:bg-light-gray hover:drop-shadow-[0_0_12px_rgb(255_255_255/0.1)]
                        active:opacity-40"
            onClick={handleClick}
        >
            <div className="flex items-center">
                <span className="block w-[5px] h-[42px] rounded-r-[5px] bg-gray transition-all duration-300 group-hover:bg-accent-light" />
                <div className="flex p-5">
                    <div className="flex flex-col text-white text-2xl gap-2.5">
                        <p>{projectData?.name}</p>
                        <p className="text-base opacity-50 max-w-200 line-clamp-2">
                            {projectData?.description}
                        </p>
                    </div>
                </div>
            </div>
            <div>
                <div className="flex h-full">
                    <div className="p-5">
                        <div className="flex text-accent-light items-center justify-end gap-2 drop-shadow-[0_0_6px_rgba(0,185,6,0.75)]">
                            <span>
                                <CheckMark />
                            </span>
                            <p>Обновлён {projectData?.update} минуты назад</p>
                        </div>
                        <div className="flex text-white gap-1 justify-end">
                            <p className=" opacity-30">Отслеживаемая ветка: </p>
                            <span className=" drop-shadow-[0_0_6px_var(--white)]">
                                {projectData?.branch}
                            </span>
                        </div>
                    </div>
                    <span className="w-[1px] h-full bg-white/5" />
                    <div className="flex gap-10 p-5 h-full">
                        <div className="flex flex-col text-white justify-between">
                            <div className="flex items-center gap-3">
                                <span>
                                    <People />
                                </span>
                                <p>{projectData?.developers}</p>
                            </div>
                            <div className="flex items-center gap-3">
                                <span>
                                    <Message />
                                </span>
                                <p>{projectData?.commits}</p>
                            </div>
                            <div className="flex items-center gap-3">
                                <span>
                                    <CloudDownload />
                                </span>
                                <p className="whitespace-nowrap">{projectData?.size}</p>
                            </div>
                        </div>
                        <div className="relative flex flex-col h-full justify-between">
                            <div className="absolute -top-8">
                                <Notification quantity={2} />
                            </div>
                            <MenuButton variant={'icon'} bgColor={'gray'}>
                                <Lock />
                            </MenuButton>
                            <MenuButton variant={'icon'} bgColor={'red'}>
                                <Trashcan />
                            </MenuButton>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
});

export default ProjectItem;
