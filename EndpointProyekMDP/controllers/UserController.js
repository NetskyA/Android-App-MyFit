const { Sequelize, Op } = require("sequelize");

const bcrypt = require("bcrypt");
const multer = require("multer");
const fs = require("fs");

const profile = multer({ dest: "uploads/profile" });

const User = require("../models/Users");
const { testFunction, generateDummyUsers } = require("../service/Functions");
const nodemailer = require('nodemailer');
const Diets = require("../models/Diets");

module.exports = {
  uploadDummyUser: async (req, res) => {
    const users = generateDummyUsers();
    // return res.status(201).send(testFunction("Success!"))
    // return res.status(200).send("OK");
    return res.status(201).send(users);
  },

  test: async (req, res) => {
    const hashPass = await bcrypt.hash("123", 10);
    return res.status(200).send({ test: hashPass });
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

  

  loginGoogle: async (req, res) => {
    const {email} = req.query;

    const cekUser = await User.findOne({
      where: {
        email: email,
      },
    });

    if (!cekUser) return res.status(200).send({ password: "0" });

    let img = ""

    if(cekUser.dataValues.image!=""){
      const binaryData = fs.readFileSync(cekUser.dataValues.image)
      img = Buffer(binaryData).toString('base64')
    }

    return res.status(200).send({
      id: cekUser.dataValues.id,
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
      image: img,
    });
  },

  deleteAccount: async (req, res) => {
    const { id,pass } = req.query;
    console.log(pass)
    let user = await User.findByPk(parseInt(id));

    const cekPass = await bcrypt.compare(pass, user.password);

    if (!cekPass) return res.status(200).send({ text: "Incorrect Password" });

    await user.destroy({
      where: {
        id: id
      }
    })

    await Diets.destroy({
      where: {
        user_id: id
      }
    })

    return res.status(200).send({
      text: "Success"
    });
  },

  checkPass: async (req, res) => {
    let { id, password , newPassword } = req.query;
    let user = await User.findByPk(parseInt(id));

    const cekPass = await bcrypt.compare(password, user.password);

    if (!cekPass) return res.status(200).send({ password: "0" });

    const hashPass = await bcrypt.hash(newPassword, 10);
    user.password = hashPass
    await user.save()

    return res.status(200).send({
      id: user.id,
      name: user.name,
      username: user.username,
      email: user.email,
      phone: user.phone,
      dob: user.dob,
      gender: user.gender,
      height: user.height,
      weight: user.weight,
      age: user.age,
      blood_type: user.blood_type,
      allergy: user.allergy,
      image: user.image,
    });
  },

  register: async (req, res) => {
    let {
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
    if(phone ==''){
      phone=null
    }
    var cekUser = await User.create({
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

    var day = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]

    for(let i=0; i<day.length; i++){
      await Diets.create({ 
        user_id:cekUser.dataValues.id,
        day: day[i]
      })
    }

    return res.status(200).send({
      id: cekUser.dataValues.id,
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

    console.log(cekUser.dataValues.id)

    let img = ""

    if(cekUser.dataValues.image!=""){
      const binaryData = fs.readFileSync(cekUser.dataValues.image)
      img = Buffer(binaryData).toString('base64')
    }

    return res.status(200).send({
      id: cekUser.dataValues.id,
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
      image: img,
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
      html: `<body style="margin: 0; padding: 0; box-sizing: border-box; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f9f9f9; display: flex; justify-content: center; align-items: center; height: 100vh; font-size: 16px; color: #333;">
      <div class="container" style="background: white; width: 350px; border-radius: 8px; box-shadow: 0 8px 16px rgba(0,0,0,0.1); text-align: center; padding: 30px;">
          <div class="header" style="color: #0080ff; margin-bottom: 10px;">
              <h1>OTP</h1>
          </div>
          <div class="content" style="margin: 20px 0;">
              <p>Here is the verification code that can be used to login to My Fit</p>
              <div class="otp" style="font-size: 32px; font-weight: bold; margin: 20px 0; color: #333;">${otp}</div>
          </div>
          <div class="footer" style="font-size: 14px; color: #666; margin-top: 30px;">
              The code above is only valid for 1 minutes. Do not disclose the code to anyone, including the My Fit. 
          </div>
      </div>
  </body>`
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

    let img = ""

    if(cekUser.dataValues.image!=""){
      const binaryData = fs.readFileSync(cekUser.dataValues.image)
      img = Buffer(binaryData).toString('base64')
    }

    return res.status(200).send({
      id:cekUser.dataValues.id,
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
      image: img,
    });
  },

  updateProfile: async (req, res)=>{
    const {
      id,
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

    await User.update(
      {
        username: username,
        email: email,
        phone:phone,
        name:name,
        dob:dob,
        gender:gender,
        height:height,
        weight:weight,
        age:age,
        blood_type:blood_type,
        allergy:allergy,
      }, {
        where: {
          id: id
        }
      }
    )

    return res.status(200).send({text: "Success!"})
  },

  checkEmail: async (req, res)=>{
    const {
      id,
      email,
    } = req.query;

    const cekUser = await User.findOne({
      where: {
        id: {
          [Op.ne]: id
        },
        email: email
      }
    })

    if(cekUser){
      console.log("Fail")
      return res.status(200).send({text: "Fail"})
    }
    console.log("Ok")
    return res.status(200).send({text: "Ok"})
  },

  checkPhone: async (req, res)=>{
    const {
      id,
      phone,
    } = req.query;

    const cekUser = await User.findOne({
      where: {
        id: {
          [Op.ne]: id
        },
        phone: phone
      }
    })

    if(cekUser){
      console.log("Fail")
      return res.status(200).send({text: "Fail"})
    }
    console.log("Ok")
    return res.status(200).send({text: "Ok"})
  },

  checkUsername: async (req, res)=>{
    const {
      id,
      username,
    } = req.query;

    const cekUser = await User.findOne({
      where: {
        id: {
          [Op.ne]: id
        },
        username: username
      }
    })

    if(cekUser){
      console.log("Fail")
      return res.status(200).send({text: "Fail"})
    }
    console.log("Ok")
    return res.status(200).send({text: "Ok"})
  },

  uploadImageProfil: async (req, res)=>{
    const {id, image} = req.body
    // console.log(image)
    // console.log(id)
    const imageBuffer = Buffer.from(image, 'base64');

    fs.writeFile(`uploads/profile/${id}.jpg`, imageBuffer, (err) => {
        if (err) {
            console.error(err);
            return res.status(200).send({text: 'Fail'});
        }
    });
    await User.update(
      {
        image:`uploads/profile/${id}.jpg`,
      }, {
        where: {
          id: id
        }
      }
    )
    return res.status(200).send({text: "Ok"})
    // fs.renameSync(`uploads/temp.png`, `uploads/${title}`);
    // return res.status(200).send("Register Success!");
  },

  getImageProfil: async (req, res)=>{
    const {id} = req.query
    const user = await User.getByPk(id)
    const binaryData = fs.readFileSync(user.image)
    // Convert binary data to base64 encoded string
    const img = Buffer(binaryData).toString('base64')
    return res.status(200).send({text: img+""})
  },

  search: async (req, res)=>{
    const {
      id,
      keyword,
    } = req.query;
    
    let searchUser = await User.findAll({
      attributes: ['username', 'image'],
      where: {
        id: {
          [Op.ne]: id
        },
        username: {
          [Op.like]: '%' + keyword + '%' 
        },
        status: 1
        // name: {
        //   [Op.like]: '%' + keyword + '%' 
        // }
      }
    });

    for (let i = 0; i < searchUser.length; i++) {
      const user = searchUser[i];
      if(user.image!=""){
        const binaryData = fs.readFileSync(user.image)
        user.image = Buffer(binaryData).toString('base64')
      }
    }

    return res.status(200).send({searchUser: searchUser})
  },

  getUsername: async (req, res)=>{
    const {id} = req.query
    var user = await User.findOne({
      where:{
        id: id,
        status: 1
      }
    })
    if(user) return res.status(200).send(user.username)
    else return res.status(200).json("Deleted Account")
  }, 

  getUserByUsername: async (req, res)=>{
    const {username} = req.query
    var user = await User.findOne({
      where:{
        username: username,
        status: 1
      }
    })
    if(user.image!=""){
      const binaryData = fs.readFileSync(user.image)
      user.image = Buffer(binaryData).toString('base64')
    }
    return res.status(200).send(user)
  } ,
};  
  