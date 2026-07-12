interface EyeProps {
  state: "open" | "close";
}

function AppEye({ state }: EyeProps) {
  return (
    <>
      {state === "open" ? (
        <svg
          className="w-6 h-6 stroke-primary-dark cursor-pointer stroke-2 hover:stroke-primary-light active:stroke-primary-light transition duration-300"
          viewBox="0 0 22 16"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            d="M1.06251 7.65138C0.979165 7.8759 0.979165 8.12287 1.06251 8.34738C1.87421 10.3155 3.25202 11.9983 5.02128 13.1825C6.79053 14.3666 8.87155 14.9988 11.0005 14.9988C13.1295 14.9988 15.2105 14.3666 16.9797 13.1825C18.749 11.9983 20.1268 10.3155 20.9385 8.34738C21.0218 8.12287 21.0218 7.8759 20.9385 7.65138C20.1268 5.68324 18.749 4.00042 16.9797 2.81628C15.2105 1.63214 13.1295 1 11.0005 1C8.87155 1 6.79053 1.63214 5.02128 2.81628C3.25202 4.00042 1.87421 5.68324 1.06251 7.65138Z"
            strokeLinecap="round"
            strokeLinejoin="round"
          />
          <path
            d="M11.0005 10.999C12.6573 10.999 14.0005 9.65588 14.0005 7.99902C14.0005 6.34217 12.6573 4.99902 11.0005 4.99902C9.34363 4.99902 8.00049 6.34217 8.00049 7.99902C8.00049 9.65588 9.34363 10.999 11.0005 10.999Z"
            strokeLinecap="round"
            strokeLinejoin="round"
          />
        </svg>
      ) : (
        <svg
          className="w-6 h-6 stroke-primary-dark cursor-pointer stroke-2 hover:stroke-primary-light active:stroke-primary-light transition duration-300"
          viewBox="0 0 22 12"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            d="M14.0003 11.0005L13.2783 7.75049"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
          />
          <path
            d="M1.00024 1.00049C1.7486 3.05127 3.10937 4.82238 4.89815 6.07378C6.68692 7.32519 8.81719 7.99638 11.0002 7.99638C13.1833 7.99638 15.3136 7.32519 17.1023 6.07378C18.8911 4.82238 20.2519 3.05127 21.0002 1.00049"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
          />
          <path
            d="M19.0002 8.00068L17.2742 5.95068"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
          />
          <path
            d="M3.00024 8.00068L4.72624 5.95068"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
          />
          <path
            d="M8.00024 11.0005L8.72224 7.75049"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
          />
        </svg>
      )}
    </>
  );
}

export default AppEye;
