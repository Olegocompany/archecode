# Vitest

Тестирование компонентов React и утилит.

## Запуск

```bash
npm test         # watch mode
npm run test:run # однократно
```

## Конфигурация

```ts
// vitest.config.ts
import { defineConfig } from "vitest/config";
import react from "@vitejs/plugin-react";
import path from "path";

export default defineConfig({
  plugins: [react()],
  test: {
    environment: "jsdom",
    globals: true,
    setupFiles: [],
  },
  resolve: {
    alias: { "@": path.resolve(__dirname, ".") },
  },
});
```

## Использование

### Тест компонента

Создать `__tests__/ComponentName.test.tsx` рядом с компонентом:

```tsx
import { render, screen } from "@testing-library/react";
import { describe, it, expect } from "vitest";
import Button from "@/app/shared/Button";

describe("Button", () => {
  it("рендерит текст", () => {
    render(<Button>Нажми</Button>);
    expect(screen.getByText("Нажми")).toBeDefined();
  });

  it("вызывает onClick", () => {
    let clicked = false;
    render(<Button onClick={() => (clicked = true)}>Нажми</Button>);
    screen.getByText("Нажми").click();
    expect(clicked).toBe(true);
  });
});
```

### Тест утилиты

```tsx
import { describe, it, expect } from "vitest";
import { formatDate } from "@/lib/date";

describe("formatDate", () => {
  it("форматирует дату", () => {
    expect(formatDate(new Date("2025-01-01"))).toBe("01.01.2025");
  });
});
```

### Тест с провайдерами

Если компонент использует QueryClient или Zustand:

```tsx
import { render, screen } from "@testing-library/react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

function wrapper({ children }: { children: React.ReactNode }) {
  const qc = new QueryClient({ defaultOptions: { queries: { retry: false } } });
  return <QueryClientProvider client={qc}>{children}</QueryClientProvider>;
}

it("работает с QueryClient", () => {
  render(<UsersList />, { wrapper });
  expect(screen.getByText("Загрузка...")).toBeDefined();
});
```

### Мок fetch

```tsx
import { vi } from "vitest";

global.fetch = vi.fn(() =>
  Promise.resolve({
    json: () => Promise.resolve([{ id: 1, name: "Иван" }]),
  }),
) as Mock;
```

## Структура папок

```
app/
  __tests__/         # тесты для app-компонентов
  shared/
    __tests__/       # тесты для shared-компонентов
  lib/
    __tests__/       # тесты для утилит
```
