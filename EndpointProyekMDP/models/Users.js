const { Model, DataTypes } = require("sequelize");
const sequelize = require("../database/connection");

class Users extends Model {}
Users.init(
  {
    id: {
      type: DataTypes.INTEGER,
      primaryKey: true,
      autoIncrement: true
    },
    email: {
      type: DataTypes.TEXT,
      unique: true
    },
    username: {
      type: DataTypes.TEXT,
      allowNull: true,
      unique: true
    },
    phone: {
      type: DataTypes.TEXT,
      allowNull: true,
      unique: true
    },
    name: {
      type: DataTypes.TEXT,
    },
    password: {
      type: DataTypes.TEXT,
      allowNull: true
    },
    dob: {
      type: DataTypes.TEXT,
      allowNull: true,
    },
    gender: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    height: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    weight: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    age: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
    blood_type : {
      type: DataTypes.STRING(3),
      allowNull: true,
    },
    allergy :{
      type: DataTypes.TEXT,
      allowNull: true
    },
    image:{
      type: DataTypes.TEXT,
      allowNull: true
    },
    status : {
      type: DataTypes.INTEGER(1),
      defaultValue: 1
    },
  },
  {
    sequelize: sequelize,
    modelName: "Users",
    tableName: "users",
    timestamps: false,
  }
);

module.exports = Users;
