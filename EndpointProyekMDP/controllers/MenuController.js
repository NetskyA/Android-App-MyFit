const { Sequelize, Op } = require("sequelize");

const bcrypt = require("bcrypt");
const multer = require("multer");
const fs = require("fs");

const profile = multer({ dest: "uploads/menu" });

const Menu = require("../models/Menus");
const { generateDummyUsers } = require("../service/Functions");

module.exports = {
    setDummy: async(req, res)=>{
        // const tempMenu = await Menu.findAll()
        await Menu.update(
            { user_id: 1 }, 
            { 
                where: { 
                    id: { [Op.between]: [1, 30] } 
                } 
            }
        )
        await Menu.update(
            { user_id: 2 }, 
            { 
                where: { 
                    id: { [Op.between]: [31, 50] } 
                } 
            }
        )
        await Menu.update(
            { user_id: 0 }, 
            { 
                where: { 
                    id: { [Op.between]: [51, 205] } 
                } 
            }
        )
        return res.status(200).send("OK")
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
    },

    getRandomMenu: async(req, res)=>{
        const {user_id} = req.query
        let getRandomMenu = []
        const allMenu = await Menu.findAll()
        let randomSize = 20
        if(allMenu.length<20){
            randomSize = allMenu.length
        }
        for(let i = 0; i < randomSize; i++){
            let cek = false
            do {
                let randomNumber = Math.floor(Math.random() * (allMenu.length+1))
                cek = false
                const getMenu = await Menu.findOne({
                    where: {
                      id:randomNumber,
                      user_id: {
                        [Op.ne]: user_id
                      },
                    }
                })
                if(getMenu){
                    for (let j = 0; j < getRandomMenu.length; j++) {
                        if(getRandomMenu[j].id==getMenu.id){
                            cek = true
                        }                    
                    }    
                }else cek = true
                
                if(cek==false) getRandomMenu.push(getMenu)        
            } while (cek);
        }
        
        return res.status(200).json({getRandomMenu: getRandomMenu})
    }
}