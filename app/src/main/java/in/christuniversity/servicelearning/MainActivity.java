package in.christuniversity.servicelearning;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import static in.christuniversity.servicelearning.SplashActivity.MENU_ITEM_SELECTED;
import static in.christuniversity.servicelearning.SplashActivity.PREFERENCE_FILE;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    SharedPreferences sharedpreferences;

    public static int mBackCount = 0;
    public static final String MODE = "mode";

    @Override
    public void onBackPressed() {
        mBackCount++;

        if (mBackCount == 1) {
            Toast.makeText(this, getString(R.string.back_to_exit), Toast.LENGTH_SHORT).show();
        } else if (mBackCount == 2) {
            finishAffinity();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.navigation);
        mNavigationView.setItemIconTintList(null);
        mFragmentManager = getSupportFragmentManager();
        sharedpreferences = getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        mFragmentTransaction = mFragmentManager.beginTransaction();

                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        switch (menuItem.getItemId()) {

                            case R.id.nav_home:
                                editor.putInt(MENU_ITEM_SELECTED, 0);
                                editor.apply();

                                ChapterFragment homeFragment = new ChapterFragment();
                                Bundle homeBundle = new Bundle();
                                homeBundle.putInt(MODE, 0);
                                homeFragment.setArguments(homeBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, homeFragment);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_1:
                                editor.putInt(MENU_ITEM_SELECTED, 1);
                                editor.apply();

                                ChapterFragment chapterFragmentOne = new ChapterFragment();
                                Bundle chapterOneBundle = new Bundle();
                                chapterOneBundle.putInt(MODE, 1);
                                chapterFragmentOne.setArguments(chapterOneBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentOne);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_2:
                                editor.putInt(MENU_ITEM_SELECTED, 2);
                                editor.apply();

                                ChapterFragment chapterFragmentTwo = new ChapterFragment();
                                Bundle chapterTwoBundle = new Bundle();
                                chapterTwoBundle.putInt(MODE, 2);
                                chapterFragmentTwo.setArguments(chapterTwoBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentTwo);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_3:
                                editor.putInt(MENU_ITEM_SELECTED, 3);
                                editor.apply();

                                ChapterFragment chapterFragmentThree = new ChapterFragment();
                                Bundle chapterThreeBundle = new Bundle();
                                chapterThreeBundle.putInt(MODE, 3);
                                chapterFragmentThree.setArguments(chapterThreeBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentThree);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_4:
                                editor.putInt(MENU_ITEM_SELECTED, 4);
                                editor.apply();

                                ChapterFragment chapterFragmentFour = new ChapterFragment();
                                Bundle chapterFourBundle = new Bundle();
                                chapterFourBundle.putInt(MODE, 4);
                                chapterFragmentFour.setArguments(chapterFourBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentFour);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_5:
                                editor.putInt(MENU_ITEM_SELECTED, 5);
                                editor.apply();

                                ChapterFragment chapterFragmentFive = new ChapterFragment();
                                Bundle chapterFiveBundle = new Bundle();
                                chapterFiveBundle.putInt(MODE, 5);
                                chapterFragmentFive.setArguments(chapterFiveBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentFive);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_6:
                                editor.putInt(MENU_ITEM_SELECTED, 6);
                                editor.apply();

                                ChapterFragment chapterFragmentSix = new ChapterFragment();
                                Bundle chapterSixBundle = new Bundle();
                                chapterSixBundle.putInt(MODE, 6);
                                chapterFragmentSix.setArguments(chapterSixBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentSix);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_7:
                                editor.putInt(MENU_ITEM_SELECTED, 7);
                                editor.apply();

                                ChapterFragment chapterFragmentSeven = new ChapterFragment();
                                Bundle chapterSevenBundle = new Bundle();
                                chapterSevenBundle.putInt(MODE, 7);
                                chapterFragmentSeven.setArguments(chapterSevenBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentSeven);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_8:
                                editor.putInt(MENU_ITEM_SELECTED, 8);
                                editor.apply();

                                ChapterFragment chapterFragmentEight = new ChapterFragment();
                                Bundle chapterEightBundle = new Bundle();
                                chapterEightBundle.putInt(MODE, 8);
                                chapterFragmentEight.setArguments(chapterEightBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentEight);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_9:
                                editor.putInt(MENU_ITEM_SELECTED, 9);
                                editor.apply();

                                ChapterFragment chapterFragmentNine = new ChapterFragment();
                                Bundle chapterNineBundle = new Bundle();
                                chapterNineBundle.putInt(MODE, 9);
                                chapterFragmentNine.setArguments(chapterNineBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentNine);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_10:
                                editor.putInt(MENU_ITEM_SELECTED, 10);
                                editor.apply();

                                ChapterFragment chapterFragmentTen = new ChapterFragment();
                                Bundle chapterTenBundle = new Bundle();
                                chapterTenBundle.putInt(MODE, 10);
                                chapterFragmentTen.setArguments(chapterTenBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentTen);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_11:
                                editor.putInt(MENU_ITEM_SELECTED, 11);
                                editor.apply();

                                ChapterFragment chapterFragmentEleven = new ChapterFragment();
                                Bundle chapterElevenBundle = new Bundle();
                                chapterElevenBundle.putInt(MODE, 11);
                                chapterFragmentEleven.setArguments(chapterElevenBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentEleven);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_12:
                                editor.putInt(MENU_ITEM_SELECTED, 12);
                                editor.apply();

                                ChapterFragment chapterFragmentTwelve = new ChapterFragment();
                                Bundle chapterTwelveBundle = new Bundle();
                                chapterTwelveBundle.putInt(MODE, 12);
                                chapterFragmentTwelve.setArguments(chapterTwelveBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentTwelve);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_13:
                                editor.putInt(MENU_ITEM_SELECTED, 13);
                                editor.apply();

                                ChapterFragment chapterFragmentThirteen = new ChapterFragment();
                                Bundle chapterThirteenBundle = new Bundle();
                                chapterThirteenBundle.putInt(MODE, 13);
                                chapterFragmentThirteen.setArguments(chapterThirteenBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentThirteen);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_14:
                                editor.putInt(MENU_ITEM_SELECTED, 14);
                                editor.apply();

                                ChapterFragment chapterFragmentFourteen = new ChapterFragment();
                                Bundle chapterFourteenBundle = new Bundle();
                                chapterFourteenBundle.putInt(MODE, 14);
                                chapterFragmentFourteen.setArguments(chapterFourteenBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentFourteen);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_15:
                                editor.putInt(MENU_ITEM_SELECTED, 15);
                                editor.apply();

                                ChapterFragment chapterFragmentFifteen = new ChapterFragment();
                                Bundle chapterFifteenBundle = new Bundle();
                                chapterFifteenBundle.putInt(MODE, 15);
                                chapterFragmentFifteen.setArguments(chapterFifteenBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentFifteen);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_chapter_16:
                                editor.putInt(MENU_ITEM_SELECTED, 16);
                                editor.apply();

                                ChapterFragment chapterFragmentSixteen = new ChapterFragment();
                                Bundle chapterSixteenBundle = new Bundle();
                                chapterSixteenBundle.putInt(MODE, 16);
                                chapterFragmentSixteen.setArguments(chapterSixteenBundle);
                                mFragmentTransaction.replace(R.id.fragment_container, chapterFragmentSixteen);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_settings:
                                editor.putInt(MENU_ITEM_SELECTED, 17);
                                editor.apply();

                                SettingsFragment settingsFragment = new SettingsFragment();
                                mFragmentTransaction.replace(R.id.fragment_container, settingsFragment);
                                mFragmentTransaction.commit();
                                break;

                            case R.id.nav_about:
                                editor.putInt(MENU_ITEM_SELECTED, 18);
                                editor.apply();

                                AboutFragment aboutFragment = new AboutFragment();
                                mFragmentTransaction.replace(R.id.fragment_container, aboutFragment);
                                mFragmentTransaction.commit();
                                break;
                        }
                        return true;
                    }
                });

        if (savedInstanceState == null) {
            int menuItemSelected = sharedpreferences.getInt(MENU_ITEM_SELECTED, 0);
            restoreNavigationItemSelected(menuItemSelected);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setToolbar(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        ActionBar mActionBar = getSupportActionBar();

        if (mActionBar != null) {
            mActionBar.setTitle(title);
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    private void restoreNavigationItemSelected(int itemNumber) {

        switch (itemNumber) {
            case 1:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_1, 0);
                break;
            case 2:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_2, 0);
                break;
            case 3:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_3, 0);
                break;
            case 4:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_4, 0);
                break;
            case 5:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_5, 0);
                break;
            case 6:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_6, 0);
                break;
            case 7:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_7, 0);
                break;
            case 8:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_8, 0);
                break;
            case 9:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_9, 0);
                break;
            case 10:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_10, 0);
                break;
            case 11:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_11, 0);
                break;
            case 12:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_12, 0);
                break;
            case 13:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_13, 0);
                break;
            case 14:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_14, 0);
                break;
            case 15:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_15, 0);
                break;
            case 16:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_chapter_16, 0);
                break;
            case 17:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_settings, 0);
                break;
            case 18:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_about, 0);
                break;
            default:
                mNavigationView.getMenu().performIdentifierAction(R.id.nav_home, 0);
        }
    }
}
