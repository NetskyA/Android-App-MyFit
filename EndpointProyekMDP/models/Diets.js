const { Model, DataTypes } = require("sequelize");
const sequelize = require("../database/connection");

class Diets extends Model {}
Diets.init(
  {
    id: {
      type: DataTypes.INTEGER,
      primaryKey: true,
      autoIncrement: true
    },
    user_id: {
      type: DataTypes.INTEGER,
    },
    menu: {
      type: DataTypes.TEXT,
      allowNull: true
    },
    day: {
      type: DataTypes.TEXT,
    }
  },
  {
    sequelize: sequelize,
    modelName: "Diets",
    tableName: "diets",
    timestamps: false,
  }
);

module.exports = Diets;
