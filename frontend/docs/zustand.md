# Zustand

Управление клиентским состоянием.

## Использование

### Простое хранилище

Создать `app/store/use-counter.ts`:

```tsx
import { create } from "zustand";

interface CounterState {
  count: number;
  increment: () => void;
  decrement: () => void;
  reset: () => void;
}

export const useCounterStore = create<CounterState>((set) => ({
  count: 0,
  increment: () => set((state) => ({ count: state.count + 1 })),
  decrement: () => set((state) => ({ count: state.count - 1 })),
  reset: () => set({ count: 0 }),
}));
```

Использование в компоненте:

```tsx
import { useCounterStore } from "@/app/store/use-counter";

export function Counter() {
  const count = useCounterStore((state) => state.count);
  const increment = useCounterStore((state) => state.increment);

  return (
    <div>
      <p>Счёт: {count}</p>
      <button onClick={increment}>+1</button>
    </div>
  );
}
```

### Асинхронные действия

```tsx
import { create } from "zustand";

interface UserState {
  user: { id: number; name: string } | null;
  loading: boolean;
  fetchUser: (id: number) => Promise<void>;
}

export const useUserStore = create<UserState>((set) => ({
  user: null,
  loading: false,
  fetchUser: async (id) => {
    set({ loading: true });
    const res = await fetch(`/api/users/${id}`);
    set({ user: await res.json(), loading: false });
  },
}));
```

### Составной селектор (множественные поля)

```tsx
const { user, loading } = useUserStore((state) => ({
  user: state.user,
  loading: state.loading,
}));
```

Чтобы избежать лишних ререндеров — использовать хуки по одному полю или `useShallow`:

```tsx
import { useShallow } from "zustand/react/shallow";

const { user, loading } = useUserStore(
  useShallow((state) => ({ user: state.user, loading: state.loading })),
);
```

### Persist (сохранение в localStorage/sessionStorage)

```tsx
import { create } from "zustand";
import { persist } from "zustand/middleware";

interface ThemeState {
  theme: "light" | "dark";
  toggle: () => void;
}

export const useThemeStore = create<ThemeState>()(
  persist(
    (set) => ({
      theme: "light",
      toggle: () =>
        set((state) => ({
          theme: state.theme === "light" ? "dark" : "light",
        })),
    }),
    { name: "theme-storage" },
  ),
);
```

### Хранилище без React (чистый TS)

```tsx
import { createStore } from "zustand/vanilla";

interface BearState {
  bears: number;
  addBear: () => void;
}

export const bearStore = createStore<BearState>((set) => ({
  bears: 0,
  addBear: () => set((state) => ({ bears: state.bears + 1 })),
}));

// Подписка вне React:
const unsubscribe = bearStore.subscribe((state) => {
  console.log("Изменилось:", state.bears);
});
```

### Связка с TanStack Query (см. docs/tanstack-query.md)

- **Zustand** — клиентское состояние (UI, фильтры, тема, модалки)
- **TanStack Query** — серверное состояние (данные с API, кэш, ревалидация)

Пример: Zustand хранит фильтр, TanStack Query загружает данные:

```tsx
const filters = useFiltersStore((s) => s.filters);
const { data } = useQuery({
  queryKey: ["items", filters],
  queryFn: () => fetch(`/api/items?${new URLSearchParams(filters)}`),
});
```

### Devtools (Redux DevTools)

```tsx
import { devtools } from "zustand/middleware";

export const useStore = create<State>()(
  devtools((set) => ({/* ... */}), { name: "MyStore" }),
);
```
