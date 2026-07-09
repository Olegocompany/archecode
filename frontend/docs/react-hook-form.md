# React Hook Form + Zod

Управление формами с валидацией через Zod.

## Использование

### Базовая форма

```ts
// ИМЯ.types.ts
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";

const schema = z.object({
  name: z.string().min(2, "Минимум 2 символа"),
  email: z.string().email("Некорректный email"),
});

export const resolver = zodResolver(schema);
export type FormData = z.infer<typeof schema>;
```

```tsx
// ИМЯ.ts
"use client";
import { resolver, type FormData } from "./ИМЯ.types.ts";
import { useForm } from "react-hook-form";

export function MyForm() {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormData>({
    resolver: resolver,
  });

  const onSubmit = (data: FormData) => {
    console.log(data);
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <input {...register("name")} placeholder="Имя" />
      {errors.name && <p>{errors.name.message}</p>}

      <input {...register("email")} placeholder="Email" />
      {errors.email && <p>{errors.email.message}</p>}

      <button type="submit">Отправить</button>
    </form>
  );
}
```

### Валидация в реальном времени

По умолчанию валидация происходит при `onSubmit`. Режим можно изменить:

```tsx
const form = useForm<FormData>({
  resolver: zodResolver(schema),
  mode: "onChange", // при каждом изменении
  // mode: "onBlur",      // при потере фокуса
});
```

### Группы полей (массивы)

```tsx
// ИМЯ.types.ts
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";

const schema = z.object({
  items: z.array(z.object({ name: z.string() })),
});

export const resolver = zodResolver(schema);
export type FormData = z.infer<typeof schema>;
```

```tsx
// ИМЯ.ts
"use client";
import { resolver, type FormData } from "./ИМЯ.types.ts";
import { useForm, useFieldArray } from "react-hook-form";

export function DynamicForm() {
  const { control, register } = useForm<FormData>({ resolver: resolver });
  const { fields, append, remove } = useFieldArray({ control, name: "items" });

  return (
    <>
      {fields.map((field, index) => (
        <div key={field.id}>
          <input {...register(`items.${index}.name`)} />
          <button onClick={() => remove(index)}>Удалить</button>
        </div>
      ))}
      <button onClick={() => append({ name: "" })}>Добавить</button>
    </>
  );
}
```
