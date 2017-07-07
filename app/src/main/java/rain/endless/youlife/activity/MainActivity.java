package rain.endless.youlife.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import rain.endless.youlife.R;
import rain.endless.youlife.YouLifeApplication;
import rain.endless.youlife.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private final String TAG = YouLifeApplication.getTag(this);

    @BindView(R.id.tvEmail)
    TextView mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras().getString("email") != null) {
            mEmail.setText(getIntent().getExtras().getString("email"));
        }
        Log.e(TAG, "onCreate");
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_main;
    }
}
