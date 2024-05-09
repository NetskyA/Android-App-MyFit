const {
  test,
  register,
  login,
  cekEmailandUsername

} = require("../controllers/UserController");

const { Router } = require("express");

const router = Router();

// Test
router.get("/users/test", test)

// Enpoint Users
router.post("/users/register", register); // Buat register user
router.post("/users/login", login); // Buat login user

router.post("/users/cekEmailandUsername", cekEmailandUsername) // Buat cek email dan username duplicate

module.exports = router;
