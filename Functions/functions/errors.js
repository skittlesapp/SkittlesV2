/**
 * @author Simphiwe Zulu
 * @description This file contains all functions related to errors
 */

 /**
 * Returns a rejected promise with error as the reason
 * @param {JSON} error error object which includes the error code, message and details 
 */
exports.onError = (error) => {
    return Promise.reject(error);   
}

/**
 * Returns the Custom Claims Exist error object
 * @returns {JSON} error object that includes the error code, message and details
 */
exports.customClaimExists = () => {
    var error = {
        code: 'failed-precondition',
        message: 'User already has a custom claim',
        details: ''
    }
    return error;
}

exports.noUserClaim = () => {
    var error = {
        code: 'failed-precondition',
        message: 'User custom claim not specified',
        details: ''
    };
    return error;
}

/**
 * Returns the No Claim Specified error object
 * @returns {JSON} error object that includes the error code, message and details
 */
exports.noClaimSpecified = () => {
    var error = {
        code: 'failed-precondition',
        message: 'User\'s account type must be specified',
        details: ''
    }
    return error;
}

exports.invalidClaim = () => {
    var error = {
        code: 'unauthenticated',
        message: 'User does not have valid authentication credentials',
        details: ''
    };
    return error
}

exports.userNotMember = () => {
    var error = {
        code: 'unauthenticated',
        message: 'User is not a member of this group',
        details: ''
    };
    return error;
}

exports.notProductOwner = () => {
    var error = {
        code: 'unauthenticated',
        message: 'User does not have access to this project',
        details: ''
    }
    return error;
}

exports.documentDoesNotExist = () => {
    var error = {
        code: 'not-found',
        message: 'Project not found',
        details: ''
    };
    return error;
}