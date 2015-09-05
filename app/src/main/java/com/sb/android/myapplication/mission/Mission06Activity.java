package com.sb.android.myapplication.mission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sb.android.myapplication.R;

public class Mission06Activity extends AppCompatActivity {

    private ImageView mHandleUpImageView;
    private ImageView mHandleDownImageView;
    private LinearLayout mUrlLayout;
    private EditText mUrlEditText;
    private WebView webview;

    private boolean isUrlEditTextOpen= false;

    private Animation goUpsideAnim, comeDownsideAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission06);

        mHandleUpImageView = (ImageView) findViewById(R.id.handleup_image_view);
        mHandleDownImageView = (ImageView) findViewById(R.id.handledown_image_view);

        mHandleUpImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mHandleUpImageView.startAnimation(goUpsideAnim);
            }
        });
        mHandleDownImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mHandleDownImageView.startAnimation(comeDownsideAnim);
            }
        });

        // Member filed for edittext and move button
        mUrlLayout = (LinearLayout) findViewById(R.id.urllayout);

        // For animation
        goUpsideAnim = AnimationUtils.loadAnimation(this, R.anim.goup);
        comeDownsideAnim = AnimationUtils.loadAnimation(this, R.anim.comdown);

        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        goUpsideAnim.setAnimationListener(animListener);
        comeDownsideAnim.setAnimationListener(animListener);

        // EditText, Url move button and listener
        mUrlEditText = (EditText) findViewById(R.id.url_edit_text);
        Button mMoveButton = (Button) findViewById(R.id.move_button);
        mMoveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String urlStr = mUrlEditText.getText().toString().trim();
                if (urlStr.length() < 1) {
                    Toast.makeText(getApplicationContext(), "needs your url address.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!urlStr.startsWith("http://")) {
                    urlStr = "http://" + urlStr;
                    mUrlEditText.setText(urlStr);
                }

                webview.loadUrl(urlStr);
            }
        });

        // Webview settings
        webview = (WebView)findViewById(R.id.webview);

        WebSettings ws= webview.getSettings();
        ws.setSaveFormData(false);
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(true);

        webview.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                // start animation
                mUrlLayout.startAnimation(goUpsideAnim);
            }
        });

        // Initialize as ready to get url and move
        isUrlEditTextOpen = true;
    }

    private void showHandleImageView() {
        mHandleUpImageView.setVisibility(View.INVISIBLE);
        mHandleDownImageView.setVisibility(View.VISIBLE);
        mUrlLayout.setVisibility(View.GONE);

        isUrlEditTextOpen = false;
    }

    private void showUrlEditText() {
        mHandleUpImageView.setVisibility(View.VISIBLE);
        mHandleDownImageView.setVisibility(View.INVISIBLE);
        mUrlLayout.setVisibility(View.VISIBLE);

        isUrlEditTextOpen = true;
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
            if (isUrlEditTextOpen) {
                showHandleImageView();
            } else {
                showUrlEditText();
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mission06, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
