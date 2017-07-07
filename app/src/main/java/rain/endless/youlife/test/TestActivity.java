package rain.endless.youlife.test;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import rain.endless.youlife.R;

/**
 * Created by RAINY on 7/5/2017.
 */

public class TestActivity extends AppCompatActivity {
    @BindView(R.id.btFacebook)
    LoginButton mLoginFacebook;
    private CallbackManager mCallbackManager;

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.e("onSuccess", "onSuccess");

            GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            Log.e("onCompleted", "onCompleted");
                            try {
                                if (object.getString("id") != null) {
                                    String id = object.getString("id");

                                    //("https://graph.facebook.com/" + id + "/picture?type=large");
                                    Log.e("id", id);
                                }
                                if (object.getString("first_name") != null) {
                                    String firstName = object.getString("first_name");
                                    Log.e("email", firstName);

                                }
                                if (object.getString("last_name") != null) {
                                    String lastName = object.getString("last_name");

                                    Log.e("email", lastName);
                                }
                                if (object.has("email")) {
                                    if (object.getString("email") != null) {
                                        String email = object.getString("email");

                                        Log.e("email", email);
                                    }
                                }

                            } catch (JSONException e) {
                                LoginManager.getInstance().logOut();
                                Log.e("E", e.toString());
                            }
                        }
                    }
            );
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,first_name,last_name,email");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        mCallbackManager = CallbackManager.Factory.create();
        mLoginFacebook.setReadPermissions(Arrays.asList("public_profile, email"));
        mLoginFacebook.registerCallback(mCallbackManager, mCallback);
        printKeyHash();
    }

    private void printKeyHash() {
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo("rain.endless.youlife", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("KeyHash:", e.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("KeyHash:", e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
