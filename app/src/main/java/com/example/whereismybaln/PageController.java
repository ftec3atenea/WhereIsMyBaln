package com.example.whereismybaln;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PageController extends FragmentStateAdapter {
    public PageController(@NonNull FragmentManager fm, @NonNull Lifecycle lc) {
        super(fm, lc);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new America();
            case 1:
                return new Europe();
            default:
                return null;
        }
    }
    @Override
    public int getItemCount() {
        return 2;
    }
}