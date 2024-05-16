const { Sequelize, Op } = require("sequelize");

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
}