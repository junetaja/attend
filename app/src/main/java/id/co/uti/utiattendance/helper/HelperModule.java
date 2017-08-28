package id.co.uti.utiattendance.helper;

import dagger.Module;
import dagger.Provides;
import id.co.uti.utiattendance.helper.navigator.AppNavigator;
import zambelz.library.core.scopes.AppScope;

/**
 * Created by DELL on 8/22/2017.
 */

@Module
public class HelperModule {

    public HelperModule() {}

    @Provides
    @AppScope
    AppNavigator provideAppNavigator() {
        return new AppNavigator();
    }
}
