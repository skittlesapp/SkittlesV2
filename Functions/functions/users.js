/**
 * @author Simphiwe Zulu
 * @description This file contains all the functions that are related to a user's profile
 *              in the application.
 */

const functions = require('firebase-functions');
const admin = require('firebase-admin');
const errors = require('./errors');

/**
 * Returns a promise that contains a snapshot of the user's profile
 */
exports.getUserProfile = function (data, context) {
    // get user's uid and document reference
    var uid = context.auth.uid;
    var ref = admin.firestore().doc('users/' + uid);

    // return result of getting the document
    return ref.get();
}

/**
 * Returns a promise that contains an object with a few of a user's
 * details
 */
exports.getUserProfileOverview = function (data, context) {
    // get user's uid and document reference
    var uid = data.uid;
    var ref = admin.firestore().doc('users/' + uid);

    // return promise with the object required
    return ref.get().then((snapshot) => {
        // check if data exists
        if (!snapshot.exists) {
            // return rejected promise
            var error = errors.documentDoesNotExist();
            return Promise.reject(error);
        }

        // get snapshot data
        var snapdata = snapshot.data();
        // return object
        return {
            fname: snapdata.fname,
            lname: snapdata.lname,
            university: snapdata.university,
            email: snapdata.email
        };
    });
}