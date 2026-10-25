'use client';
import { memo, useState } from 'react';
import { Edit, Save } from '@/app/shared/svg';
import { MenuButton } from '@shared-ui';

interface EditFieldProps {
    name: string;
    state: string;
    handleClick?: () => void;
}

const EditField = memo(function EditField({ name, handleClick }: EditFieldProps) {
    const [isEdit, setIsEdit] = useState(false);
    const handleIsEdit = () => {
        setIsEdit(!isEdit);
    };

    return (
        <div className={'transition-all duration-300 font-montserrat text-2xl text-white'}>
            {isEdit ? (
                <div className={'flex gap-2.5 items-center'}>
                    <label>
                        <input placeholder={name} type="text" className={'w-fit bg-transparent'} />
                    </label>
                    <MenuButton variant={'icon'} bgColor={'green'}>
                        <Save onClick={handleIsEdit} />
                    </MenuButton>
                </div>
            ) : (
                <div className={'flex gap-2.5 items-center'}>
                    <p>{name}</p>
                    <Edit
                        onClick={handleIsEdit}
                        className={
                            'text-white cursor-pointer transition-all duration-300 ' +
                            'hover:drop-shadow-[0_0_12px_var(--white)] '
                        }
                    />
                </div>
            )}
        </div>
    );
});

export default EditField;
