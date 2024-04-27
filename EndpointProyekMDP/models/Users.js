const { Model, DataTypes } = require("sequelize");
const sequelize = require("../database/connection");

class Users extends Model {}
Users.init(
  {
    username: {
      type: DataTypes.STRING(64),
      primaryKey: true,
    },
    email: {
      type: DataTypes.STRING(64),
      allowNull: true,
      unique: true
    },
    phone: {
      type: DataTypes.STRING(12),
      allowNull: true,
      unique: true
    },
    name: {
      type: DataTypes.TEXT,
      allowNull: true,
    },
    password: {
      type: DataTypes.TEXT,
      allowNull: false
    },
    image:{
      type: DataTypes.TEXT,
      allowNull: true
    }
  },
  {
    sequelize: sequelize,
    modelName: "Users",
    tableName: "users",
    timestamps: false,
  }
);

module.exports = Users;
