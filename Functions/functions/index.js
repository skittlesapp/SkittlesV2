const functions = require('firebase-functions');
const admin = require('firebase-admin');
const login = require('./login');
const signup = require('./signup');
const sprints = require("./sprints");
const projects = require("./projects");
const users = require("./users");

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

admin.initializeApp();

/*==============================Sign Up=============================== */
exports.createUserDocument = functions.https.onCall(signup.createUserDocument);
exports.createUser = functions.https.onCall(signup.createUser);

/*===============================Log In=============================== */
exports.checkEmailVerification = functions.https.onCall(login.checkEmailVerification);

/*=============================Projects=============================== */
exports.createProject = functions.https.onCall(projects.createProject);
exports.joinProject = functions.https.onCall(projects.joinProject);
exports.loadProjectData = functions.https.onCall(projects.loadProjectData);
exports.loadProjectOverview = functions.https.onCall(projects.loadProjectOverview);

/*==============================Sprints=============================== */


/*================================Users=============================== */
exports.getUserProfile = functions.https.onCall(users.getUserProfile);
exports.getUserProfileOverview = functions.https.onCall(users.getUserProfileOverview);