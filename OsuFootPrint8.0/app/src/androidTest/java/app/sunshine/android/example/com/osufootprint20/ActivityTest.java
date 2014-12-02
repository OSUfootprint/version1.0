package app.sunshine.android.example.com.osufootprint20;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

/**
 * Created by Vincent on 14/12/1.
 */
public class ActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    public ActivityTest() {
        super(Main.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(),getActivity());
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();

    }
    /**
     * Test wrong username and password
     * @throws Exception
     */
    public void testCurrectActivity()throws Exception{
        EditText user = (EditText) solo.getView(R.id.new_username);
        EditText pass = (EditText) solo.getView(R.id.new_password);
        solo.assertCurrentActivity("Not correct Activity!",Main.class);
        solo.enterText(user,"Adam Champion");
        solo.enterText(pass,"skiuyd");
        solo.clickOnButton("Log in");
    }

    /**
     * Test foorprints related after log in
     * @throws Exception
     */
    public void testLogin() throws Exception {
        EditText user = (EditText) solo.getView(R.id.new_username);
        EditText pass = (EditText) solo.getView(R.id.new_password);
        solo.assertCurrentActivity("Not correct Activity!", Main.class);
        solo.enterText(user, "w");
        solo.enterText(pass, "w");
        //test login button
        solo.clickOnButton("Log in");
        solo.assertCurrentActivity("Log in Activity error!", LoginActivity.class);
        //test footprint
        solo.clickOnButton("My footprints");
        solo.assertCurrentActivity("footprint activity error!", FootprintMap.class);
        solo.clickOnButton("+");
        solo.assertCurrentActivity("new footprint activity error!", NewFootprintActivity.class);
//        solo.clickOnButton("Select Place");
//        solo.assertCurrentActivity("SelectPlace activity error!",SelectPlace.class);
//        solo.goBack();

        EditText activity = (EditText) solo.getView(R.id.new_footprint_activity);
        EditText comment = (EditText) solo.getView(R.id.new_footprint_comment);
        solo.enterText(activity, "study");
        solo.enterText(comment, "Android homework is so fucking difficult!");
        solo.goBack();
    }
    public void testwishlist() throws Exception{
        EditText user = (EditText) solo.getView(R.id.new_username);
        EditText pass = (EditText) solo.getView(R.id.new_password);
        solo.assertCurrentActivity("Not correct Activity!", Main.class);
        solo.enterText(user, "w");
        solo.enterText(pass, "w");
        //test login button
        solo.clickOnButton("Log in");
        solo.assertCurrentActivity("Log in Activity error!", LoginActivity.class);
        //test mywishlist
        solo.clickOnButton("My wishlist");
        solo.assertCurrentActivity("wishlist activity error!",WishlistActivity.class);
        solo.goBack();

    }

    /**
     * Test for the "new user" button
     * @throws Exception
     */
    public void testNewUser() throws Exception{

        solo.assertCurrentActivity("Not correct Activity!",Main.class);
        solo.clickOnButton("New User");
        solo.assertCurrentActivity("Not correct Activity!",NewAccounts.class);
    }
}
