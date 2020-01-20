package com.example.javaalgorithmsrecipes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.ButterKnife;

import static com.example.javaalgorithmsrecipes.Constants.SharedPreferences.SAVINGS_FINAL;

public class MainActivity extends AppCompatActivity implements RecycleViewCode.ItemClickListener, RecycleViewCode.ContactsAdapterListener {

    //tab twice to exit method
    boolean doubleBackToExitPressedOnce = false;
    public static final int DELAY_MILLIS = 2000;

    ArrayList<CodeObject> codeObjects = new ArrayList<>();
    RecycleViewCode adapter;
    RecyclerView recyclerView;
    String description = "";
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        ActionBar actionBar = getSupportActionBar();
//        //no need for this
////            Objects.requireNonNull(actionBar).setTitle(getResources().getString(R.string.dashboard));
//        //set color of background
//        Objects.requireNonNull(actionBar).
//                setBackgroundDrawable(
//                        new ColorDrawable(
//                                ContextCompat.getColor(
//                                        getApplicationContext(), R.color.white)));
//        TextView textView = new TextView(getApplicationContext());
//        textView.setText(getResources().getString(R.string.app_name));
////        textView.setTypeface(ResourcesCompat.getFont(getApplicationContext(), R.font.robotoblack));
////        textView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
//        textView.setTextSize(18f);
//        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        Objects.requireNonNull(actionBar).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setCustomView(textView);
////            actionBar.setElevation(0.2f);
//        actionBar.setElevation(0f);
//            actionBar.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.actionbar_round));
        searchView = (SearchView) findViewById(R.id.action_search);
        //set up recycle
        recyclerView = findViewById(R.id.rvCode);
        adapter = new RecycleViewCode(this, codeObjects, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        recyclerView.setLayoutAnimation(animation);
        initCodeData();


        String examplesbefore = "// #option 1\n" +
                "public class AwesomeButtonActivity extends AppCompatActivity {\n" +
                "    private Button awesomeButton;\n" +
                "    @Override\n" +
                "    protected void onCreate(@Nullable Bundle savedInstanceState) {\n" +
                "        super.onCreate(savedInstanceState);\n" +
                "        awesomeButton = new Button(this);\n" +
                "\n" +
                "        awesomeButton.setOnClickListener(new View.OnClickListener() {\n" +
                "            @Override\n" +
                "            public void onClick(View v) {\n" +
                "                awesomeButtonClicked();\n" +
                "            }\n" +
                "        });\n" +
                "    }\n" +
                "    private void awesomeButtonClicked() {\n" +
                "        awesomeButton.setText(\"AWESOME!\");\n" +
                "    }\n" +
                "}\n" +
                "// #option 2\n" +
                "public class AwesomeButtonActivity extends AppCompatActivity {\n" +
                "    private Button awesomeButton; \n" +
                "    private View.OnClickListener awesomeOnClickListener = new View.OnClickListener() {\n" +
                "        @Override\n" +
                "        public void onClick(View v) {\n" +
                "            awesomeButtonClicked();\n" +
                "        }\n" +
                "    };\n" +
                "    @Override\n" +
                "    protected void onCreate(@Nullable Bundle savedInstanceState) {\n" +
                "        super.onCreate(savedInstanceState);\n" +
                "        awesomeButton = new Button(this);\n" +
                "        awesomeButton.setOnClickListener(awesomeOnClickListener);\n" +
                "    }\n" +
                "    private void awesomeButtonClicked() {\n" +
                "        awesomeButton.setText(\"AWESOME!\");\n" +
                "    }\n" +
                "}\n" +
                "// #option 3\n" +
                "public class AwesomeButtonActivity extends AppCompatActivity {\n" +
                "    private Button awesomeButton;\n" +
                "    @Override\n" +
                "    protected void onCreate(@Nullable Bundle savedInstanceState) {\n" +
                "        super.onCreate(savedInstanceState);\n" +
                "        awesomeButton = new Button(this);\n" +
                "\n" +
                "        awesomeButton.setOnClickListener(new AwesomeButtonClick());\n" +
                "    }\n" +
                "    private void awesomeButtonClicked() {\n" +
                "        awesomeButton.setText(\"AWESOME!\");\n" +
                "    }\n" +
                "    class AwesomeButtonClick implements View.OnClickListener {\n" +
                "        @Override\n" +
                "        public void onClick(View v) {\n" +
                "            awesomeButtonClicked();\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "// #option 4\n" +
                "public class AwesomeButtonActivity extends AppCompatActivity implements View.OnClickListener {\n" +
                "    private Button awesomeButton;\n" +
                "    @Override\n" +
                "    protected void onCreate(@Nullable Bundle savedInstanceState) {\n" +
                "        super.onCreate(savedInstanceState);\n" +
                "        awesomeButton = new Button(this);\n" +
                "\n" +
                "        awesomeButton.setOnClickListener(this);\n" +
                "    }\n" +
                "    @Override\n" +
                "    public void onClick(View v) {\n" +
                "        awesomeButtonClicked();\n" +
                "    }\n" +
                "    private void awesomeButtonClicked() {\n" +
                "        awesomeButton.setText(\"AWESOME!\");\n" +
                "    }\n" +
                "}";

    }

    @Override
    protected void onResume() {
        super.onResume();
        //
    }

    @Override
    public void onBackPressed() {
        //tap once to exit method
//        super.onBackPressed();
//        if (counter == ZERO) {
//            counterText.setText(getResources.getString(R.string.quotation_mark));
//        } else {
//            counterText.setText(String.valueOf(counter));
//        }
//        finish();

        //tap twice to exit method
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getResources().getString(R.string.press_twice), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, DELAY_MILLIS);

        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }

    }

    // data to populate the RecyclerView with
    private void initCodeData(){
        CodeObject codeObject0 = new CodeObject("Biggest common divisor", "Geek", R.drawable.ic_geek);
        codeObjects.add(codeObject0);
        CodeObject codeObject1 = new CodeObject("Caesar cypher", "Encryption", R.drawable.ic_encrypt);
        codeObjects.add(codeObject1);
        CodeObject codeObject2 = new CodeObject("Ascii binary to char", "Geek", R.drawable.ic_geek);
        codeObjects.add(codeObject2);
        CodeObject codeObject3 = new CodeObject("Bubble sort", "Algorithms", R.drawable.ic_algorithm);
        codeObjects.add(codeObject3);
        CodeObject codeObject4 = new CodeObject("Char to byte & Byte to char", "Geek", R.drawable.ic_geek);
        codeObjects.add(codeObject4);
        CodeObject codeObject5 = new CodeObject("Class class type", "Java language", R.drawable.ic_java);
        codeObjects.add(codeObject5);
        CodeObject codeObject6 = new CodeObject("Char counter", "Geek", R.drawable.ic_geek);
        codeObjects.add(codeObject6);
        CodeObject codeObject7 = new CodeObject("Capitalize first letter", "Geek", R.drawable.ic_geek);
        codeObjects.add(codeObject7);
        CodeObject codeObject8 = new CodeObject("Even digits", "Geek", R.drawable.ic_geek);
        codeObjects.add(codeObject8);
        CodeObject codeObject9 = new CodeObject("Even odd numbers", "Geek", R.drawable.ic_geek);
        codeObjects.add(codeObject9);
        CodeObject codeObject10 = new CodeObject("Prime Numbers", "Geek", R.drawable.ic_geek);
        codeObjects.add(codeObject10);
        CodeObject codeObject11 = new CodeObject("Show declared methods", "Java language", R.drawable.ic_java);
        codeObjects.add(codeObject11);
        CodeObject codeObject12 = new CodeObject("Get month name", "Java language", R.drawable.ic_java);
        codeObjects.add(codeObject12);
        CodeObject codeObject13 = new CodeObject("Guess the movie", "Geek", R.drawable.ic_geek);
        codeObjects.add(codeObject13);
        CodeObject codeObject14 = new CodeObject("Guess the number", "Geek", R.drawable.ic_geek);
        codeObjects.add(codeObject14);
        CodeObject codeObject15 = new CodeObject("The highest number from a list", "Java language", R.drawable.ic_java);
        codeObjects.add(codeObject15);
        CodeObject codeObject16 = new CodeObject("Java without main", "Java language", R.drawable.ic_java);
        codeObjects.add(codeObject16);
        CodeObject codeObject17 = new CodeObject("Letter counter", "Geek", R.drawable.ic_geek);
        codeObjects.add(codeObject17);
        CodeObject codeObject18 = new CodeObject("Linear search", "Algorithms", R.drawable.ic_algorithm);
        codeObjects.add(codeObject18);
        CodeObject codeObject19 = new CodeObject("Binary search", "Algorithms", R.drawable.ic_algorithm);
        codeObjects.add(codeObject19);
        CodeObject codeObject20 = new CodeObject("Longest string", "Algorithms", R.drawable.ic_algorithm);
        codeObjects.add(codeObject20);
        CodeObject codeObject21 = new CodeObject("Normalize text", "Java language", R.drawable.ic_java);
        codeObjects.add(codeObject21);
        CodeObject codeObject22 = new CodeObject("OnClickListener examples", "Android library", R.drawable.ic_robot);
        codeObjects.add(codeObject22);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
//        adapter.getItem(position) +
        Toast.makeText(this, "Processing your command of item number " + position, Toast.LENGTH_SHORT).show();

        // auto language recognition
        switch (position) {
            //case is equal to index
            case 0:
                description = getString(R.string.index0);
                break;
            case 1:
                description = getString(R.string.index1);
                break;
            case 2:
                description = getString(R.string.index2);
                break;
            case 3:
                description = getString(R.string.index3);
                break;
            case 4:
                description = getString(R.string.index4);
                break;
            case 5:
                description = getString(R.string.index5);
                break;
            case 6:
                description = getString(R.string.index6);
                break;
            case 7:
                description = getString(R.string.index7);
                break;
            case 8:
                description = getString(R.string.index8);
                break;
            case 9:
                description = getString(R.string.index9);
                break;
            case 10:
                description = getString(R.string.index10);
                break;
            case 11:
                description = getString(R.string.index11);
                break;
            case 12:
                description = getString(R.string.index12);
                break;
            case 13:
                description = getString(R.string.index13);
                break;
            case 14:
                description = getString(R.string.index14);
                break;
            case 15:
                description = getString(R.string.index15);
                break;
            case 16:
                description = getString(R.string.index16);
                break;
            case 17:
                description = getString(R.string.index17);
                break;
            case 18:
                description = getString(R.string.index18);
                break;
            case 19:
                description = getString(R.string.index19);
                break;
            case 20:
                description = getString(R.string.index20);
                break;
            case 21:
                description = getString(R.string.index21);
                break;
            case 22:
                description = getString(R.string.index22);
                break;
            default:
                //default position should not exist
                description = "default";
                break;
        }
        Intent intent = new Intent(MainActivity.this, CodeViewActivity.class);
        intent.putExtra(SAVINGS_FINAL, description);
        startActivity(intent);
    }

    //[MENU]
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
//                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (BuildConfig.DEBUG) {
                    Log.i("TAOJAVA", "this works");
                }
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //onOptionsItemSelected is called everytime user clicks
        //options menu, so here it is ok to have a final int assigned as id
        final int ID_LOCAL = item.getItemId();
        View view = findViewById(ID_LOCAL);
//        view.setOnLongClickListener(new View.OnLongClickListener() {
//            public boolean onLongClick(View v) {
//                Toast.makeText(v.getContext(), getResources().getString(R.string.settings_area), Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });

        if (ID_LOCAL == R.id.options) {
            final Intent INTENT = new Intent(MainActivity.this, OptionsActivity.class);
            startActivity(INTENT);
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (ID_LOCAL == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }//END OF -> [MENU]

    //hide the rest of the menu
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.check_in_hour).setVisible(false);
//        menu.findItem(R.id.action_settings).setVisible(false);
//        menu.findItem(R.id.action_help).setVisible(false);
        menu.findItem(R.id.options).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        menu.findItem(R.id.action_search).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        return true;
    }

    @Override
    public void onContactSelected(CodeObject codeObject) {
        Toast.makeText(getApplicationContext(), "Selected: " + codeObject.getTitle(), Toast.LENGTH_LONG).show();
    }
}
