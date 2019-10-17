package com.example.skittles;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import java.util.HashMap;
import java.util.Map;

class CloudFunctions{

    private FirebaseFunctions mFunctions;

    public CloudFunctions(){
        mFunctions = FirebaseFunctions.getInstance();
    }

    public Task<String> createUserDocument(String acc_type, String fname, String lname, String university, String university_id) {
        // Create the arguments to the callable function.
        Map<String, Object> data = new HashMap<>();
        data.put("acc_type", acc_type);
        data.put("fname", fname);
        data.put("lname", lname);
        data.put("university", university);
        data.put("university_id", university_id);
    
        return mFunctions
                .getHttpsCallable("createUserDocument")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        // This continuation runs on either success or failure, but if the task
                        // has failed then getResult() will throw an Exception which will be
                        // propagated down.
                        String result = (String) task.getResult().getData();
                        return result;
                    }
                });
    }

    public Task<String> checkEmailVerification() {
        // Create the arguments to the callable function.
        Map<String, Object> data = new HashMap<>();
        return mFunctions
                .getHttpsCallable("checkEmailVerification")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        String result = (String) task.getResult().getData();
                        return result;
                    }
                });
    }
}