
package com.example.academia.adapters;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.academia.CarouselFragment;
import com.example.academia.OnCarouselItemClickListener;
import com.example.academia.R;
import com.example.academia.model.Entity;
import com.example.academia.view.CarouselViewPager;
import com.example.academia.view.ScaledFrameLayout;
import java.util.List;

public class CarouselAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private final static float BIG_SCALE = 1.0f;
    private final static float SMALL_SCALE = 0.90f;
    private final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;

    private float scale;
    private CarouselViewPager carousel;

    private Context context;
    private FragmentManager fragmentManager;
    private List<Entity> entities;
    private ScaledFrameLayout currentLayout = null;
    private ScaledFrameLayout nextLayout = null;

    // Adicionando a interface OnCarouselItemClickListener
    private OnCarouselItemClickListener onCarouselItemClickListener;

    // Construtor que aceita a interface como parâmetro
    public CarouselAdapter(Context context,
                           CarouselViewPager carousel,
                           FragmentManager fragmentManager,
                           List<Entity> mData,
                           OnCarouselItemClickListener listener) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.carousel = carousel;
        this.entities = mData;
        this.onCarouselItemClickListener = listener;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            scale = BIG_SCALE;
        } else {
            scale = SMALL_SCALE;
        }

        // Cria uma instância do Fragment e define um clique no item
        final CarouselFragment carouselFragment = (CarouselFragment) CarouselFragment.newInstance(context, entities.get(position), position, scale);
        carouselFragment.setOnViewPagerItemClickListener(new CarouselFragment.OnViewPagerItemClickListener() {
            @Override
            public void onPageItemSelected(int position) {
                // Este método é chamado quando o item é clicado
                if (onCarouselItemClickListener != null) {
                    onCarouselItemClickListener.onCarouselItemClick(entities.get(position));
                }
            }
        });

        return carouselFragment;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset >= 0f && positionOffset <= 1f) {
            currentLayout = getRootView(position);
            currentLayout.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);

            if (position < entities.size() - 1) {
                nextLayout = getRootView(position + 1);
                nextLayout.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onPageSelected(int position) {
        Log.w("onPageSelected", "position : " + position);
        // Notifica o ouvinte sobre a mudança de página
//        if (onCarouselItemClickListener != null) {
//            onCarouselItemClickListener.onCarouselItemClick(entities.get(position));
//        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private ScaledFrameLayout getRootView(int position) {
        Fragment fragment = fragmentManager.findFragmentByTag(this.getFragmentTag(position));
        return (ScaledFrameLayout) fragment.getView().findViewById(R.id.rootItem);
    }

    private String getFragmentTag(int position) {
        return "android:switcher:" + carousel.getId() + ":" + position;
    }

    public static float getSmallScale() {
        return SMALL_SCALE;
    }

    // Método adicionado para atualizar a lista de entidades
    public void updateEntities(List<Entity> newEntities) {
        this.entities.clear();
        this.entities.addAll(newEntities);
        notifyDataSetChanged(); // Notifica o ViewPager sobre as mudanças
    }

    // Adicionando um setter para a interface OnCarouselItemClickListener
    public void setOnCarouselItemClickListener(OnCarouselItemClickListener listener) {
        this.onCarouselItemClickListener = listener;
    }
}


//import android.content.Context;
//import android.util.Log;
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//import com.example.academia.CarouselFragment;
//import com.example.academia.OnCarouselItemClickListener;
//import com.example.academia.R;
//import com.example.academia.model.Entity;
//import com.example.academia.view.CarouselViewPager;
//import com.example.academia.view.ScaledFrameLayout;
//import java.util.List;
//
//public class CarouselAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
//
//    private final static float BIG_SCALE = 1.0f;
//    private final static float SMALL_SCALE = 0.90f;
//    private final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;
//
//    private float scale;
//    private CarouselViewPager carousel;
//
//    private Context context;
//    private FragmentManager fragmentManager;
//    private List<Entity> entities;
//    private ScaledFrameLayout currentLayout = null;
//    private ScaledFrameLayout nextLayout = null;
//
//    // Adicionando a interface OnCarouselItemClickListener
//    private OnCarouselItemClickListener onCarouselItemClickListener;
//
//    // Construtor que aceita a interface como parâmetro
//    public CarouselAdapter(Context context,
//                           CarouselViewPager carousel,
//                           FragmentManager fragmentManager,
//                           List<Entity> mData,
//                           OnCarouselItemClickListener listener) {
//        super(fragmentManager);
//        this.fragmentManager = fragmentManager;
//        this.context = context;
//        this.carousel = carousel;
//        this.entities = mData;
//        this.onCarouselItemClickListener = listener;
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        if (position == 0) {
//            scale = BIG_SCALE;
//        } else {
//            scale = SMALL_SCALE;
//        }
//        return CarouselFragment.newInstance(context, entities.get(position), position, scale);
//    }
//
//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        return super.getItemPosition(object);
//    }
//
//    @Override
//    public int getCount() {
//        return entities.size();
//    }
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        if (positionOffset >= 0f && positionOffset <= 1f) {
//            currentLayout = getRootView(position);
//            currentLayout.setScaleBoth(BIG_SCALE - DIFF_SCALE * positionOffset);
//
//            if (position < entities.size() - 1) {
//                nextLayout = getRootView(position + 1);
//                nextLayout.setScaleBoth(SMALL_SCALE + DIFF_SCALE * positionOffset);
//            }
//        }
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return super.getItemId(position);
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        Log.w("onPageSelected", "position : " + position);
//        // Notifica o ouvinte sobre a mudança de página
//        if (onCarouselItemClickListener != null) {
//            onCarouselItemClickListener.onCarouselItemClick(entities.get(position));
//        }
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//    }
//
//    private ScaledFrameLayout getRootView(int position) {
//        Fragment fragment = fragmentManager.findFragmentByTag(this.getFragmentTag(position));
//        return (ScaledFrameLayout) fragment.getView().findViewById(R.id.rootItem);
//    }
//
//    private String getFragmentTag(int position) {
//        return "android:switcher:" + carousel.getId() + ":" + position;
//    }
//
//    public static float getSmallScale() {
//        return SMALL_SCALE;
//    }
//
//    // Método adicionado para atualizar a lista de entidades
//    public void updateEntities(List<Entity> newEntities) {
//        this.entities.clear();
//        this.entities.addAll(newEntities);
//        notifyDataSetChanged(); // Notifica o ViewPager sobre as mudanças
//    }
//
//    // Adicionando um setter para a interface OnCarouselItemClickListener
//    public void setOnCarouselItemClickListener(OnCarouselItemClickListener listener) {
//        this.onCarouselItemClickListener = listener;
//    }
//}
//

