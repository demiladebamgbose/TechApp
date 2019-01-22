package inc.maprace.techapp.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

import inc.maprace.techapp.data.ArticlesRepository;

/**
 * Created by demiladebamgbose on 12/11/17.
 */

public class ViewModelFactory implements ViewModelProvider.Factory {
    private ArticlesRepository mArticleRepository;

    public ViewModelFactory (ArticlesRepository articlesRepository) {
        mArticleRepository = articlesRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(  MainViewModel.class)) {
            return (T) new MainViewModel(mArticleRepository);
        } else throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
