const {
  test,
  register,
  login,
  cekEmailandUsername,
  cekPhoneNumber,
  sendCode,
  verifyOtp,

} = require("../controllers/UserController");

const { Router } = require("express");

const router = Router();

// Test
router.get("/users/test", test)

// Enpoint Users
router.post("/users/register", register); // Buat register user
router.post("/users/login", login); // Buat login user

router.post("/users/cekEmailandUsername", cekEmailandUsername) // Buat cek email dan username duplicate

router.post("/users/cekPhoneNumber", cekPhoneNumber) // Buat cek phone number

router.post("/users/sendCode", sendCode) // Buat kirim kode otp

router.post("/users/verifyOtp", verifyOtp) // Buat verifikasi kode otp

module.exports = router;
