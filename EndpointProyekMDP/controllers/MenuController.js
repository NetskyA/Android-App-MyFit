const { Sequelize, Op } = require("sequelize");

const bcrypt = require("bcrypt");
const multer = require("multer");
const fs = require("fs");

const profile = multer({ dest: "uploads/menu" });

const Menu = require("../models/Menus");
const { generateDummyUsers } = require("../service/Functions");
const Diets = require("../models/Diets");
const Users = require("../models/Users");
const { text } = require("express");

module.exports = {
    setDummy: async(req, res)=>{
        const tempMenu = await Menu.findAll()
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
            { user_id: 3 }, 
            { 
                where: { 
                    id: { [Op.between]: [51, tempMenu.length] } 
                } 
            }
        )
        for (let i = 0; i < tempMenu.length; i++) {
            const m = tempMenu[i];
            let randomNumber = Math.floor(Math.random() * 200)
            await Menu.update(
                {like: randomNumber},
                {
                    where:{
                        id: m.id
                    }
                }
            )
            
        }
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
        if(allMenu.length<randomSize){
            randomSize = allMenu.length
        }
        // console.log("test")
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
                      status:1
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
        for (let i = 0; i < getRandomMenu.length; i++) {
            const menu = getRandomMenu[i];
            if(menu.image!=""){
              const binaryData = fs.readFileSync(menu.image)
              menu.image = Buffer(binaryData).toString('base64')
            }
          }
        return res.status(200).json({allRandomMenu: getRandomMenu})
    },

    searchAllMenu: async(req,res)=>{
        const {
            q,
          } = req.query;
          
          let searchMenu = await Menu.findAll({
            where: { 
              name: {
                [Op.like]: '%' + q + '%' 
              },
              status: 1
            }
          });

        var hasil = []

        for(let i = 0; i < searchMenu.length; i++){
            if(searchMenu[i].dataValues.image!=""){
              const binaryData = fs.readFileSync(searchMenu[i].image)
              searchMenu[i].dataValues.image = Buffer(binaryData).toString('base64')
            } 
            var nama="System"
            if(searchMenu[i].dataValues.user_id!==0){
                temp = await Users.findByPk(searchMenu[i].dataValues.user_id)
                nama = temp.dataValues.name.toString()
                console.log(nama)
            }
 
            hasil.push({
                id: searchMenu[i].dataValues.id,
                name: searchMenu[i].dataValues.name,
                ingredients: searchMenu[i].dataValues.ingredients,
                nutrition: searchMenu[i].dataValues.nutrition,
                how_to_make: searchMenu[i].dataValues.how_to_make,
                note: searchMenu[i].dataValues.note,
                like: searchMenu[i].dataValues.like,
                date: searchMenu[i].dataValues.date,
                image: searchMenu[i].dataValues.image,
                nama_user: nama
            })
        }
          console.log(hasil.length)
          return res.status(200).send(hasil)
    },

    getMenuDiet: async(req,res)=>{ 
        const {user_id} = req.query
        let search = await Diets.findAll({
            where:{
                user_id: user_id
            }
        })
        return res.status(200).send(search)
    },

    getmenuById: async(req,res)=>{
        const {id} = req.query
        console.log(id)
        var list = id.split(",")
        var hasil = []

        for(let i = 0; i < list.length; i++){
            let search = await Menu.findOne({
                where:{
                    id: list[i]
                }
            })
            if(search == null) continue
            if(search.dataValues.image!=""){
              const binaryData = fs.readFileSync(search.image)
              search.dataValues.image = Buffer(binaryData).toString('base64')
            } 
            hasil.push(search) 
        }
        return res.status(200).send(hasil)
    },

    saveMenuDiet: async(req,res)=>{
        const data = req.body
        for(let i = 0; i < data.length; i++){
            var {id,menu} = data[i]
            const dietData = await Diets.findByPk(id)
            if(menu == undefined) menu = null
            dietData.menu = menu
            await dietData.save()
        }
        return res.status(200).send({text: "Success"})
    },

    getOneMenuById: async(req, res)=>{
        const {id} = req.query
        let menu = await Menu.findByPk(id)
        if(menu.image!=""){
            const binaryData = fs.readFileSync(menu.image)
            menu.image = Buffer(binaryData).toString('base64')
          } 
        return res.status(200).send(menu)
    },
    searchImageByQuery: async(req, res)=>{
        const {
            id, q,
        } = req.query;
        
        let searchMenu = await Menu.findAll({
        where: { 
            name: {
            [Op.like]: '%' + q + '%' 
            },
            user_id: {
                [Op.ne]: id
            },
            status: 1
        }
        });
        
        for(let i = 0; i < searchMenu.length; i++){
            if(searchMenu[i].dataValues.image!=""){
              const binaryData = fs.readFileSync(searchMenu[i].image)
              searchMenu[i].dataValues.image = Buffer(binaryData).toString('base64')
            }         
        }
        return res.status(200).json(searchMenu)       
    }
}