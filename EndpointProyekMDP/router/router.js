const {
  test,
  uploadDummyUser,
  register,
  login,
  cekEmailandUsername,
  cekPhoneNumber,
  sendCode,
  verifyOtp,
  updateProfile,
  checkEmail,
  checkPhone,
  checkUsername,
  uploadImageProfil,
  getImageProfil,
  search,
} = require("../controllers/UserController"); //function user

const { upload } = require("../controllers/MenuController"); //function menu

const { Router } = require("express");

const router = Router();

// Test
router.get("/users/test", test);
router.get("/users/uploadDummyUser", uploadDummyUser);

// Enpoint Users
router.post("/users/register", register); // Buat register user

router.post("/users/login", login); // Buat login user

router.post("/users/cekEmailandUsername", cekEmailandUsername); // Buat cek email dan username duplicate

router.post("/users/cekPhoneNumber", cekPhoneNumber); // Buat cek phone number

router.post("/users/sendCode", sendCode); // Buat kirim kode otp

router.post("/users/verifyOtp", verifyOtp); // Buat verifikasi kode otp

router.post("/users/updateProfile", updateProfile); // Buat update profil account dan body

router.post("/users/checkEmail", checkEmail); // Buat pengecekan email kembar

router.post("/users/checkPhone", checkPhone); // Buat pengecekan phone kembar

router.post("/users/checkUsername", checkUsername); // Buat pengecekan username kembar

router.post("/users/uploadImage", uploadImageProfil); // Buat upload image profile

router.post("/users/getImage", getImageProfil); // Buat get image profile

router.get("/users/search", search); // Buat search user

// Menu
// ==================================================================================
router.post("/menus/upload", upload); // Buat upload custom menu

module.exports = router;
