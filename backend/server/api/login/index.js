'use strict';

import 'source-map-support/register';
import query from '../../query';

function login (req, res, next){
    query.query('SELECT * FROM register WHERE phone = ?  AND pwd =?',[req.params.phone , req.params.pwd], function(err, results) {
        console.log(results);
    if (results.length) {
        res.json({
            success: "true",
            msg: "successfully logged in",
            result: results,
            error: "false"
        });

    }
    else {
        res.json({
            success: "false",
            error:"cannot login"
        });

    }

});
};
module.exports=login;
