'use strict';

require('source-map-support/register');

module.exports = {

    database: {
        host: 'localhost',
        user: 'root',
        password: 'ubuntu',
        database: 'cafe'
    },

    port: process.env.PORT || 8080,

    env: process.env.NODE_ENV || 'dev' // prod, dev

};
//# sourceMappingURL=../maps/config/local.js.map
