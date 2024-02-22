import { Kafka } from "kafkajs";



export class Configuration {

    constructor() {
        this.kafka = new Kafka({
            clientId: "Athanasios.Leonis",
            brokers: ['zimolong.eu:9092'],
            sasl: {
                mechanism: "plain",
                username: "dhbw",
                password: "dhbw",
            },
        });

        this.producer = this.kafka.producer();
        this.consumer = this.kafka.consumer({groupId : "x"});
    }

    async produce(message) {
        try {
            await this.producer.connect()
            await this.producer.send({
                topic: "WWI22B2.LeonisAthanasios.Aufgabenblatt",
                messages: [message]
            })
        } catch (err) {
            console.log(err)
        }
        
    }

    async consume() {
        try{
            
            await this.consumer.connect()
            await this.consumer.subscribe({topic:"WWI22B2.LeonisAthanasios.Aufgabenblatt", fromBeginning:true})
            await this.consumer.run({
                    eachMessage: async ({message}) =>{
                        console.log('Bestellung', {
                            value: message.value.toString()
                    });
                }
            })
        }catch (err){
            console.log(err)
        }
    }
    createJsonObject(wrapTyp, FleischGramms, gemüse, scharf) {
        let message = {
            key: "", value: JSON.stringify({
                "Wrap_Typ": wrapTyp,
                "Gemuese": gemüse,
                "Fleisch_Gramms": FleischGramms,
                "Scharf": scharf
            })
        }
        return message;
    }

}

