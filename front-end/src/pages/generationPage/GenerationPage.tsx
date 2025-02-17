import { useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import IUrlShortener from "../../interfaces/IUrlShortener";
import api from "../../services/api";
import "./styles.css";

export default function GenerationPage(){
  const [urlToBeShortened, setUrlToBeShortened] = useState<IUrlShortener>({ url: ""});
  const [urlResponseData, setUrlResponseData] = useState<IUrlShortener>({ url: ""});

  const copyToClipboard = (text: string) => {
    const textArea = document.createElement("textarea");
    textArea.value = text;
    document.body.appendChild(textArea);
    textArea.select();
    document.execCommand("copy");
    document.body.removeChild(textArea);
  }

  const handleClick = async () => {
    if (!urlToBeShortened.url) {
      toast.error("You must provide an URL!", { position: "top-right", autoClose: 2000 });
    } else {
      try {
        const { data } = await api.post("shorten-url", urlToBeShortened)
        setUrlResponseData(data)
        toast.success(
          "Your shortened URL has been successfully generated and it will expire in 24 hours. Click on the link to copy it to the clipboard.",
          { position: "top-right", autoClose: 5000 }
        );
      } catch (error) {
        console.error(error);
        toast.error("Something went wrong!", { position: "top-right", autoClose: 2000 });
      }
    }
  };

  const handleCopy = () => {
    copyToClipboard(urlResponseData.url);
    toast.success("URL copied to clipboard!", { position: "top-right", autoClose: 2000 });
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUrlToBeShortened({ url: event.target.value });
  };

  return(
    <div className="container">
      <div className="content">
        <div className="header">
          <h1 className="main-title">Shorten URL</h1>
          <p className="description">
            This is a simple URL shortener application. Enter a URL below, and we will generate a shortened version for you.
          </p>
        </div>
        <div className="generation">
          <input
            type="text"
            placeholder="Type the URL to be shortened"
            className="input-field"
            onChange={handleInputChange}
          />
          <button className="button" onClick={handleClick}>
            Shorten URL
          </button>
        </div>
        {urlResponseData.url && (
          <div className="result">
            <h2 className="result-text">
              Generated url:
            </h2>
            <button onClick={handleCopy} className="copyButton">
             {urlResponseData.url}
            </button>
          </div>
        )}
        </div>
        <ToastContainer />
    </div>
  );
}