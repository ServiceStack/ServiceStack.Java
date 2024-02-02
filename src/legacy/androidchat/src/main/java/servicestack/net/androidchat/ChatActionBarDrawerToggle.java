package servicestack.net.androidchat;

import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

/**
 * Created by mythz on 2/15/2017.
 */
public class ChatActionBarDrawerToggle extends ActionBarDrawerToggle {
    private AppCompatActivity hostActivity;
    private DrawerLayout drawerLayout;
    private int openedResource;
    private int closedResource;

    private boolean rightIsClosed = true;
    private boolean leftIsClosed = true;

    public ChatActionBarDrawerToggle(
            AppCompatActivity host,
            DrawerLayout drawerLayout,
            Toolbar toolbar,
            @StringRes int openedResource,
            @StringRes int closedResource) {
        super(host, drawerLayout, toolbar, openedResource, closedResource);

        hostActivity = host;
        this.drawerLayout = drawerLayout;
        this.openedResource = openedResource;
        this.closedResource = closedResource;
    }

    @Override
    public void onDrawerOpened(View drawerView)
    {
        int drawerType = (int)drawerView.getTag();

        if (drawerType == 0){
            super.onDrawerOpened(drawerView);
            hostActivity.getSupportActionBar().setTitle(openedResource);
        }

        if (drawerView.getId() == R.id.nav_view)
            leftIsClosed = false;

        if (drawerView.getId() == R.id.right_drawer)
            rightIsClosed = false;
    }

    @Override
    public void onDrawerClosed(View drawerView)
    {
        int drawerType = (int)drawerView.getTag();

        if (drawerType == 0){
            super.onDrawerClosed(drawerView);
            hostActivity.getSupportActionBar().setTitle(closedResource);
        }

        if (drawerView.getId() == R.id.nav_view)
            leftIsClosed = true;

        if (drawerView.getId() == R.id.right_drawer)
            rightIsClosed = true;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset)
    {
        int drawerType = (int)drawerView.getTag();

        NavigationView leftDrawer = (NavigationView)hostActivity.findViewById(R.id.nav_view);
        ListView rightDrawer = (ListView)hostActivity.findViewById(R.id.right_drawer);
        switch (drawerView.getId()){
            case R.id.right_drawer:
                if (drawerLayout.isDrawerOpen(leftDrawer) && !leftIsClosed){
                    drawerLayout.closeDrawer(leftDrawer);
                    leftIsClosed = true;
                }
                break;
            case R.id.nav_view:
                if (drawerLayout.isDrawerOpen(rightDrawer) && !rightIsClosed){
                    drawerLayout.closeDrawer(rightDrawer);
                    rightIsClosed = true;
                }
                break;
        }

        if (drawerType == 0){
            super.onDrawerSlide(drawerView, slideOffset);
        }
    }
}
