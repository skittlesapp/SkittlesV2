/**
 * @author Simphiwe Zulu
 * @description This file contains all the functions that are related to projects
 *              in the applications.
 */

const functions = require('firebase-functions');
const admin = require('firebase-admin');
const errors = require('./errors');

/**
 * Creates new project
 */
exports.createProject = function (data, context){
    /*
    // get user object
    var user = admin.auth().getUserByEmail(context.auth.email);
    var out;

    // check user claim
    if (user.customClaims.student) {
        out = errorInvalidClaim();
    } else if (user.customClaims.lecturer) {
        // get object variables
        var lecturer = {
            uid: user.uid,
            name: user.displayName,
            email: user.email
        };
        var projectid = data.projectid;
        var name = data.name;
        var description = data.description;
        var password = data.password;

        out = writeToNewProjectDoc(projectid, name, description, password, lecturer);

    } else {
        out = errorNoUserClaim();
    }
    return out;
    */

    return admin.auth().getUser(context.auth.uid).then((user) => {
        if (user.customClaims.valueOf().lecturer) {
            var lecturer = {
                uid: user.uid,
                name: user.displayName,
                email: user.email
            };
            var projectid = data.projectid;
            var name = data.name;
            var description = data.description;
            var password = data.password;

            return writeToNewProjectDoc(projectid, name, description, password, lecturer);
        } else if (user.customClaims.valueOf().student) {
            var error = errors.invalidClaim();
            return Promise.reject(error);
        } else {
            error = errors.noUserClaim();
            return Promise.reject(error);
        }
    }).then((value) => {
        return writeToUser(context.auth.uid, value.projectid, value.name, value.description);
    }).catch(errors.onError);
}

/**
 * Writes new project document
 * @param {String} name 
 * @param {String} description 
 * @param {String} password 
 * @param {Object} lecturer 
 */
function writeToNewProjectDoc(projectid, name, description, password, lecturer) {
    // get document reference
    var ref = admin.firestore().doc('projects/' + projectid);

    // create new document for project
    return ref.set({
        lecturer: lecturer,
        name: name,
        description: description,
        password: password
    }).then((value) => {
        return {
            projectid: projectid,
            name: name,
            description: description
        };
    }).catch(errors.onError);
}

/**
 * Writes new project document in a user's profile document
 * @param {String} uid 
 * @param {String} projectid 
 * @param {String} name 
 * @param {String} description 
 */
function writeToUser(uid, projectid, name, description) {
    var ref = admin.firestore().doc('users/' + uid + '/projects/' + projectid);
    return ref.set({
        name: name,
        description: description
    });
}

/**
 * Adds a new member to the project
 */
exports.joinProject = function (data, context) {
    var uid = context.auth.uid;
    var user = admin.auth().getUser(uid);
    var projectid = data.projectid;
    
    return user.then((user) => {
        if (user.customClaims.student || user.customClaims.lecturer) {
            return addNewMember(projectid, user.displayName, user.email, user.uid);
        } else {
            var error = errorNoUserClaim();
            return Promise.reject(error);
        }
    }).then((value) => {
        return writeToMember(value.projectid, value.uid);
    }).catch(errors.onError);
}

/**
 * 
 * @param {*} projectid 
 * @param {*} uid 
 */
function writeToMember(projectid, uid) {
    var ref = admin.firestore().doc('projects/' + projectid);

    return ref.get().then((snapshot) => {
        if (!snapshot.exists) {
            return Promise.reject(errors.documentDoesNotExist());
        }
        var data = snapshot.data();
        return {
            name: data.name,
            description: data.description
        }
    }).then((value) => {
        return writeToUser(uid, projectid, value.name, value.description);
    }).catch(errors.onError);
}

/**
 * Adds a new member document in the project document specified by projectid
 * @param {String} projectid id of the project
 * @param {String} displayName new member's display name
 * @param {String} email new member's email address
 * @param {String} uid new member's user id
 */
function addNewMember(projectid, displayName, email, uid) {
    var ref = admin.firestore().doc("projects/" + projectid + "/members/" + uid);
    return ref.set({
        name: displayName,
        email: email
    }).then((docRef) => {
        return {
            uid: uid,
            projectid: projectid
        };
    }).catch(errors.onError);
}

/**
 * Sends project data to the client app.
 */ 
exports.loadProjectData = function (data, context) {
    var uid = context.auth.uid;
    var projectid = data.projectid;
    
    // Check custom user claim
    var claim = getUserCustomClaim(uid);
    return claim.then((data) => {
        if (data.student) {
            return getProjectMember(uid, projectid);
        } else if (data.lecturer) {
            return getProjectProductOwner(uid, projectid);
        } else {
            return Promise.reject(errorNoUserClaim());
        }
    }).catch(errors.onError);
}
/*
exports.loadProjectData = function (data, context) {
    var uid = context.auth.uid;
    var user = admin.auth().getUser(uid);
    var projectid = data.projectid;

    user.then((user) => {
        if (user.customClaims.lecturer) {
        } else {
            return {
                error: "No User Claim"
            };
        }
    }).catch((error) => {
        return {
            error:error
        };
    });
} */


exports.loadProjectOverview = function (data, context) {
    var projectid = data.projectid;
    var ref = admin.firestore().doc('projects/' + projectid);

    return ref.get().then((snapshot) => {
        if (!snapshot.exists) {
            var error = errors.documentDoesNotExist;
            return Promise.reject(error);
        }
        var data = snapshot.data()
        var out = {
            name: data.name,
            description: data.description,
        };
        return out;
    }).catch(errors.onError);
}

/**
 * Gets the Custom User Claim of the user specified by uid
 * @param {String} uid user id of the user 
 */
function getUserCustomClaim(uid) {
    var user = admin.auth().getUser(uid);
    return user.then((user) => {
        if (user.customClaims.student) {
            return {
                student: true,
                lecturer: false
            };
        } else if (user.customClaims.lecturer) {
            return {
                student: false,
                lecturer: true
            }
        } else {
            return Promise.reject(errors.noUserClaim);
        }
    }).catch(errors.onError);
}

/**
 * Checks whether a user is a member of the group. Returns snapshot
 * of the project document if so.
 * @param {String} uid 
 * @param {String} projectid 
 */
function getProjectMember(uid, projectid) {
    var ref = admin.firestore().doc('projects/' + projectid + '/members');
    return ref.get().then((snapshot) => {
        if (!snapshot.exists) {
            var error = errors.documentDoesNotExist();
            return Promise.reject(error);
        }
        var data = snapshot.data();
        var member = false;
        data.forEach(element => {
            if (element.id === uid) {
                member = true;
            }
        });
        if (member) {
            return snapshot;
        } else {
            error = errors.userNotMember();
            return Promise.reject(error);
        }
    }).catch(errors.onError);
}

/**
 * Checks if user specified by uid is the product owner of the project.
 * Returns snapshot of project document if so.
 * @param {String} uid 
 * @param {String} projectid 
 */
function getProjectProductOwner(uid, projectid) {
    var ref = admin.firestore().doc('projects/' + projectid);
    return ref.get().then((snapshot) => {
        if (!snapshot.exists) {
            var error = errors.documentDoesNotExist();
            return Promise.reject(error);
        }
        var data = snapshot.data();

        if (data.lecturer.uid === uid) {
            return snapshot;
        } else {
            error = errors.notProductOwner();
            return Promise.reject(error);
        }
    }).catch(errors.onError);
}