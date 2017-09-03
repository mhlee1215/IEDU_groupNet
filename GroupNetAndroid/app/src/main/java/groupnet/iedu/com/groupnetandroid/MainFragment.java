package groupnet.iedu.com.groupnetandroid;

import android.view.View;

/**
 * Created by mhlee on 8/9/17.
 */

public interface MainFragment {
    public void refresh();
    public void willBeDisplayed();
    public void willBeHidden();
    public void postExecute(View view, Object object);
}
