'use strict';

var _mysql = require('mysql');

var _mysql2 = _interopRequireDefault(_mysql);

var _local = require('./config/local');

var _local2 = _interopRequireDefault(_local);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var options = _local2.default.database;

var con = _mysql2.default.createConnection(options);

con.connect(function (error) {
    if (!error) {
        console.log("Database is connected!");
    } else {
        console.log("Error connecting database!");
    }
});

module.exports = con;
//# sourceMappingURL=maps/query.js.map
