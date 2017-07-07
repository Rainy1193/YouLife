package rain.endless.youlife.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.OnClick;
import rain.endless.youlife.R;
import rain.endless.youlife.YouLifeApplication;
import rain.endless.youlife.base.BaseActivity;
import rain.endless.youlife.utils.FontManager;


public class LoginActivity extends BaseActivity {
    private final String TAG = YouLifeApplication.getTag(this);
    private Animation mFadeIn;
    private Animation mFadeOut;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @BindView(R.id.tvLogo)
    TextView mLogo;

    @BindView(R.id.tvBack)
    TextView mBack;

    @BindView(R.id.etEmailLogin)
    EditText mEmailLogin;

    @BindView(R.id.etPasswordLogin)
    EditText mPasswordLogin;

    @BindView(R.id.etEmailSignUp)
    EditText mEmailSignUp;

    @BindView(R.id.etPasswordSignUp)
    EditText mPasswordSignUp;

    @BindView(R.id.etRePasswordSignUp)
    EditText mRePasswordSignUp;

    @BindView(R.id.layoutLogin)
    LinearLayout mLayoutLogin;

    @BindView(R.id.layoutSingUp)
    LinearLayout mLayoutSingUp;

    @OnClick(R.id.btLogin)
    void logIn() {
        mAuth.signInWithEmailAndPassword(mEmailLogin.getText().toString(), mPasswordLogin.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mUser = task.getResult().getUser();
                            if (mUser.isEmailVerified()) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("email", mUser.getEmail());
                                startActivity(intent);
                            } else {
                                mUser.sendEmailVerification().addOnCompleteListener(LoginActivity.this, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.e(TAG, task.toString());
                                        } else {
                                            Log.e(TAG, "Error");
                                        }
                                    }
                                });
                            }

                        } else {
                            Log.e(TAG, task.getException().getMessage());
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure" + e.getClass());
                    }
                })
                .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.e(TAG, "onSuccess");
                    }
                });
    }


    @OnClick(R.id.btSignUp)
    void signUp() {
        mAuth.createUserWithEmailAndPassword(mEmailSignUp.getText().toString(), mPasswordSignUp.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e(TAG, "onComplete");
                        if (task.isSuccessful()) {
                            mUser = task.getResult().getUser();
                            Log.e(TAG, mUser.toString());
                        } else {
                            Log.e(TAG, task.getException().getMessage());
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure");
                    }
                })
                .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.e(TAG, "onSuccess");
                    }
                });
    }

    @OnClick(R.id.tvSignUp)
    void goSignUp() {
        mFadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mFadeIn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        mLayoutLogin.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mBack.setVisibility(View.VISIBLE);
                        mLayoutSingUp.setVisibility(View.VISIBLE);
                        mLayoutLogin.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mLayoutSingUp.startAnimation(mFadeIn);
                mBack.startAnimation(mFadeIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mLayoutLogin.startAnimation(mFadeOut);

    }

    @OnClick(R.id.tvBack)
    void backLogin() {
        mFadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLayoutSingUp.setVisibility(View.INVISIBLE);
                mBack.setVisibility(View.GONE);
                mFadeIn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mLayoutLogin.setVisibility(View.VISIBLE);
                        mLayoutSingUp.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mLayoutLogin.startAnimation(mFadeIn);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mBack.startAnimation(mFadeOut);
        mLayoutSingUp.startAnimation(mFadeOut);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initAnimation();
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_login;

    }

    public void initUI() {
        mLogo.setTypeface(FontManager.getTypefaceVivaldi(this));
    }

    public void initAnimation() {
        mFadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        mFadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        if (mLayoutSingUp.getVisibility() == View.VISIBLE) {
            backLogin();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUser = mAuth.getCurrentUser();
    }
}
