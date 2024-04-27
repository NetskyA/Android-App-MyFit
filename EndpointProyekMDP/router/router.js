const {
  register,
  login,
} = require("../controllers/UserController");

const { Router } = require("express");

const router = Router();

// Enpoint Users
router.post("/users/register", register); // Buat register user
router.post("/users/login", login); // Buat login user

module.exports = router;
