const { Sequelize, Op } = require("sequelize");

const multer = require("multer");
const fs = require("fs");

const Likes = require("../models/Likes");
const Menus = require("../models/Menus");

module.exports = {
    getLikeMenu: async(req, res)=>{
        const {menu_id, user_id} = req.query
        const cek = await Likes.findOne({
            where:{
                menu_id: menu_id,
                user_id: user_id
            }
        })
        if(cek)return res.status(200).send("like")
        else return res.status(200).send("unlike")
        
    },
    likeMenu: async(req, res)=>{
        const {menu_id, user_id} = req.query
        const cek = await Likes.findOne({
            where:{
                menu_id: menu_id,
                user_id: user_id
            }
        })
        if(cek){
            await Likes.destroy({
                where:{
                    menu_id: menu_id,
                    user_id: user_id
                }
            })
            var menu = await Menus.findByPk(menu_id);
            menu.decrement('like',{by:1})
            return res.status(200).send("unlike")
        }else{
            await Likes.create({
                menu_id:menu_id,
                user_id:user_id
            })
            var menu = await Menus.findByPk(menu_id);
            menu.increment('like',{by:1})
        }
        return res.status(200).send("like")
    },
    savedMenu: async (req, res)=>{
        const {id} = req.query
        const like = await Likes.findAll({
            where:{
                user_id: id
            },
            order: [['id', 'DESC']]
        })
        const savedMenu = []
        for (let i = 0; i < like.length; i++) {
            const l = like[i];
            const menu = await Menus.findOne({
                where:{
                    id: l.menu_id,
                    status: 1
                }
            })
            if(menu.image!=""){
                const binaryData = fs.readFileSync(menu.image)
                menu.image = Buffer(binaryData).toString('base64')
              } 
            savedMenu.push(menu)
        }
        return res.status(200).json(savedMenu)        
    },
}