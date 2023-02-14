import React, { useEffect } from "react";

const ReactButton = () => {
  useEffect(() => {
    console.log("this is react button");
  }, []);

  return (
    <button onClick={() => console.log("button clicked!")}>react button</button>
  );
};

export default ReactButton;
