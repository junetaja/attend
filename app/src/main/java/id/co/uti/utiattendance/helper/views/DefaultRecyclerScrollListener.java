package id.co.uti.utiattendance.helper.views;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

/**
 * Created by nandajulianda on 12/23/16.
 */

public class DefaultRecyclerScrollListener extends RecyclerView.OnScrollListener {

    private final SwipeRefreshLayout swipeLayout;

    public DefaultRecyclerScrollListener(SwipeRefreshLayout swipeLayout) {
        this.swipeLayout = swipeLayout;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        int topRowVerticalPosition;
        if(recyclerView == null || recyclerView.getChildCount() == 0) {
            topRowVerticalPosition = 0;
        } else {
            topRowVerticalPosition = recyclerView.getChildAt(0).getTop();
        }
        swipeLayout.setEnabled(topRowVerticalPosition >= 0);
    }

}
