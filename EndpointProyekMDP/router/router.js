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
  checkPass,
  getUsername,
} = require("../controllers/UserController"); //function user

const { 
  setDummy,
  upload,
  getAllMenuUser,
  getRandomMenu,
  getMenuDiet,
  searchAllMenu,
  getmenuById,
  saveMenuDiet,
  getOneMenuById,
} = require("../controllers/MenuController"); //function menu

const { 
  getLikeMenu,
  likeMenu,
} = require("../controllers/LikeController"); //function like

const { Router } = require("express");

const router = Router();

// Test
router.get("/users/test", test);
router.get("/users/uploadDummyUser", uploadDummyUser);
router.get("/menus/setDummy", setDummy);

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

router.post("/users/checkPass", checkPass); // Buat check dan update password

router.get("/users/getUsername", getUsername); // Buat get username

// Menu
// ==================================================================================
router.post("/menus/upload", upload); // Buat upload custom menu

router.get("/menus/getAllMenuUser", getAllMenuUser); //Buat get all menu yang dipunya user

router.get("/menus/getRandomMenu", getRandomMenu); //Buat get 20 random menu

router.get("/menus/getMenuDiet", getMenuDiet); //Buat get menu diet

router.get("/menus/searchAllMenu", searchAllMenu); //Buat search all menu

router.get("/menus/getmenuById", getmenuById); //Buat get menu by id

router.post("/menus/saveMenuDiet", saveMenuDiet); //Buat save menu diet

router.get("/menus/getOneMenuById", getOneMenuById); //Buat Get menu by id

// Like
// ==================================================================================
router.get("/likes/getLikeMenu", getLikeMenu); //Buat get like

router.post("/likes/likeMenu", likeMenu); //Buat like dan unlike menu

module.exports = router;
