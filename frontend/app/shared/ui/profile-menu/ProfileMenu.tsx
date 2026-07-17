"use client";
import { AvatarIcon } from "@/app/shared/svg";
import { useState } from "react";

interface ProfileMenuProps {
  handleClick?: () => void;
}

function ProfileMenu({ handleClick }: ProfileMenuProps) {
  const [isHover, setIsHover] = useState(false);
  return (
    <div
      className="flex text-white rounded-[20px] flex-none px-5 py-4 bg-input-outline transition duration-300"
      onMouseEnter={() => setIsHover(true)}
      onMouseLeave={() => setIsHover(false)}
    >
      <AvatarIcon />
      <p
        className={`
            drop-shadow-[0_0_6px_var(--white)]
            transition-all duration-300
            ${isHover ? "opacity-100 max-w-40 ml-3" : "opacity-0 max-w-0 overflow-hidden ml-0"}
        `}
      >
        Профиль
      </p>
    </div>
  );
}

export default ProfileMenu;
