'use strict';

require('source-map-support/register');

var _query = require('../../query');

var _query2 = _interopRequireDefault(_query);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function signup(req, res, next) {
    var data = JSON.parse(JSON.stringify(req.body));
    console.log(data);

    _query2.default.query('SELECT * FROM register WHERE phone = ?  AND pwd =?', [data.phone, data.pwd], function (err, results) {
        console.log(results);
        if (results.length) {
            res.json({
                success: "false",
                msg: "mobile no. already registered",
                result: results,
                error: "true"
            });
        } else {

            _query2.default.query('INSERT INTO register SET ?', data, function (error, result, fields) {
                if (result.affectedRows) {
                    res.json({
                        success: true,
                        msg: "successfully signed up",
                        result: data
                    });
                } else {
                    res.json({
                        success: "false",
                        error: "cannot signup"
                    });
                }
            });
        }
    });
};
module.exports = signup;
//# sourceMappingURL=../../maps/api/signup/index.js.map
