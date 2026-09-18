'use client';
import { SubmitEvent, memo, useState, useRef } from 'react';
import { Cancel, CheckMark, Edit, Save } from '@/app/shared/svg';
import { MenuButton } from '@shared-ui';

interface EditFieldProps {
    name: string;
    onNameChange: (newName: string) => Promise<void>;
}

interface RequestStatus {
    status: 'wait' | 'reject' | 'resolve';
}

const EditField = memo(function EditField({ name, onNameChange }: EditFieldProps) {
    const [isEdit, setIsEdit] = useState(false);
    const [isLoad, setIsLoad] = useState(false);
    const [status, setStatus] = useState<RequestStatus['status']>('wait');
    const [newName, setNewName] = useState(name);
    const timer = useRef<ReturnType<typeof setTimeout> | null>(null);

    const handleEdit = () => {
        setIsEdit(!isEdit);
    };

    async function handleSubmit(e: SubmitEvent) {
        e.preventDefault();
        e.stopPropagation();

        if (timer.current) {
            clearTimeout(timer.current);
        }

        try {
            await onNameChange(newName);
            setNewName(newName);
            setStatus('resolve');
        } catch {
            setNewName(name);
            setStatus('reject');
        }

        timer.current = setTimeout(() => {
            setStatus('wait');
            handleEdit();
            setIsLoad(false);
        }, 500);
    }

    return (
        <div className={'transition-all duration-300 font-montserrat text-2xl text-white'}>
            {isEdit ? (
                <form onSubmit={handleSubmit} className={'flex gap-2.5 items-center'}>
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
                            <Save onClick={handleSubmit} />
                        </MenuButton>
                    )}
                </form>
            ) : (
                <div className={'flex gap-2.5 items-center'}>
                    <p>{name}</p>
                    <Edit
                        onClick={handleEdit}
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
