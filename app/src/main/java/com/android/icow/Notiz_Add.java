package com.android.icow;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Notiz_Add extends AppCompatActivity {

    ActionBar actionBar;
    EditText mTitleEditText;
    EditText mContentEditText;
    private NotizCard notizCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notiz__add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.title_activity_add);

        this.notizCard = new NotizCard();

        mTitleEditText = (EditText) findViewById(R.id.detail_title);
        mContentEditText = (EditText) findViewById(R.id.detail_content);

        this.mTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

            }

            @Override
            public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

            }

            @Override
            public void afterTextChanged(final Editable editable) {
                notizCard.setTitle(editable.toString().length() == 0 ? null : editable.toString());
            }
        });

        this.mContentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

            }

            @Override
            public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

            }

            @Override
            public void afterTextChanged(final Editable editable) {
                notizCard.setContent(editable.toString().length() == 0 ? null : editable.toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_item_save:
                if (notizCard.getTitle() == null) {
                    Toast.makeText(Notiz_Add.this, R.string.toast_no_title, Toast.LENGTH_SHORT).show();
                }
                else if (notizCard.getContent() == null) {
                    Toast.makeText(Notiz_Add.this, R.string.toast_no_content, Toast.LENGTH_SHORT).show();
                }
                else{
                    DatabaseHelper.getInstance(Notiz_Add.this).createEntry(notizCard);
                    finish();
                }return true;


            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
