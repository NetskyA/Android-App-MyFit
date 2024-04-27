const { Sequelize } = require("sequelize");

const multer = require('multer');
const fs = require('fs');

const upload = multer({ dest: 'uploads/' });

const User = require("../models/Users");
const {
  testFunction,
} = require("../service/Functions");

module.exports = {
  // register: async (req, res) => {
  //   const { username, email, phone, name, password} = req.body;
  //   if(username=="" || username=="" || username=="" || username=="" || username==""){
  //     return res.status(401).send("Field cannot be empty!")
  //   }

  //   await User.create({
  //     username: username,
  //     email: email,
  //     phone: phone,
  //     name: name,
  //     password: password,
  //   })

  //   return res.status(201).send(testFunction("Success!"))
  // },

  register: async (req, res) => {
    const {username, password, image} = req.body
    // console.log(image)

    await User.create({
      username: username,
      password: password,
      image: `uploads/${username}.jpg`
    })

    const imageBuffer = Buffer.from(image, 'base64');

    fs.writeFile(`uploads/${username}.jpg`, imageBuffer, (err) => {
        if (err) {
            console.error(err);
            return res.status(500).send('Error saving image');
        }
    });

    // fs.renameSync(`uploads/temp.png`, `uploads/${title}`);
    // return res.status(200).send("Register Success!");
    return res.status(200).json(image+"");
  },
  login: async (req, res) => {
    const {username, password} = req.body

    const cekUser = await User.findOne({
      where: {
        username: username,
        password: password
      }
    })
    if(!cekUser) return res.status(400).json("-1")

    const binaryData = fs.readFileSync(cekUser.image)
    // Convert binary data to base64 encoded string
    const img = Buffer(binaryData).toString('base64')

    return res.status(200).json(img+"")
  }
};
