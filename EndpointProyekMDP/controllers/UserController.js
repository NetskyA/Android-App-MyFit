const { Sequelize } = require("sequelize");

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
      image: image+""
    })

    // const imageBuffer = Buffer.from(img, 'base64');
    // fs.writeFile(`uploads/${title}.jpg`, imageBuffer, (err) => {
    //     if (err) {
    //         console.error(err);
    //         return res.status(500).send('Error saving image');
    //     }
    // });
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

    return res.status(200).json(cekUser.image+"")
  }
};
