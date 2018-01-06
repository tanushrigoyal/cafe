'use strict';

var _express = require('express');

var _express2 = _interopRequireDefault(_express);

var _bodyParser = require('body-parser');

var _bodyParser2 = _interopRequireDefault(_bodyParser);

var _index = require('./api/login/index');

var _index2 = _interopRequireDefault(_index);

var _index3 = require('./api/signup/index');

var _index4 = _interopRequireDefault(_index3);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var router = (0, _express2.default)();
router.use(_bodyParser2.default.json());
router.use(_bodyParser2.default.urlencoded({ extended: true }));
router.use(_bodyParser2.default.text());
router.use(_bodyParser2.default.json({ type: 'application/json' }));

var Login = _express2.default.Router();
Login.get('/:phone/:pwd', _index2.default, function (req, res) {});

var Signup = _express2.default.Router();
Signup.post('/', _index4.default, function (req, res) {});

router.use('/login', Login);
router.use('/signup', Signup);

router.listen(8080);
//# sourceMappingURL=maps/index.js.map
