package navigation;

import android.view.View;

/**
 * Created by Will on 22/02/2016.
 */
public class NavigationEntry {

    private String mNavigationId;
    private String mNavigationTitle;
    private View.OnClickListener mNavigationClickListener;

    public NavigationEntry(String mNavigationTitle, View.OnClickListener mNavigationClickListener) {
        this.mNavigationTitle = mNavigationTitle;
        this.mNavigationClickListener = mNavigationClickListener;
    }

    public String getNavigationTitle() {
        return mNavigationTitle;
    }

    public View.OnClickListener getNavigationClickListener() {
        return mNavigationClickListener;
    }
}
