package ryanman.example.fragment;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import ryanman.example.R;

/*
http://examples.javacodegeeks.com/android/core/app/fragment/android-fragments-example/
http://www.tutorialspoint.com/android/android_fragments.htm
http://www.javatpoint.com/android-fragments

Fragment represents a portion of a user interface or an operation that runs within an Activity.
The advantage is due to the convenience of reusing the components in different layouts.
A single activity can contain multiple fragments and many fragments can be reused in many, different activities.
Although each fragment has each own lifecycle, it is connected with the Activity it belongs to, so it's lifecycle is directly influenced by the activity's lifecycle.
You can add or remove fragments in an activity while the activity is running.

Others
- sample list fragment http://www.tutorialspoint.com/android/android_list_fragment.htm
- sample transaction fragment on KitKat http://www.tutorialspoint.com/android/android_fragment_transitions.htm
- sample Multi-pane handset http://www.vogella.com/tutorials/AndroidFragments/article.html
 */
public class FragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);
    }

    public void selectFrag(View view) {
        Fragment fragment;
        if (view == findViewById(R.id.col22Btn)) {
            fragment = new FragmentTwo();
        } else {
            fragment = new FragmentOne();
        }

        FragmentManager fragmentManager = getFragmentManager(); //class is responsible to make interaction between fragment objects
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main, fragment);  // replace fragment


        /**
         * Sample logic to check the device orientation and act accordingly
         */
//        Configuration config = getResources().getConfiguration();
//        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            /**
//             * Landscape mode of the device
//             */
//            LM_Fragment ls_fragment = new LM_Fragment();
//            fragmentTransaction.replace(android.R.id.content, ls_fragment);
//        }else{
//            /**
//             * Portrait mode of the device
//             */
//            PM_Fragment pm_fragment = new PM_Fragment();
//            fragmentTransaction.replace(android.R.id.content, pm_fragment);
//        }

        fragmentTransaction.commit();
    }
}