const express = require("express");
const router = require("./router/router");
const app = express();

app.use(express.json({ limit: "50mb" }));
app.use(express.urlencoded({ limit: "50mb", extended: true }));

app.use("/api", router);

app.listen(3000, () =>
  console.log(`Server is running at http://localhost:3000`)
);



app.get("/test2",(req,res)=>{
  const data = []
  data.push(fs.readFileSync('data/salad.json', 'utf8'),fs.readFileSync('data/grilled.json', 'utf8'),fs.readFileSync('data/yoghurt.json', 'utf8'),fs.readFileSync('data/juice.json', 'utf8'),fs.readFileSync('data/oatmeal.json', 'utf8'),fs.readFileSync('data/porridge.json', 'utf8'),fs.readFileSync('data/rice.json', 'utf8'),fs.readFileSync('data/smoothie.json', 'utf8'))
  var datareturn = [];
  for(let i = 0; i < data.length; i++){
    const jsonData = JSON.parse(data[i]);
    for (let j = 0; j < jsonData.results.length; j++) {
      let ingredients = [];
      for(let k = 0; k < jsonData.results[j].extendedIngredients.length; k++){
        ingredients.push(jsonData.results[j].extendedIngredients[k].original)
      }
      let nutrition = [];
      for(let k = 0; k < jsonData.results[j].nutrition.nutrients.length; k++){
        nutrition.push(jsonData.results[j].nutrition.nutrients[k].name + "~" + jsonData.results[j].nutrition.nutrients[k].amount + "~" + jsonData.results[j].nutrition.nutrients[k].unit)
      }
      let howtomake = [];
      for(let k = 0; k < jsonData.results[j].analyzedInstructions.length; k++){
        if(jsonData.results[j].analyzedInstructions[k].name !== ""){
          howtomake.push(jsonData.results[j].analyzedInstructions[k].name)
        }
        for(let l = 0; l < jsonData.results[j].analyzedInstructions[k].steps.length; l++){
          howtomake.push(jsonData.results[j].analyzedInstructions[k].steps[l].step)
        }
      }
      datareturn.push({
        title: jsonData.results[j].title,
        thumbnail: jsonData.results[j].image,
        ingredients: JSON.stringify(ingredients),
        nutrition: JSON.stringify(nutrition),
        howtomake: JSON.stringify(howtomake)
      })
    }
  }
  return res.send(datareturn) 
}) 