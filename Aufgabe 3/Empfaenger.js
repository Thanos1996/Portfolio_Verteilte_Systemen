import { Configuration } from './Gemeinsam.js';

const config = new Configuration();


try {

   let message = config.consume();
    console.log(message);

} catch (err) {
    console.log(err)
}