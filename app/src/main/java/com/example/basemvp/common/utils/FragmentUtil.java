package com.example.basemvp.common.utils;

import com.example.basemvp.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentUtil {
    private static final FragmentUtil instance = new FragmentUtil();

    public static FragmentUtil getInstance() {
        return instance;
    }

    /**
     * Replace fragment with backstack
     *
     * @param fragmentManager:
     * @param fragment
     * @param container
     */
    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int container) {
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
    public static void replaceFragmentWithoutBackStack(FragmentManager fragmentManager, Fragment fragment, int container) {
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
    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int container) {
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
    public static void addFragmentWithoutBackStack(FragmentManager fragmentManager, Fragment fragment, int container) {
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
    public static void replaceChildFragment(Fragment parentFragment, Fragment childFragment, int container) {
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
    public static void replaceChildFragmentWithoutBackStack(Fragment parentFragment, Fragment childFragment, int container) {
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
    public static void addChildFragment(Fragment parentFragment, Fragment childFragment, int container) {
        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();
        parentFragment.getChildFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out)
                .addToBackStack(FRAGMENT_TAG)
                .add(container, childFragment, FRAGMENT_TAG)
                .commit();
    }

    /**
     *
     * @param parentFragment
     * @param childFragment
     * @param container
     */
    public static void addChildFragmentWithoutBackStack(Fragment parentFragment, Fragment childFragment, int container) {
        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();
        parentFragment.getChildFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out)
                .add(container, childFragment, FRAGMENT_TAG)
                .commit();
    }

    /**
     *
     * @param fragmentManager
     * @param fragmentName
     */
    public static void backFragmentWithName(FragmentManager fragmentManager, String fragmentName){

    }
}
