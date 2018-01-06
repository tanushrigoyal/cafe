'use strict';

require('source-map-support/register');

var _query = require('../../query');

var _query2 = _interopRequireDefault(_query);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function login(req, res, next) {
    _query2.default.query('SELECT * FROM register WHERE phone = ?  AND pwd =?', [req.params.phone, req.params.pwd], function (err, results) {
        console.log(results);
        if (results.length) {
            res.json({
                success: "true",
                msg: "successfully logged in",
                result: results,
                error: "false"
            });
        } else {
            res.json({
                success: "false",
                error: "cannot login"
            });
        }
    });
};
module.exports = login;
//# sourceMappingURL=../../maps/api/login/index.js.map
