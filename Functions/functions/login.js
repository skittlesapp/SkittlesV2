/**
 * @author Simphiwe Zulu
 * @description
 */

const functions = require('firebase-functions');
const admin = require('firebase-admin');

exports.checkEmailVerification = function (data, context) {
    var uid = context.auth.uid;
    var promise;

    promise = admin.auth().getUser(uid).then((user) => {
        return {
            emailVerified:user.emailVerified
        };
    }).catch(onError);

    return promise;
}

function onError(error) {
    return Promise.reject(error);
}