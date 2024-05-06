const { Sequelize } = require("sequelize");

const bcrypt = require('bcrypt');
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
  test: (req, res) => {
    return res.status(200).send("Test")
  },
  register: async (req, res) => {
    const {username, password, image} = req.body
    // console.log(image)

    const hashPass = await bcrypt.hash(password, 10)

    await User.create({
      username: username,
      password: hashPass,
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
      }
    })
    if(!cekUser) return res.status(400).json("-1")

    const cekPass = await bcrypt.compare(password, cekUser.password);

    if(!cekPass) return res.status(400).send("-1")

    const binaryData = fs.readFileSync(cekUser.image)
    // Convert binary data to base64 encoded string
    const img = Buffer(binaryData).toString('base64')

    return res.status(200).json(img+"")
  }
};
