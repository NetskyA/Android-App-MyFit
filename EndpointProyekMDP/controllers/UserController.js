const { Sequelize, Op } = require("sequelize");

const bcrypt = require("bcrypt");
const multer = require("multer");
const fs = require("fs");

const upload = multer({ dest: "uploads/" });

const User = require("../models/Users");
const { testFunction } = require("../service/Functions");
const nodemailer = require('nodemailer');

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
    return res.status(200).send({ test: "wkwk" });
  },

  cekEmailandUsername: async (req, res) => {
    const { username, email } = req.body;

    let user = await User.findOne({
      where: {
        [Op.or]: [{ username: username }, { email: email }],
      },
    });

    if (user) {
      return res.status(200).send({ text: "Duplicate" });
    } else {
      return res.status(200).send({ text: "Ok" });
    }
  },

  register: async (req, res) => {
    const {
      username,
      email,
      password,
      phone,
      name,
      dob,
      gender,
      height,
      weight,
      age,
      blood_type,
      allergy,
      image,
    } = req.body;
    // console.log(image)

    const hashPass = await bcrypt.hash(password, 10);

    await User.create({
      username: username,
      password: hashPass,
      email: email,
      phone: phone,
      name: name,
      dob: dob,
      gender: gender,
      height: height,
      weight: weight,
      age: age,
      blood_type: blood_type,
      allergy: allergy,
      image: image,
    });

    // const imageBuffer = Buffer.from(image, 'base64');

    // fs.writeFile(`uploads/${username}.jpg`, imageBuffer, (err) => {
    //     if (err) {
    //         console.error(err);
    //         return res.status(500).send('Error saving image');
    //     }
    // });

    // fs.renameSync(`uploads/temp.png`, `uploads/${title}`);
    // return res.status(200).send("Register Success!");
    return res.status(201).send({ text: "Ok" });
  },

  login: async (req, res) => {
    const { username, email, password } = req.body;

    const cekUser = await User.findOne({
      where: {
        [Op.or]: [{ username: username }, { email: email }],
      },
    });

    if (!cekUser) return res.status(200).send({ password: "0" });

    const cekPass = await bcrypt.compare(password, cekUser.password);

    if (!cekPass) return res.status(200).send({ password: "1" });

    // const binaryData = fs.readFileSync(cekUser.image)
    // // Convert binary data to base64 encoded string
    // const img = Buffer(binaryData).toString('base64')

    // return res.status(200).json(img+"")
    return res.status(200).send({
      name: cekUser.dataValues.name,
      username: cekUser.dataValues.username,
      email: cekUser.dataValues.email,
      phone: cekUser.dataValues.phone,
      dob: cekUser.dataValues.dob,
      gender: cekUser.dataValues.gender,
      height: cekUser.dataValues.height,
      weight: cekUser.dataValues.weight,
      age: cekUser.dataValues.age,
      blood_type: cekUser.dataValues.blood_type,
      allergy: cekUser.dataValues.allergy,
      image: cekUser.dataValues.image,
    });
  },

  sendCode:async(req,res)=>{
    const {email} = req.query
    const cekUser = await User.findOne({
      where: {
        email: email
      },
    });
    const otp = Math.floor(100000 + Math.random() * 900000); // 6-digit OTP
    const expiry = Date.now() + 60000; // 1 minute in milliseconds

    await User.update({
      otp: otp,
      otp_expired: expiry
    }, {
      where: {
        email: email
      }
    })

    const transporter = nodemailer.createTransport({
      service: "Gmail",
      host: "smtp.gmail.com",
      port: 465,
      secure: true,
      auth: {
        user: "alvinbwiyono@gmail.com",
        pass: "jqzfirqblwjgdwtk",
      },
    });
    
    var mailOptions = {
      from: 'alvinbwiyono@gmail.com',
      to: email,
      subject: 'Sending Email using Node.js',
      html: `<h1>Welcome</h1><p>That was easy!</p><p>OTP: ${otp}</p>`
    }; 
    
    transporter.sendMail(mailOptions, function(error, info){
      if (error) {
        console.log(error);
        return res.status(400).send({ text: "Err" })
      } else {
        console.log('Email sent: ' + info.response);
        return res.status(200).send({ text: "Ok" })
      }
    });
  },

  verifyOtp:async(req,res)=>{
    const {email,otp} = req.query
    const cekUser = await User.findOne({
      where: {
        email: email
      },
    });
    if (cekUser.dataValues.otp && String(cekUser.dataValues.otp) === String(otp)) {
      if (parseInt(cekUser.dataValues.otp_expired) > Date.now()){ 
        await User.update({
          otp: null,
          otp_expired: null
        }, {
          where: {
            email: email
          }
        });
          res.status(200).send({text:'OTP is valid'});
      } else {
        await User.update({
          otp: null,
          otp_expired: null
        }, {
          where: {
            email: email
          }
        });
        res.status(200).send({text:'OTP has expired'});
      }
    } else {
      res.status(200).send({text:'Invalid OTP'});
    }
  },

  cekPhoneNumber: async (req, res) => {
    const { phone } = req.query;

    let cekUser = await User.findOne({
      where: {
        phone: phone,
      },
    });
    if (!cekUser) return res.status(200).send({ password: "0" }); 

    return res.status(200).send({
      name: cekUser.dataValues.name,
      username: cekUser.dataValues.username,
      email: cekUser.dataValues.email,
      phone: cekUser.dataValues.phone,
      dob: cekUser.dataValues.dob,
      gender: cekUser.dataValues.gender,
      height: cekUser.dataValues.height,
      weight: cekUser.dataValues.weight,
      age: cekUser.dataValues.age,
      blood_type: cekUser.dataValues.blood_type,
      allergy: cekUser.dataValues.allergy,
      image: cekUser.dataValues.image,
    });
  }
};
