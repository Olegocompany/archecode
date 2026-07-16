import { PlusIcon } from "@/app/shared/svg";

interface CreateProjectProps {
  handleClick?: () => void;
}

function CreateProject({ handleClick }: CreateProjectProps) {
  return (
    <div
      className="flex items-center justify-center gap-4 px-5 py-6 bg-gray rounded-[20px]
        hover:bg-input-outline hover:drop-shadow-[0_0_60px_rgba(84,202,88,0.25)]
        active:opacity-50"
    >
      <PlusIcon />
      <p className="text-accent-light text-shadow-[0_0_6px_rgba(0,185,6,0.75)]">
        Создать проект
      </p>
    </div>
  );
}

export default CreateProject;
