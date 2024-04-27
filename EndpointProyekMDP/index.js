const express = require("express");
const router = require("./router/router");
const app = express();

app.use(express.json({ limit: "50mb" }));
app.use(express.urlencoded({ limit: "50mb", extended: true }));

app.use("/api", router);

app.listen(3000, () =>
  console.log(`Server is running at http://localhost:3000`)
);
