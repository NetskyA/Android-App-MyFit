const fs = require('fs');

function readJSONFile() {
  const data = [];
  data.push(
    fs.readFileSync("../data/salad.json", "utf8"),
    fs.readFileSync("../data/grilled.json", "utf8"),
    fs.readFileSync("../data/yoghurt.json", "utf8"),
    fs.readFileSync("../data/juice.json", "utf8"),
    fs.readFileSync("../data/oatmeal.json", "utf8"),
    fs.readFileSync("../data/porridge.json", "utf8"),
    fs.readFileSync("../data/rice.json", "utf8"),
    fs.readFileSync("../data/smoothie.json", "utf8")
  );
  var datareturn = [];
  var tempId = 0;
  for (let i = 0; i < data.length; i++) {
    const jsonData = JSON.parse(data[i]);
    for (let j = 0; j < jsonData.results.length; j++) {
        let ingredients = [];
        tempId++
        for (let k = 0; k < jsonData.results[j].extendedIngredients.length; k++) {
            ingredients.push(jsonData.results[j].extendedIngredients[k].original);
        }
        let nutrition = [];
        for (let k = 0; k < jsonData.results[j].nutrition.nutrients.length; k++) {
            nutrition.push(
            jsonData.results[j].nutrition.nutrients[k].name +
                "~" +
                jsonData.results[j].nutrition.nutrients[k].amount +
                "~" +
                jsonData.results[j].nutrition.nutrients[k].unit
            );
        }
        let howtomake = [];
        for (
            let k = 0;
            k < jsonData.results[j].analyzedInstructions.length;
            k++
        ) {
            if (jsonData.results[j].analyzedInstructions[k].name !== "") {
            howtomake.push(jsonData.results[j].analyzedInstructions[k].name);
            }
            for (
            let l = 0;
            l < jsonData.results[j].analyzedInstructions[k].steps.length;
            l++
            ) {
            howtomake.push(
                jsonData.results[j].analyzedInstructions[k].steps[l].step
            );
            }
        }

        datareturn.push({
            name: jsonData.results[j].title,
            image: `uploads/menu/${tempId}.jpg`,
            ingredients: JSON.stringify(ingredients),
            nutrition: JSON.stringify(nutrition),
            how_to_make: JSON.stringify(howtomake),
            date: "11/05/2024",
        });
    }
  }
  return datareturn;
}

const menus = readJSONFile();

module.exports = menus;
