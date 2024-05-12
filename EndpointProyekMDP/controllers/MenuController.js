const { Sequelize, Op } = require("sequelize");

const bcrypt = require("bcrypt");
const multer = require("multer");
const fs = require("fs");

const profile = multer({ dest: "uploads/menu" });

const Menu = require("../models/Menus");
const { generateDummyUsers } = require("../service/Functions");

module.exports = {
    setDummy: async(req, res)=>{
        const tempMenu = await Menu.findAll()

    },
    upload: async(req, res)=>{
        const {
            user_id,
            name,
            ingredients,
            nutrition,
            how_to_make,
            note,
            like,
            date,
            image
        } = req.body
    
        const allMenus  = await Menu.findAll({
            attributes: ['id'],
        })
        const id = allMenus[allMenus.length-1].id+1
        // console.log(id)

        const imageBuffer = Buffer.from(image, 'base64')

        fs.writeFile(`uploads/menu/${id}.jpg`, imageBuffer, (err) => {
            if (err) {
                console.error(err);
                return res.status(200).send({text: 'Fail'});
            }
        })

        const today = new Date();
        const dd = String(today.getDate()).padStart(2, '0');
        const mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
        const yyyy = today.getFullYear();
        const formattedDate = `${dd}/${mm}/${yyyy}`;

        Menu.create({
            id:id,
            user_id:user_id,
            name:name,
            ingredients:ingredients,
            nutrition:nutrition,
            how_to_make:how_to_make,
            note:note,
            like:like,
            date:formattedDate,
            image:`uploads/menu/${id}.jpg`
        })

        return res.status(200).send({text: "Success"})
    },

    getAllMenuUser: async(req, res)=>{
        const {user_id} = req.query
        const allMenuUser = await Menu.findAll({
            where:{
                user_id: user_id,
                status:1
            }
        })
        for (let i = 0; i < allMenuUser.length; i++) {
            const menu = allMenuUser[i];
            if(menu.image!=""){
              const binaryData = fs.readFileSync(menu.image)
              menu.image = Buffer(binaryData).toString('base64')
            }
          }
        return res.status(200).json({allMenuUser: allMenuUser})
    }
    
}