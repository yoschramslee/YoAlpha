package com.example.yoalpha2;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

public class Util {

    public static boolean switchTo(Activity self, int id){
        Intent switchActivity = null;
        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_announcements) {
            switchActivity = new Intent(self, AnnouncementsActivity.class);
        } else if (id == R.id.nav_info) {
            switchActivity = new Intent(self, InfoActivity.class);
        } else if (id == R.id.nav_clubsport) {
            switchActivity = new Intent(self, ClubSportActivity.class);
        } else if (id == R.id.nav_teachers) {
            switchActivity = new Intent(self, DirectoryActivity.class);
        }


        if (switchActivity instanceof Intent)
        {
            self.startActivity(switchActivity);
            DrawerLayout drawer = self.findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            if (!(self instanceof MainActivity)) {
                self.finish();

            }
            return true;
        }
        return false;
    }
}
