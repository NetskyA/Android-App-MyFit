const express = require("express");
const router = require("./router/router");
const app = express();
const nodemailer = require('nodemailer');

app.use(express.json({ limit: "50mb" }));
app.use(express.urlencoded({ limit: "50mb", extended: true }));

app.use("/api", router);

app.listen(3000, () =>
  console.log(`Server is running at http://localhost:3000`)
);
app.get("/test",(req,res)=>{
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
    to: 'alvinbwiyono2@gmail.com',
    subject: 'Sending Email using Node.js',
    html: '<h1>Welcome</h1><p>That was easy!</p>'
  }; 
  
  transporter.sendMail(mailOptions, function(error, info){
    if (error) {
      console.log(error);
      return res.send("err")
    } else {
      console.log('Email sent: ' + info.response);
      return res.send("sukses")
    }
  });
})