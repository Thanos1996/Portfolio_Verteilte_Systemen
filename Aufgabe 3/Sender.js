import pkg from 'readline-sync';
import { Configuration } from './Gemeinsam.js';





const readlineSync = pkg;
const config = new Configuration();



console.log("Willkommen in Doener Laden");
const wrapTyp = readlineSync.question("Welche Wrap-Typ moechten Sie fuer ihr Doenner? (Yufka,Flatenbrot): ");
const FleischGramms = readlineSync.questionInt("Wie viele Gramm Fleisch soll die Bestellung haben? (z.B 500): ");
const gemüse = readlineSync.question("Mit allen Gemuesen? (Ja, Ohne x): ");
const scharf = readlineSync.question("Moechten Sie scharf? (Ja, Nein): ");

let message = config.createJsonObject(wrapTyp, FleischGramms, gemüse,scharf);
try {

    config.produce(message);
    console.log("Message Produced")

} catch (err) {
    console.log(err)
}


