const fs = require('fs');

function readJSONFile() {
  const data = [];
  data.push(
    fs.readFileSync("./data/salad.json", "utf8"),
    fs.readFileSync("./data/grilled.json", "utf8"),
    fs.readFileSync("./data/yoghurt.json", "utf8"),
    fs.readFileSync("./data/juice.json", "utf8"),
    fs.readFileSync("./data/oatmeal.json", "utf8"),
    fs.readFileSync("./data/porridge.json", "utf8"),
    fs.readFileSync("./data/rice.json", "utf8"),
    fs.readFileSync("./data/smoothie.json", "utf8")
  );
  var datareturn = [];
  var tempId = 0;
  for (let i = 0; i < data.length; i++) {
    const jsonData = JSON.parse(data[i]);
    for (let j = 0; j < jsonData.results.length; j++) {
        let ingredients ="";
        tempId++
        for (let k = 0; k < jsonData.results[j].extendedIngredients.length; k++) {
            ingredients += `${(jsonData.results[j].extendedIngredients[k].original)} \n`;
        }
        let nutrition = "";
        for (let k = 0; k < jsonData.results[j].nutrition.nutrients.length; k++) {
            nutrition +=`${
            jsonData.results[j].nutrition.nutrients[k].name}: ${jsonData.results[j].nutrition.nutrients[k].amount} ${jsonData.results[j].nutrition.nutrients[k].unit} \n`;
        }
        let howtomake = "";
        for (
            let k = 0;
            k < jsonData.results[j].analyzedInstructions.length;
            k++
        ) {
            if (jsonData.results[j].analyzedInstructions[k].name !== "") {
            howtomake += `${jsonData.results[j].analyzedInstructions[k].name} \n`;
            }
            for (
            let l = 0;
            l < jsonData.results[j].analyzedInstructions[k].steps.length;
            l++
            ) {
            howtomake += `${jsonData.results[j].analyzedInstructions[k].steps[l].step} \n`
            }
        }

        datareturn.push({
            name: jsonData.results[j].title,
            image: `uploads/menu/${tempId}.jpg`,
            ingredients:ingredients,
            nutrition: nutrition,
            how_to_make: howtomake,
            date: "11/05/2024",
        });
    }
  }
  return datareturn;
}

const menus = readJSONFile();

module.exports = menus;
