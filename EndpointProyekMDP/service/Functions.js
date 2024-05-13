const { faker } = require("@faker-js/faker");
faker.seed(42);

const testFunction = (key) => {
  return key;
};

// Function to generate random date of birth
const generateDOB = () => {
  const year = faker.number.int({ min: 1990, max: 2006 });
  const month = faker.number.int({ min: 1, max: 12 });
  const day = faker.number.int({ min: 1, max: 28 }); // Assuming all months have max 28 days
  return `${day}/${month}/${year}`;
};

// Function to generate random gender
const generateGender = () => faker.number.int({ min: 1, max: 2 }); // Assuming 1 for male, 2 for female

// Function to generate random blood type
const generateBloodType = () =>
  faker.string.fromCharacters(["A", "B", "AB", "O"]);

// Function to generate dummy data for a single user
const generateUser = (id) => ({
  email: faker.internet.email(),
  username: faker.internet.userName(),
  phone: faker.phone.number(),
  name: faker.person.fullName(),
  password: "$2b$10$eLPKVZ24yygu1J2MUEdB6OweiifAmkx71a5QgT9sky2u6zcgdpo9m",
  dob: generateDOB(),
  gender: generateGender(),
  height: faker.number.int({ min: 150, max: 190 }),
  weight: faker.number.int({ min: 40, max: 100 }),
  age: faker.number.int({ min: 18, max: 40 }),
  blood_type: generateBloodType(),
  allergy: "Nope",
  image: `uploads/profile/${id}.jpg`,
});

const generateDummyUsers = () => {
  const users = [
    {
      email: "zee@gmail.com",
      username: "zee",
      phone: "081123456789",
      name: "Azizi Asadel",
      password: "$2b$10$eLPKVZ24yygu1J2MUEdB6OweiifAmkx71a5QgT9sky2u6zcgdpo9m",
      dob: "12/12/2004",
      gender: 2,
      height: 164,
      weight: 53,
      age: 20,
      blood_type: "O",
      allergy: "Nope",
      image: "uploads/profile/1.jpg",
    },
    {
      email: "vonzy@gmail.com",
      username: "vonzy",
      phone: "081987654321",
      name: "Vonny Felicia",
      password: "$2b$10$eLPKVZ24yygu1J2MUEdB6OweiifAmkx71a5QgT9sky2u6zcgdpo9m",
      dob: "1/2/2001",
      gender: 2,
      height: 160,
      weight: 50,
      age: 21,
      blood_type: "AB",
      allergy: "Nope",
      image: "uploads/profile/2.jpg",
    },
  ];
  for (let i = 3; i <= 50; i++) {
    users.push(generateUser(i));
  }
  console.log(users);
  return users;
};

module.exports = {
  testFunction: testFunction,
  generateDummyUsers: generateDummyUsers,
};
