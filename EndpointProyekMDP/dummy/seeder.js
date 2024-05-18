const mysql = require("mysql2/promise");

const DB_NAME = "proyek_mdp"
const DB_HOST = "localhost"
const DB_USER = "root"
const DB_PASS = ""
const sequelize = require("../database/connection");

const Users = require("../models/Users");
const Menus = require("../models/Menus");
const Likes = require("../models/Likes");
const Diets = require("../models/Diets");

const users = require("./users");
const menus = require("./menus");
const likes = require("./likes");
const diets = require("./diets");

const { generateDummyUsers } = require("../service/Functions");

(async function () {
    try {
        console.log("START SEEDER");

        const conn = await mysql.createConnection({
            host: DB_HOST,
            user: DB_USER,
            password: DB_PASS
        });
        await conn.query(`CREATE DATABASE IF NOT EXISTS ${DB_NAME}`);
        await conn.end();

        await sequelize.sync({ force: true });
        // const users = generateDummyUsers
        for (let i = 0; i < users.length; i++) {
            var id =await Users.create({
                email: users[i].email,
                username: users[i].username,
                phone: users[i].phone,
                name: users[i].name,
                password: users[i].password,
                dob: users[i].dob,
                gender: users[i].gender,
                height: users[i].height,
                weight: users[i].weight,
                age: users[i].age,
                blood_type: users[i].blood_type,
                allergy: users[i].allergy ,
                image  : users[i].image  ,
            })

            var day = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]

            for(let i=0; i<day.length; i++){
                if(id.dataValues.id !== 1 && id.dataValues.id !== 2){
                    await Diets.create({ 
                        user_id:id.dataValues.id,
                        day: day[i]
                    })
                }
            }
        }

        for (let i = 0; i < menus.length; i++) {
            var acak = Math.floor(Math.random() * (users.length/2))
            var user_id = Math.floor((Math.random() * users.length/2) + 1)
            for (let j = 0; j < acak; j++) {
                let cek = false
                do {
                    cek = false
                    var acakUser = Math.floor((Math.random() * users.length) + 1)
                    if(acakUser==user_id) {
                        cek = true
                    }else {
                        await Likes.create({menu_id: i+1, user_id: acakUser})
                    }
                } while (cek);
            }
            await Menus.create({
                name: menus[i].name,
                user_id: user_id,
                ingredients: menus[i].ingredients,
                nutrition: menus[i].nutrition,
                how_to_make: menus[i].how_to_make,
                note: menus[i].note,
                like: menus[i].acak,
                date: menus[i].date,
                note: "",
                image: menus[i].image,
            })
        }

        for (let i = 0; i < likes.length; i++) {
            await Likes.create({
                user_id: likes[i].user_id,
                menu_id: likes[i].menu_id,
            })
        }

        for (let i = 0; i < diets.length; i++) {
            await Diets.create({
                user_id: diets[i].user_id,
                menu: diets[i].menu,
                day: diets[i].day,
            })
        }

        console.log("END SEEDER");
        process.exit(0);
    }
    catch (err) {
        console.log(err);
    }
})();