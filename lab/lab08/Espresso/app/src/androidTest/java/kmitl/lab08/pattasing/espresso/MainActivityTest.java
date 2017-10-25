package kmitl.lab08.pattasing.espresso;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    public static RecyclerViewMatcher withRecyclerView(final int id) {
        return new RecyclerViewMatcher(id);
    }

    @Test
    public void mainActivityTest() {
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }
    @Test
    public void mainActivityTest2() {
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }
    @Test
    public void mainActivityTest3() {
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(withText("Not Found")).check(matches(isDisplayed()));
    }
    @Test
    public void mainActivityTest4() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        onView(withText("Please Enter user info")).check(matches(isDisplayed()));
    }
    @Test
    public void mainActivityTest5() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Ying"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(withRecyclerView(R.id.list)
                .atPositionOnView(0, R.id.textName))
                .check(matches(withText("Ying")));
        onView(withRecyclerView(R.id.list)
                .atPositionOnView(0, R.id.textAge))
                .check(matches(withText("20")));
    }
    @Test
    public void mainActivityTest6() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Ladarat"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("20"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(withRecyclerView(R.id.list)
                .atPositionOnView(1, R.id.textName))
                .check(matches(withText("Ladarat")));
        onView(withRecyclerView(R.id.list)
                .atPositionOnView(1, R.id.textAge))
                .check(matches(withText("20")));
    }
    @Test
    public void mainActivityTest7() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Somkait"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("80"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(withRecyclerView(R.id.list)
                .atPositionOnView(2, R.id.textName))
                .check(matches(withText("Somkait")));
        onView(withRecyclerView(R.id.list)
                .atPositionOnView(2, R.id.textAge))
                .check(matches(withText("80")));
    }
    @Test
    public void mainActivityTest8() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("60"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(withRecyclerView(R.id.list)
                .atPositionOnView(3, R.id.textName))
                .check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.list)
                .atPositionOnView(3, R.id.textAge))
                .check(matches(withText("60")));
    }
    @Test
    public void mainActivityTest9() {
        onView(withId(R.id.editTExtName)).perform(replaceText("Prayoch"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("50"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(withRecyclerView(R.id.list)
                .atPositionOnView(4, R.id.textName))
                .check(matches(withText("Prayoch")));
        onView(withRecyclerView(R.id.list)
                .atPositionOnView(4, R.id.textAge))
                .check(matches(withText("50")));
    }
    @Test
    public void mainActivityTest91() {
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
        onView(allOf(withId(R.id.buttonClear), withText("CLEAR LIST"))).perform(click());
        onView(withText("Not Found")).check(matches(isDisplayed()));
    }


}
