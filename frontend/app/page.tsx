"use client";

import { Button } from "@shared-ui";

export default function Home() {
  return (
    <div className="flex flex-col flex-1 items-center justify-center bg-dark-gray font-sans dark:pink-50">
      <main className="flex flex-1 w-full max-w-3xl flex-col items-center justify-between py-32 px-16 bg-secondary dark:pink-50 sm:items-start">
        <Button
          disabled={false}
          variantButton={"danger"}
          handleClick={() => console.log("blob")}
        >
          Создать
        </Button>
      </main>
    </div>
  );
}
