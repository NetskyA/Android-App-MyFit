const { Sequelize } = require("sequelize");

const sequelize = new Sequelize("proyek_mdp", "root", "", {
  host: "localhost",
  dialect: "mysql",
});

module.exports = sequelize;
