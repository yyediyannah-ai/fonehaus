package com.fonehaus.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CategoryActivity extends AppCompatActivity {

    private EditText edtCategorySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);

        edtCategorySearch = findViewById(R.id.edtCategorySearch);
        Button btnCategorySearch = findViewById(R.id.btnCategorySearch);

        Runnable triggerCategorySearch = () -> {
            if (edtCategorySearch != null) {
                String query = edtCategorySearch.getText().toString().trim();
                Intent intent = new Intent(CategoryActivity.this, ProductListActivity.class);
                intent.putExtra("searchQuery", query);
                startActivity(intent);
            }
        };

        if (btnCategorySearch != null) {
            btnCategorySearch.setOnClickListener(v -> triggerCategorySearch.run());
        }

        if (edtCategorySearch != null) {
            edtCategorySearch.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    triggerCategorySearch.run();
                    return true;
                }
                return false;
            });
        }

        bindCategoryButton(R.id.btnPhones, "Phones");
        bindCategoryButton("btnTablets", "Tablets");
        bindCategoryButton("btnLaptops", "Laptops");
        bindCategoryButton("btnAudio", "Audio");
        bindCategoryButton("btnTVs", "Smart TVs");
        bindCategoryButton("btnWatches", "Smart Watches");
        bindCategoryButton("btnAllProducts", "All");

        Button btnCart = findViewById(R.id.btnCategoryCart);
        if (btnCart != null) {
            ViewCompat.setOnApplyWindowInsetsListener(btnCart, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                if (lp != null) {
                    int baseMargin = getResources().getDimensionPixelSize(R.dimen.button_margin_bottom);
                    lp.bottomMargin = systemBars.bottom + baseMargin;
                    v.setLayoutParams(lp);
                }
                return insets;
            });

            btnCart.setOnClickListener(v -> {
                Intent intent = new Intent(CategoryActivity.this, CartActivity.class);
                startActivity(intent);
            });
        }
    }

    private void bindCategoryButton(int resId, String category) {
        Button btn = findViewById(resId);
        if (btn != null) {
            btn.setOnClickListener(v -> openCategory(category));
        }
    }

    private void bindCategoryButton(String idName, String category) {
        int resId = getResources().getIdentifier(idName, "id", getPackageName());
        if (resId != 0) {
            bindCategoryButton(resId, category);
        }
    }

    private void openCategory(String category) {
        Intent intent = new Intent(CategoryActivity.this, ProductListActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
