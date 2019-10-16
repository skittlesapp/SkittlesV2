/**
 * @author Simphiwe Zulu
 * @description This file contains all functions that will be deployed
 *              as Cloud Functions to the Firebase project that will
 *              be used when working with Sprints.
 */

 const functions = require('firebase-functions');
 const admin = require('firebase-admin');
 const errors = require('./errors');