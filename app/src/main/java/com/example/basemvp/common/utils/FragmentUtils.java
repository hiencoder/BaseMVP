package com.example.basemvp.common.utils;

import com.example.basemvp.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentUtils {
    private static final FragmentUtils instance = new FragmentUtils();

    public static FragmentUtils getInstance() {
        return instance;
    }

    /**
     * Replace fragment with backstack
     *
     * @param fragmentManager:
     * @param fragment
     * @param container
     */
    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int container) {
        String FRAGMENT_TAG = fragment.getClass().getSimpleName();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
                .replace(container, fragment)
                .addToBackStack(FRAGMENT_TAG)
                .commitAllowingStateLoss();
    }

    /**
     * Replace fragment without backstack
     *
     * @param fragmentManager
     * @param fragment
     * @param container
     */
    public void replaceFragmentWithoutBackStack(FragmentManager fragmentManager, Fragment fragment, int container) {
        String FRAGMENT_TAG = fragment.getClass().getSimpleName();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
                .replace(container, fragment, FRAGMENT_TAG)
                .commit();
    }

    /**
     * @param fragmentManager
     * @param fragment
     * @param container
     */
    public void addFragment(FragmentManager fragmentManager, Fragment fragment, int container) {
        String FRAGMENT_TAG = fragment.getClass().getSimpleName();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
                .replace(container, fragment)
                .addToBackStack(FRAGMENT_TAG)
                .commit();
    }

    /**
     * Add fragment without backstack
     *
     * @param fragmentManager
     * @param fragment
     * @param container
     */
    public void addFragmentWithoutBackStack(FragmentManager fragmentManager, Fragment fragment, int container) {
        String FRAGMENT_TAG = fragment.getClass().getSimpleName();
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out)
                .replace(container, fragment, FRAGMENT_TAG)
                .commit();
    }

    /**
     * Replace child fragment
     *
     * @param parentFragment
     * @param childFragment
     * @param container
     */
    public void replaceChildFragment(Fragment parentFragment, Fragment childFragment, int container) {
        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();
        parentFragment.getChildFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out)
                .addToBackStack(FRAGMENT_TAG)
                .replace(container, childFragment, FRAGMENT_TAG)
                .commit();
    }

    /**
     * Replace fragment without backstack
     *
     * @param parentFragment
     * @param childFragment
     * @param container
     */
    public void replaceChildFragmentWithoutBackStack(Fragment parentFragment, Fragment childFragment, int container) {
        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();
        parentFragment.getChildFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out)
                .replace(container, childFragment, FRAGMENT_TAG)
                .commit();
    }

    /**
     * @param parentFragment
     * @param childFragment
     * @param container
     */
    public void addChildFragment(Fragment parentFragment, Fragment childFragment, int container) {
        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();
        parentFragment.getChildFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out)
                .addToBackStack(FRAGMENT_TAG)
                .add(container, childFragment, FRAGMENT_TAG)
                .commit();
    }

    /**
     * @param parentFragment
     * @param childFragment
     * @param container
     */
    public void addChildFragmentWithoutBackStack(Fragment parentFragment, Fragment childFragment, int container) {
        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();
        parentFragment.getChildFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out)
                .add(container, childFragment, FRAGMENT_TAG)
                .commit();
    }

    /**
     * Back fragment with name
     *
     * @param fragmentManager
     * @param fragmentName
     */
    public void backFragmentWithName(FragmentManager fragmentManager, String fragmentName) {
        while (fragmentManager.getBackStackEntryCount() > 1) {
            if (fragmentManager.getBackStackEntryAt(
                    fragmentManager.getBackStackEntryCount() - 1).getName().equals(fragmentName)) {
                return;
            }
            try {
                fragmentManager.popBackStackImmediate();
            } catch (IllegalStateException ex) {
                ex.getMessage();
            }
        }
    }

    /**
     * @param fragmentManager
     * @param index
     * @return Fragment with index
     */
    public Fragment getFragmentBackStack(FragmentManager fragmentManager, int index) {
        String FRAGMENT_TAG = fragmentManager.getBackStackEntryAt(index).getName();
        return fragmentManager.findFragmentByTag(FRAGMENT_TAG);
    }

    /**
     * @param fragmentManager
     * @param fragment
     * @param viewContainer
     */
    public void replaceFragmentAfterResetBackstack(FragmentManager fragmentManager, Fragment fragment, int viewContainer) {
        while (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }
        replaceFragment(fragmentManager, fragment, viewContainer);
    }

    /**
     * Reset backstack
     *
     * @param fragmentManager
     */
    public void resetBackStack(FragmentManager fragmentManager) {
        while (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }
    }

    /**
     * Back to main
     * @param fragmentManager
     */
    public void backStackToMain(FragmentManager fragmentManager) {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount() - 1; ++i) {
            fragmentManager.popBackStack();
        }
    }

}
