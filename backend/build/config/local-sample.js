'use strict';

require('source-map-support/register');

module.exports = {

    database: {
        host: 'localhost',
        user: '< MySQL username >',
        password: '< MySQL password >',
        database: '<your database name>'
    },

    port: process.env.PORT || 8080,

    env: process.env.NODE_ENV || 'dev' // prod, dev

};
//# sourceMappingURL=../maps/config/local-sample.js.map
