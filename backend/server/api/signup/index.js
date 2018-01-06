'use strict';

import 'source-map-support/register';
import query from '../../query';

function signup(req, res, next) {
    let data = JSON.parse(JSON.stringify(req.body));
    console.log(data);

    query.query('SELECT * FROM register WHERE phone = ?  AND pwd =?', [data.phone, data.pwd], function(err, results) {
        console.log(results);
        if (results.length) {
            res.json({
                success: "false",
                msg: "mobile no. already registered",
                result: results,
                error: "true"
            });

        } else {

            query.query('INSERT INTO register SET ?', data, function(error, result, fields) {
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
