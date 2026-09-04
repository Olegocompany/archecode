'use client';
import { memo, useState } from 'react';
import { Cancel, CheckMark, Edit, Save } from '@/app/shared/svg';
import { MenuButton } from '@shared-ui';

interface EditFieldProps {
    name: string;
    handleClick?: () => void;
}

const EditField = memo(function EditField({ name, handleClick }: EditFieldProps) {
    const [isEdit, setIsEdit] = useState(false);
    const [isLoad, setIsLoad] = useState(false);
    const [status, setStatus] = useState('wait');
    const [newName, setNewName] = useState(name);

    const handleIsEdit = () => {
        setIsEdit(!isEdit);
    };

    async function editName() {
        setIsLoad(false);
        setStatus('resolve');
        if (status === 'resolve') {
            setNewName(newName);
        } else if (status === 'reject') {
            setNewName(name);
        }
        console.log(newName);
        setTimeout(() => {
            setStatus('wait');
            handleIsEdit();
            setIsLoad(false);
        }, 500);
    }

    return (
        <div className={'transition-all duration-300 font-montserrat text-2xl text-white'}>
            {isEdit ? (
                <form className={'flex gap-2.5 items-center'}>
                    {isLoad ? (
                        <p className={'text-orange-600'}>Загрузка</p>
                    ) : (
                        <label>
                            <input
                                placeholder={name}
                                type="text"
                                className={`border-b bg-transparent ${status === 'wait' ? 'border-accent-base' : 'border-white '} `}
                                disabled={status !== 'wait'}
                                value={newName}
                                onChange={(e) => setNewName(e.target.value)}
                            />
                        </label>
                    )}
                    {status === 'resolve' ? (
                        <MenuButton variant={'icon'} bgColor={'green'}>
                            <CheckMark />
                        </MenuButton>
                    ) : status === 'reject' ? (
                        <MenuButton variant={'icon'} bgColor={'red'}>
                            <Cancel />
                        </MenuButton>
                    ) : (
                        <MenuButton variant={'icon'} bgColor={'green'}>
                            <Save onClick={editName} />
                        </MenuButton>
                    )}
                </form>
            ) : (
                <div className={'flex gap-2.5 items-center'}>
                    <p>{newName}</p>
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
