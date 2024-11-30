package com.fatec.recycleapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.fatec.recycleapp.ui.fragments.signup.SignUpAddressFragment;
import com.fatec.recycleapp.ui.fragments.signup.SignUpInformationFragment;
import com.fatec.recycleapp.ui.fragments.signup.SignUpMaterialFragment;
import com.fatec.recycleapp.ui.fragments.signup.SignUpSuccessFragment;
import com.fatec.recycleapp.ui.fragments.signup.SignUpTypeFragment;

public class SignUpAdapter extends FragmentStateAdapter {

    public SignUpAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SignUpInformationFragment();
            case 1:
                return new SignUpAddressFragment();
            case 2:
                return new SignUpMaterialFragment();
            case 3:
                return new SignUpTypeFragment();
            case 4:
                return new SignUpSuccessFragment();
            default:
                return new SignUpInformationFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5; // Quantidade de fragmentos que você tem
    }
}