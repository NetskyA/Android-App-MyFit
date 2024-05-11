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

        for (let i = 0; i < users.length; i++) {
            await Users.create({
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
        }

        for (let i = 0; i < menus.length; i++) {
            await Menus.create({
                name: menus[i].name,
                ingredients: menus[i].ingredients,
                nutrition: menus[i].nutrition,
                how_to_make: menus[i].how_to_make,
                note: menus[i].note,
                like: menus[i].like,
                date: menus[i].date,
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