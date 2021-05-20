package com.example.bank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bank.Fragment.FragmentTrangChu;
import com.example.bank.Fragment.Fragment_CK24_7;
import com.example.bank.Fragment.Fragment_CKTNH;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
            {
                return new FragmentTrangChu();
            }
            case 1:
            {
                return new Fragment_CKTNH();

            }
            case 2:
            {
                return new Fragment_CK24_7();

            }
            default:
            {
                return  new FragmentTrangChu();

            }
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
            {
                return "Trang chủ";
            }
            case 1:
            {
                return "Trong NH";
            }
            case 2:
                return "CK 24/7";
            default:
                return "Trang chủ";
        }
    }
}
