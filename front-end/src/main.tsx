import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./global-style.css";
import GenerationPage from "./pages/generationPage/GenerationPage";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <GenerationPage />
  </StrictMode>
);
