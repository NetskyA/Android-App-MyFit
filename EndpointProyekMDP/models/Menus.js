const { Model, DataTypes } = require("sequelize");
const sequelize = require("../database/connection");

class Menus extends Model {}
Menus.init(
  {
    id: {
      type: DataTypes.INTEGER,
      primaryKey: true,
      autoIncrement: true
    },
    user_id:{
      type: DataTypes.INTEGER,
      allowNull: true
    },
    name: {
      type: DataTypes.TEXT,
    },
    ingredients: {
      type: DataTypes.TEXT,
    },
    nutrition: {
      type: DataTypes.TEXT,
    },
    how_to_make: {
      type: DataTypes.TEXT,
      allowNull: true,
    },
    note: {
      type: DataTypes.TEXT,
      allowNull: true,
    },
    like: {
      type: DataTypes.INTEGER,
      defaultValue: 1
    },
    date: {
      type: DataTypes.TEXT,
    },
    status : {
      type: DataTypes.INTEGER(1),
      defaultValue: 1
    },
    image:{
      type: DataTypes.TEXT,
      allowNull: true
    },
  },
  {
    sequelize: sequelize,
    modelName: "Menus",
    tableName: "menus",
    timestamps: false,
  }
);

module.exports = Menus;
