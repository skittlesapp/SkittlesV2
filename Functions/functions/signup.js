/**
 * @author Simphiwe Zulu
 * @description This file contains all functions that will be deployed
 *              as Cloud Functions to the Firebase project that will
 *              be used when creating a new user's account.
 */

const functions = require('firebase-functions');
const admin = require('firebase-admin');
const errors = require('./errors');


/*=================================Exports===================================*/
/**
 * Creates a new document in the users collection
 */
exports.createUserDocument = function (data, context) {
    var uid = context.auth.uid;

    var email = data.email;
    var fname = data.fname;
    var lname = data.lname;
    var phone = data.phone;
    var acc_type = data.acc_type;
    var university_id = data.university_id;
    var university = data.university;
    var claim = setCustomClaim(acc_type, uid);
    var promise = claim.then(() =>{
        return writeToNewUser(acc_type, email, fname, lname, phone, university, university_id, uid);
    }).catch(errors.onError);
    return promise;
}

/**
 * Creates new user as well as the user document
 */
exports.createUser = function (data, context){
    var acc_type = data.acc_type;
    var email = data.email;
    var fname = data.fname;
    var lname = data.lname;
    var password = data.password;
    var phone = data.phone;
    var university = data.university;
    var university_id = data.university_id;

    var user = createNewUserAuth(email, phone, password, fname, lname);
    var uid;
    /*
    user.then((value) => {
        uid = value.uid;
        return;
    }).catch(onError);
    

    var claim = setCustomClaim(acc_type, email);
    var promise = claim.then(() => {
        if (user.error) {
            return user;
        }
        return writeToNewUser(acc_type, email, fname, lname, university, university_id, uid);
    }).catch(errors.onError);

    return promise;
    */
    var promise = setCustomClaim(acc_type, uid).then(() => {
        return user;
    }).then((value) => {
        uid = value.uid;
        return writeToNewUser(acc_type, email, fname, lname, phone, university, university_id, uid);
    }).catch(errors.onError);

    return promise;
}



/*=======================Local Functions===========================*/

function createNewUserAuth(email, phone, password, fname, lname) {
    return admin.auth().createUser({
        email: email,
        emailVerified: false,
        phoneNumber: phone,
        password: password,
        displayName: fname + " " + lname,
        disabled: false
    }).then((user) => {
        return {
            uid: user.uid
        }
    }).catch(errors.onError);
}

function writeToNewUser(acc_type, email, fname, lname, phone, university, university_id, uid) {
    var ref = admin.firestore().doc('users/' + uid);
    console.log(uid);
    console.log(university_id);
    return ref.set({
        acc_type: acc_type,
        email: email,
        fname: fname,
        lname: lname,
        phone: phone,
        university: university,
        university_id: university_id
    }).then((docRef) => {
        return docRef;
    }).catch(errors.onError);
}


function setCustomClaim(acc_type, uid) {
    console.log(uid);
    var ref = admin.auth().getUser(uid);
    return ref.then((user) => {
        if (user.customClaims) {
            return Promise.reject(errors.customClaimExists());
        }

        var customClaim = {
            student: false,
            lecturer: false
        }

        if (acc_type === 'student') {
            customClaim.student = true;
        } else if (acc_type === "lecturer") {
            customClaim.lecturer = true;
        } else {
            return Promise.reject(errors.noClaimSpecified());
        }

        return admin.auth().setCustomUserClaims(user.uid, customClaim);
    }).catch(errors.onError);
}

/*
    var user = admin.auth().getUserByEmail(email);
    var error;
    if (user.customClaims) {
        error = errors.customClaimExists();
        return Promise.reject(error);
    }

    var customClaim = {
        student: false,
        lecturer: false
    }

    if (acc_type === "student") {
        customClaim.student = true;
    } else if (acc_type === "lecturer") {
        customClaim.lecturer = true;
    } else {
        error = errors.noClaimSpecified();
        return Promise.reject(error);
    }
    return admin.auth().setCustomUserClaims(user.uid, customClaim);
    */

