'use strict';

import express from 'express';
import bodyParser from 'body-parser';
import login from './api/login/index';
import signup from './api/signup/index';

const router = express();
router.use(bodyParser.json());
router.use(bodyParser.urlencoded({ extended: true }));
router.use(bodyParser.text());
router.use(bodyParser.json({ type: 'application/json' }));

var Login = express.Router();
Login.get('/:phone/:pwd',login, function(req, res) { });

var Signup = express.Router();
Signup.post('/',signup, function(req, res) { });

router.use('/login', Login);
router.use('/signup', Signup);


router.listen(8080);
