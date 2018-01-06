import mysql from 'mysql';
import config from './config/local';

let options = config.database;


let con = mysql.createConnection(options);

con.connect( function (error){
if(!error) {
    console.log("Database is connected!");
} else {
    console.log("Error connecting database!");
}
});

module.exports = con;
