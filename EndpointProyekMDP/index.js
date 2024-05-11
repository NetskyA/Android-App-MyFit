const express = require("express");
const router = require("./router/router");
const app = express();
const Recipe = require("./models/Menus");
const axios = require('axios');
const fs = require('fs');

app.use(express.json({ limit: "50mb" }));
app.use(express.urlencoded({ limit: "50mb", extended: true }));

app.use("/api", router);
app.use('/uploads/profile', express.static('uploads/profile'));
app.use('/uploads/menu', express.static('uploads/menu'));

app.listen(3000, () =>
  console.log(`Server is running at http://localhost:3000`)
);


async function downloadImage(url, id) {
  try {
      // Axios GET request to fetch the image as a stream
      const response = await axios({
          method: 'GET',
          url: url,
          responseType: 'stream'
      });

      // Create a write stream to save the image
      const writer = fs.createWriteStream('./uploads/menu/' + id + '.jpg');

      // Pipe the image data from the request to the file
      response.data.pipe(writer);

      // Return a promise that resolves when the image has been fully downloaded
      return new Promise((resolve, reject) => {
          writer.on('finish', resolve);
          writer.on('error', reject);
      });
  } catch (error) {
      console.error('Error downloading the image:', error);
  }
}

app.get("/", async (req, res) => {
  let recipe = await Recipe.findAll({
    attributes:['id','name','image'],
  });
  for (let i = 0; i < recipe.length; i++) {
    let data = recipe[i].dataValues;
    await downloadImage(data.image, data.id);
  }
  
});