# Архитектура frontend

## Стек

| Слой                 | Инструмент                          | Назначение                                   |
| -------------------- | ----------------------------------- | -------------------------------------------- |
| Фреймворк            | Next.js 16 (App Router)             | Роутинг, SSR, Server Components              |
| Язык                 | TypeScript 5 (strict)               | Типобезопасность                             |
| Стили                | Tailwind CSS v4 + tailwind-variants | Утилитарные стили, variant-driven компоненты |
| Серверное состояние  | TanStack Query 5                    | Кэш API, ревалидация, мутации                |
| Клиентское состояние | Zustand 5                           | UI-состояние, тема, фильтры                  |
| Формы                | React Hook Form 7 + Zod 4           | Формы с валидацией                           |
| Тесты                | Vitest 4 + Testing Library          | Юнит-тесты и тесты компонентов               |
| Линтер               | ESLint 9 (flat config) + Prettier   | Статический анализ, форматирование           |
| Пре-коммит           | Husky 9 + lint-staged               | Автоматические проверки перед коммитом       |

---

## Правила работы

### Компоненты

1. **Server Component по умолчанию.** `"use client"` ставится только когда нужны:
   - хуки (`useState`, `useEffect`, `useRouter`, и т.д.)
   - обработчики событий (`onClick`, `onSubmit`)
   - browser-only API
   - контекст / провайдеры

2. **Клиентский компонент должен быть максимально глубоко** — не оборачивать в `"use client"` весь page/layout, только интерактивную часть.

3. **Один компонент — один файл.** Исключение: мелкие вспомогательные компоненты в том же файле (до 2-3).

4. **Явный интерфейс пропсов.** Всегда `interface ComponentNameProps`, экспортируется.

5. **Default export** для страниц (`page.tsx`) и компонентов в `shared/`. Named export для хуков, утилит, store.

### Стилизация

1. Все стили через Tailwind CSS v4 (директивы `@import "tailwindcss"`, `@theme`).
2. Переиспользуемые варианты — через `tv()` из `tailwind-variants`.
3. Кастомные токены — в `globals.css` через `@theme inline {}`.
4. Инлайн-стили и CSS-модули не используем.

### Данные

1. **Серверные данные** — TanStack Query (`useQuery`, `useMutation`).
2. **UI-состояние** — Zustand (модалки, фильтры, тема, сайдбар).
3. **Формы** — React Hook Form + Zod (через `zodResolver`).
4. **Не дублировать состояние.** Данные из API не класть в Zustand — TanStack Query уже кэширует.

### Тесты

1. Файлы тестов класть рядом с тестируемым модулем: `ComponentName.test.tsx`.
2. Для компонентов с провайдерами — использовать функцию-обёртку `wrapper`.
3. Тестировать поведение, не имплементацию.

### Импорты

1. Алиас `@/` — всегда, без относительных путей вверх:
   ```tsx
   // ✅ глобальный слой
   import { Password } from "@/app/shared/input";
   import { useAuthStore } from "@/app/store/use-auth-store";
   import { cn } from "@/app/lib/cn";
   import { User } from "@/app/types/user";

   // ✅ Feature-папка (импорт внутри своей фичи)
   import { LoginForm } from "@/app/(auth)/_components/login-form";
   import { authApi } from "@/app/(auth)/_lib/auth-api";

   // ❌ относительные пути вверх
   import { Password } from "../../shared/input";
   ```

---

## Структура папок (Hybrid - FSD + LB)

```
frontend/
├── app/
│   ├── shared/                       # 🔷 Общий слой
│   │   ├── input.tsx                 #    UI-компоненты (ничего не знают о бизнесе)
│   │   ├── password.variants.ts
│   │   ├── button.tsx
│   │   └── modal.tsx
│   │
│   ├── store/                        # 🔷 Глобальные Zustand-сторы
│   │   ├── use-auth-store.ts         #    Один файл = один стор
│   │   └── use-theme-store.ts
│   │
│   ├── lib/                          # 🔷 Глобальные утилиты
│   │   ├── api-client.ts             #    Базовый API-клиент (fetch wrapper)
│   │   ├── cn.ts                     #    tailwind-merge + clsx
│   │   └── format-date.ts
│   │
│   ├── hooks/                        # 🔷 Глобальные React-хуки
│   │   ├── use-debounce.ts
│   │   └── use-media-query.ts
│   │
│   ├── types/                        # 🔷 Глобальные TypeScript-типы
│   │   ├── api.ts                    #    Ответы API, общие сущности
│   │   └── user.ts
│   │
│   ├── providers.tsx                 # Провайдеры (QueryClient и т.д.)
│   ├── globals.css                   # Глобальные стили + @theme
│   ├── layout.tsx                    # Корневой layout
│   └── page.tsx                      # Главная страница
│
│   ─────────────────────────────────────────────────────
│
│   # 🔷 Feature-папки (Route groups)
│   # Каждая группа маршрутов может содержать:
│   #   _components/  — компоненты только для этой фичи
│   #   _lib/         — утилиты только для этой фичи
│   #   _hooks/       — хуки только для этой фичи
│   #   _types/       — типы только для этой фичи
│
│   │
│   ├── (auth)/                       # 🔷 Feature: аутентификация
│   │   ├── _components/
│   │   │   ├── login-form.tsx
│   │   │   └── oauth-buttons.tsx
│   │   ├── _hooks/
│   │   │   └── use-auth.ts
│   │   ├── _lib/
│   │   │   └── auth-api.ts
│   │   ├── login/page.tsx
│   │   └── register/page.tsx
│   │
│   └── (dashboard)/                  # 🔷 Feature: дашборд
│       ├── _components/
│       │   ├── stats-card.tsx
│       │   └── chart.tsx
│       ├── _lib/
│       │   └── dashboard-api.ts
│       ├── profile/page.tsx
│       └── settings/page.tsx
│
├── public/                           # Статические файлы
├── docs/                             # Документация по инструментам
├── vitest.config.ts
├── eslint.config.mjs
├── next.config.ts
├── tsconfig.json
└── package.json
```

### Принципы организации

**Общий слой** (`shared/`, `store/`, `lib/`, `hooks/`, `types/`):

- Код, переиспользуемый между разными фичами.
- Имеет чёткие границы: UI не знает про store, store не знает про lib и т.д.
- `shared/` — только dumb-компоненты без бизнес-логики.
- `store/` — Zustand. Один файл = один стор. Только клиентское состояние.

**Feature-папки** (каждая группа маршрутов):

- Содержат всё, что специфично для этой фичи.
- Префикс `_` в именах папок — Next.js не воспринимает их как маршруты.
- Если код перестаёт быть специфичным — выносится в общий слой.

**Правило миграции:** код живёт максимально близко к месту использования. Как только появляется второй потребитель — выносится в общий слой.

---

## Data flow

```
[Браузер]
    │
    ├── Страница (Server Component)
    │     └── Дочерний клиентский компонент
    │           ├── TanStack Query ────── API (fetch / Server Actions)
    │           ├── Zustand ───────────── Локальное UI-состояние
    │           └── React Hook Form ──── Zod ─────── Валидация
    │
    └── pre-commit (Husky)
          ├── ESLint --fix
          └── Prettier --write
```

### Когда что использовать

| Ситуация                             | Инструмент                                  |
| ------------------------------------ | ------------------------------------------- |
| Получить список пользователей с бэка | TanStack Query                              |
| Создать/обновить запись              | TanStack Query mutation                     |
| Переключить тему (светлая/тёмная)    | Zustand (+ persist)                         |
| Открыть/закрыть модалку              | Zustand                                     |
| Отфильтровать список                 | Zustand (фильтры) + TanStack Query (запрос) |
| Форма регистрации                    | React Hook Form + Zod                       |
| Валидация email/пароля               | Zod schema                                  |
| Ручная отправка формы                | React Hook Form handleSubmit                |
| Статический контент                  | Server Component, никаких хуков             |

---

## Соглашения по именованию

| Сущность          | Стиль                       | Пример                         |
| ----------------- | --------------------------- | ------------------------------ |
| Компоненты        | PascalCase                  | `Password`, `UsersTable`       |
| Файлы компонентов | kebab-case                  | `input.tsx`, `users-table.tsx` |
| Файлы вариантов   | kebab-case + `.variants`    | `password.variants.ts`         |
| Хуки              | camelCase + `use`           | `useDebounce`, `useAuthStore`  |
| Файлы хуков       | kebab-case + `use-`         | `use-debounce.ts`              |
| Сторы             | camelCase + `use` + `Store` | `useThemeStore`                |
| Файлы сторов      | kebab-case + `use-`         | `use-theme-store.ts`           |
| Типы              | PascalCase                  | `UserResponse`, `ApiError`     |
| Файлы типов       | kebab-case                  | `user.ts`, `api.ts`            |
| Утилиты           | camelCase                   | `formatDate`, `cn`             |
| Файлы утилит      | kebab-case                  | `format-date.ts`, `cn.ts`      |
| Папки маршрутов   | kebab-case                  | `profile/settings`             |
| Тесты             | `<name>.test.ts(x)`         | `input.test.tsx`               |
