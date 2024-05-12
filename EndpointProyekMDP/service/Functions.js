
const testFunction = (key) => {
  return key;
};

// Function to generate random date of birth
const generateDOB = () => {
    const year = faker.random.number({ min: 1990, max: 2006 });
    const month = faker.random.number({ min: 1, max: 12 });
    const day = faker.random.number({ min: 1, max: 28 }); // Assuming all months have max 28 days
    return `${day}/${month}/${year}`;
};

// Function to generate random gender
const generateGender = () => faker.random.arrayElement([1, 2]); // Assuming 1 for male, 2 for female

// Function to generate random blood type
const generateBloodType = () => faker.random.arrayElement(["A", "B", "AB", "O"]);

// Function to generate dummy data for a single user
const generateUser = (id) => ({
    email: faker.internet.email(),
    username: faker.internet.userName(),
    phone: faker.phone.phoneNumber(),
    name: faker.name.findName(),
    password: "$2b$10$eLPKVZ24yygu1J2MUEdB6OweiifAmkx71a5QgT9sky2u6zcgdpo9m",
    dob: generateDOB(),
    gender: generateGender(),
    height: faker.random.number({ min: 150, max: 190 }),
    weight: faker.random.number({ min: 40, max: 100 }),
    age: faker.random.number({ min: 18, max: 40 }),
    blood_type: generateBloodType(),
    allergy: "",
    image: `uploads/profile/${id}.jpg`
});

const generateDummyUsers = () => {
  const users = [
    {email: "zee@gmail.com", username: "zee", phone: "081123456789", name: "Azizi Asadel", password: "$2b$10$eLPKVZ24yygu1J2MUEdB6OweiifAmkx71a5QgT9sky2u6zcgdpo9m", dob: "12/12/2004", gender: 2, height: 164, weight: 53, age: 20, blood_type: "O", allergy: "Nope", image: "uploads/profile/1.jpg"},
    {email: "vonzy@gmail.com", username: "vonzy", phone: "081987654321", name: "Vonny Felicia", password: "$2b$10$eLPKVZ24yygu1J2MUEdB6OweiifAmkx71a5QgT9sky2u6zcgdpo9m", dob: "1/2/2001", gender: 2, height: 160, weight: 50, age: 21, blood_type: "AB", allergy: "Nope", image: "uploads/profile/2.jpg"},
  ];
  for (let i = 3; i <= 50; i++) {
      users.push(generateUser(i));
  }
  console.log(users)
  return users;
};

module.exports = {
  testFunction: testFunction,
  // generateDummyUsers: generateDummyUsers
};
