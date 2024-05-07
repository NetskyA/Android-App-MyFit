const { Model, DataTypes } = require("sequelize");
const sequelize = require("../database/connection");

class Likes extends Model {}
Likes.init(
  {
    id: {
      type: DataTypes.INTEGER,
      primaryKey: true,
      autoIncrement: true
    },
    user_id: {
      type: DataTypes.INTEGER,
    },
    menu_id: {
      type: DataTypes.INTEGER,
    },
  },
  {
    sequelize: sequelize,
    modelName: "Likes",
    tableName: "likes",
    timestamps: false,
  }
);

module.exports = Likes;
