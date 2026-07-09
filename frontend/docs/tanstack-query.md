# TanStack Query (React Query)

Управление серверным состоянием — кэширование, ревалидация, загрузка данных с API.

## Использование

### Простой запрос

```tsx
import { useQuery } from "@tanstack/react-query";

export function UsersList() {
  const { data, isLoading, error } = useQuery({
    queryKey: ["users"],
    queryFn: () => fetch("/api/users").then((r) => r.json()),
  });

  if (isLoading) return <div>Загрузка...</div>;
  if (error) return <div>Ошибка: {error.message}</div>;

  return data.map((user) => <div key={user.id}>{user.name}</div>);
}
```

### Мутация (создание/обновление)

```tsx
import { useMutation, useQueryClient } from "@tanstack/react-query";

export function AddUser() {
  const queryClient = useQueryClient();

  const mutation = useMutation({
    mutationFn: (newUser) =>
      fetch("/api/users", {
        method: "POST",
        body: JSON.stringify(newUser),
      }),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["users"] }),
  });

  return (
    <button onClick={() => mutation.mutate({ name: "Иван" })}>Добавить</button>
  );
}
```

### С параметрами

```tsx
const { data } = useQuery({
  queryKey: ["users", userId],
  queryFn: () => fetch(`/api/users/${userId}`).then((r) => r.json()),
});
```

## Связка с Zustand

- **TanStack Query** — данные с бэка (кэш, ревалидация)
- **Zustand** — клиентское состояние (UI, фильтры, тема)

Пример: Zustand хранит выбранный фильтр, TanStack Query загружает данные:

```tsx
const filters = useFiltersStore((s) => s.filters);
const { data } = useQuery({
  queryKey: ["items", filters],
  queryFn: () => fetch(`/api/items?${new URLSearchParams(filters)}`),
});
```
